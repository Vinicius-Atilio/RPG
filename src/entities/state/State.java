package entities.state;

import entities.ally.Ally;
import entities.character.Character;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public abstract class State {
    protected double life;
    protected int strength;
    protected int intelligence;
    protected int agility;
    protected int vigor;
    protected int mana;
    protected int defense;
    protected int temporallyShield;
    protected int stateDuration;

    public State(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        this.life = life;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.vigor = vigor;
        this.mana = mana;
        this.defense = defense;
    }

    public State(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int stateDuration) {
        this.life = life;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.vigor = vigor;
        this.mana = mana;
        this.defense = defense;
        this.stateDuration = stateDuration;
    }

    public void update(State state) {
        if (state == null) return;
        this.strength += state.strength;
        this.intelligence += state.intelligence;
        this.agility += state.agility;
        this.vigor += state.vigor;
        this.mana += state.mana;
        this.defense += state.defense;
    }

    public State(String name, State state) {
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "strength=" + strength +
                ", intelligence=" + intelligence +
                ", agility=" + agility +
                ", vigor=" + vigor +
                ", mana=" + mana +
                ", defense=" + defense +
                '}';
    }

    public double getLife() {
        return life;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public int getVigor() {
        return vigor;
    }

    public int getMana() {
        return mana;
    }

    public abstract void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill);
    public abstract void receiveDamage(double value, Character passivePlayer, String effectName);
    public abstract void receiveDamage(Trap trap);
    public abstract void receiveEffect(String name);

    public abstract void onDeath(Character character);
    public abstract boolean isAlive();

    public abstract double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack);
    public abstract double calculateAllyDamage(Ally ally, Skill skill, Character actionPlayer, Character passivePlayer);
    public abstract double calculateAllyHeal(Ally ally, Skill skill, Character actionPlayer);
    public abstract double calculateDefense();

    public abstract State withLife(double life);
    public abstract void stateCountDown(Character actionPlayer, State state);

    public abstract void receiveHeal(double value);
}
