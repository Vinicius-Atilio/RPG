package entities.state;

import entities.character.Attribute;
import entities.character.Character;
import entities.effect.StatusEffect;
import entities.skill.Skill;

public abstract class State {
    protected double life;
    protected int strength;
    protected int intelligence;
    protected int agility;
    protected int vigor;
    protected int mana;
    protected int defense;
    protected int temporallyShield;

    public State(double life) {
        this.life = life;
    }

    public State(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        this.life = life;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.vigor = vigor;
        this.mana = mana;
        this.defense = defense;
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

    public abstract void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill, StatusEffect statusEffect);
    public abstract void receiveEffect(String name);

    public abstract void onDeath(Character character);
    public abstract boolean isAlive();

    public abstract double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack);
    public abstract double calculateDefense();

    public abstract State withLife(double life);
}
