package entities.character;

import entities.Inventory;
import entities.Weapon;
import entities.ally.Ally;
import entities.skill.Skill;
import entities.state.AliveState;
import entities.state.State;
import enums.Race;
import enums.Specialization;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
    private BigInteger id;
    private String name;
    private Race race;
    private Specialization specialization;
    private Attribute attribute;
    private List<Skill> skills;
    private StatusEffect statusEffect;
    private Weapon weapon;
    private Ally ally;
    private Inventory inventory;
    private State state;
    private boolean immune;

    public Character(String name, Race race, Specialization specialization, Attribute attribute, List<Skill> skills, Weapon weapon, Inventory inventory) {
        this.id = new BigInteger(128, new SecureRandom());
        this.name = name;
        this.race = race;
        this.specialization = specialization;
        this.attribute = attribute;
        this.skills = skills;
        this.weapon = weapon;
        this.inventory = inventory;
        this.state = new AliveState();

        if (this.weapon != null) {
            this.attribute.update(this.weapon.getAttribute());
        }
    }

    public Character(String name, Attribute attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public boolean timeToDie() {
        return this.attribute.getLife() <= 0;
    }

    public boolean isAlive() {
        return this.state.isAlive();
    }

    public String getName() {
        return name;
    }

    public double getLife() {
        return this.attribute.getLife();
    }

    public void prepareToPlay() {
        this.skills.forEach(Skill::updateSkillCooldown);

        this.updateStatusEffect();
    }

    public Skill play() {
        Skill selectedSkill = this.skills.get(ThreadLocalRandom.current().nextInt(this.skills.size()));
        if (selectedSkill.getCurrentCooldown() == 0) {
            return selectedSkill;
        }

        return play();
    }

    public void decreaseAllSkillsCd() {
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public boolean receiveAttack(Character actionPlayer, int activeSKillPowerAttack) {
        if (this.immune) {
            System.out.printf("%s está imune a ataques, sob efeito de %s!%n", this.name, this.statusEffect != null ? this.statusEffect.getName() : "imunidade");
            return false;
        }

        this.attribute.receiveDamage(this.attackPower(actionPlayer, activeSKillPowerAttack));
        return true;
    }

    public double attackPower(Character actionPlayer, int activeSKillPowerAttack) {
        return this.attribute.calculateDamage(actionPlayer, this, activeSKillPowerAttack);
    }

    public int getMainAttribute() {
        return switch (this.specialization) {
            case Warrior, Paladin -> this.attribute.getStrength();
            case Mage -> this.attribute.getIntelligence();
            case Hunter -> this.attribute.getAgility();
        };
    }

    public void makeDefense(Character actionPlayer, Skill skill) {
        skill.updateSkillCooldown();
    }

    public void  evokeAlly() {
        this.ally = this.specialization.ally();
    }

    public void useAllyIfAlive() {
        if (this.ally != null && this.ally.isAlive()) {
            if (this.ally.isSupport()) {
                this.attribute.update(this.ally.getAttribute());
            }
        }
    }

    public void addEffect(StatusEffect statusEffect) {
        this.statusEffect = statusEffect;
    }

    public void applyEffect() {
        if (this.statusEffect == null) {
            System.out.println("Nenhum efeito ativo.");
            return;
        }

        this.statusEffect.accept(this);
    }

    private void updateStatusEffect() {
        if (this.statusEffect == null) {
            return;
        }

        if (this.statusEffect.getTurnDuration() > 0) {
            this.attribute.receiveEffect(this.name); // Aplicando o efeito do status
            this.statusEffect.updateTurnDuration();
            System.out.println(this.statusEffect.getTurnDuration() + " turnos restantes do efeito " + this.statusEffect.getName() + " de " + this.name + ".");
            return;
        }

        System.out.println("O efeito " + this.statusEffect.getName() + " de " + this.name + " expirou.");
        this.statusEffect = null;
        this.attribute = this.specialization.attribute(); // Resetando os atributos para o padrão da especialização
//        this.immune = false; //TODO ENVIAR PARA O STATE
    }

    public Ally getAlly() {
        return ally;
    }

    public void markAsImmune() {
        this.immune = true;
    }

    public void makePoisoned() {
        this.attribute = PoisonedAttribute.of(this.attribute);
    }

    public boolean canBeAttacked() {
        return this.isAlive() && !this.immune;
    }

    public void makeDeath() {
        this.state.onDeath(this);
    }

    public void changeState(State state) {
        this.state = state;
    }
    
    public double weaponFactor() {
        return this.weapon.getConversionFactor();
    }

    public int originalDefenseValue() {
        return this.attribute.getDefense();
    }

    public int poisonedDefenseValue() {
        return this.attribute.getDefense() - (int) (this.attribute.getDefense() * 0.1);
    }

    public int tiredDefenseValue() {
        return this.attribute.getDefense() - (int) (this.attribute.getDefense() * 0.5);
    }

    public BigInteger getId() {
        return id;
    }

    public Race getRace() {
        return race;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public StatusEffect getStatusEffect() {
        return statusEffect;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public State getState() {
        return state;
    }

    public boolean isImmune() {
        return immune;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", race=" + race +
                ", specialization=" + specialization +
                ", attribute=" + attribute +
                ", skills=" + skills +
                ", weapon=" + weapon +
                ", ally=" + ally +
                ", inventory=" + inventory +
                ", life=" + this.attribute.getLife() +
                '}';
    }
}
