package entities.character;

import entities.Inventory;
import entities.Weapon;
import entities.ally.Ally;
import entities.effect.StatusEffect;
import entities.skill.Skill;
import entities.state.*;
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
    private List<Skill> skills;
    private StatusEffect statusEffect;
    private Weapon weapon;
    private Ally ally;
    private Inventory inventory;
    private State state;
    private boolean immune;

    public Character(String name, Race race, Specialization specialization, State state, List<Skill> skills, Weapon weapon, Inventory inventory) {
        this.id = new BigInteger(128, new SecureRandom());
        this.name = name;
        this.race = race;
        this.specialization = specialization;
        this.skills = skills;
        this.weapon = weapon;
        this.inventory = inventory;
        this.state = OriginalState.ofState(state);

        if (this.weapon != null) {
            this.state.update(this.weapon.getState());
        }
    }

    public Character(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public boolean timeToDie() {
        return this.state.getLife() <= 0;
    }

    public boolean isAlive() {
        return this.state.isAlive();
    }

    public String getName() {
        return name;
    }

    public double getLife() {
        return this.state.getLife();
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

    public void receiveDamage(Character actionPlayer, int activeSKillPowerAttack, Skill skill) {
        this.state.receiveDamage(actionPlayer, this, this.attackPower(actionPlayer, activeSKillPowerAttack), skill, this.statusEffect);
    }

    public double attackPower(Character actionPlayer, int activeSKillPowerAttack) {
        return this.state.calculateDamage(actionPlayer, this, activeSKillPowerAttack);
    }

    public int getMainAttribute() {
        return switch (this.specialization) {
            case Warrior, Paladin -> this.state.getStrength();
            case Mage -> this.state.getIntelligence();
            case Hunter -> this.state.getAgility();
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
                this.state.update(this.ally.getState());
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
            this.state.receiveEffect(this.name); // Aplicando o efeito do status
            if (this.state.getLife() <= 0) {
                System.out.println(this.name + " morreu devido ao efeito " + this.statusEffect.getName() + ".");
                this.makeDeath();
                return;
            }

            this.statusEffect.updateTurnDuration();
            System.out.println(this.statusEffect.getTurnDuration() + " turnos restantes do efeito " + this.statusEffect.getName() + " em " + this.name + ".");
            return;
        }

        System.out.println("O efeito " + this.statusEffect.getName() + " de " + this.name + " expirou.");
        this.statusEffect = null;
        this.state = this.specialization.state(this.state.getLife()); // Resetando os atributos para o padrão da especialização
    }

    public Ally getAlly() {
        return ally;
    }

    public void makeImmune() {
        this.state = ImmuneState.of(this.state);
    }

    public void makePoisoned() {
        this.state = PoisonedState.of(this.state);
    }

    public void makeEvasion() {
        this.state = EvasionState.of(this.state);
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
        return this.state.getDefense();
    }

    public int poisonedDefenseValue() {
        return this.state.getDefense() - (int) (this.state.getDefense() * 0.1);
    }

    public int tiredDefenseValue() {
        return this.state.getDefense() - (int) (this.state.getDefense() * 0.5);
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
                ", attribute=" + state +
                ", skills=" + skills +
                ", weapon=" + weapon +
                ", ally=" + ally +
                ", inventory=" + inventory +
                ", life=" + this.state.getLife() +
                '}';
    }
}
