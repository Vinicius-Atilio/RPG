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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
    private BigInteger id;
    private String name;
    private Race race;
    private Specialization specialization;
    private List<Skill> skills;
    private List<StatusEffect> effects;
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
        this.effects = new ArrayList<>();

        if (this.weapon != null) {
            this.state.update(this.weapon.getState());
        }
    }

    public Character(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public boolean isAlive() {
        return this.state.isAlive();
    }

    public String getName() {
        return name;
    }

    public void prepareToPlay() {
        System.out.println("Estado atual de " + this.name + ": " + this.state.toString());
        this.state.stateCountDown(this, this.specialization.state(this.state.getLife()));

        this.applyEffect();
        this.skills.forEach(Skill::updateSkillCooldown);

        if (this.effects == null || this.effects.isEmpty()) {
            System.out.println("Nenhum efeito ativo em " + this.name + ".");
            return;
        }

        for (StatusEffect effect : new ArrayList<>(this.effects)) {
            effect.updateTurnDuration(this, effect.getName());
        }
    }

    public Skill play() {
        Skill selectedSkill = this.skills.get(ThreadLocalRandom.current().nextInt(this.skills.size()));
        if (selectedSkill.getCurrentCooldown() == 0) {
            return selectedSkill;
        }

        return play();
    }

    public void receiveDamage(Character actionPlayer, int activeSKillPowerAttack, Skill skill) {
        this.state.receiveDamage(actionPlayer,
                this,
                this.state.calculateDamage(actionPlayer,
                        this,
                        activeSKillPowerAttack),
                skill);
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
        this.effects.add(statusEffect);
    }

    public void applyEffect() {
        if (this.effects == null || this.effects.isEmpty()) {
            System.out.println("Nenhum efeito ativo.");
            return;
        }

        // Verifica se o efeito já está ativo
        for (StatusEffect effect : this.effects) {
            if (!effect.isActive() && this.effects.stream().noneMatch(e -> e.getId().equals(effect.getId()))) {
                // Evita que o mesmo efeito seja aplicado mais de uma vez
                System.out.println("Efeito " + effect.getName() + " aplicado a " + this.name + ".");
                effect.accept(this); // cuidado: se aqui adicionar outro efeito, ainda pode estourar
                effect.makeActive();
            }
        }

    }

    public double getLife() {
        return this.state.getLife();
    }


    public void receiveEffect(double value, String effectName) {
        System.out.println("O jogador " + this.name + " está sob efeito de " + effectName + " Vida reduzida em 5%." + " Vida atual: " + String.format("%.2f",  this.getLife() ));
        this.state.receiveDamage(value, this, effectName);
    }

    public Ally getAlly() {
        return ally;
    }

    public void makeImmune() {
        this.state = ImmuneState.of(this.state);
    }

    public void changeStateToEvasion() {
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
                ", life=" + this.state.isAlive() +
                '}';
    }

    public void removeEffect(StatusEffect statusEffect) {
        this.effects.remove(statusEffect);
    }
}
