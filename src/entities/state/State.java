package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public abstract class State {
    protected String stateName;
    protected double life;
    protected int strength;
    protected int intelligence;
    protected int agility;
    protected int vigor;
    protected int mana;
    protected int defense;
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

    public State(String stateName, double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int stateDuration) {
        this.stateName = stateName;
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

    public State(String name) {
        this.stateName = name;
        this.life = 0;
        this.strength = 0;
        this.intelligence = 0;
        this.agility = 0;
        this.vigor = 0;
        this.mana = 0;
        this.defense = 0;
    }



    protected void validateIfPlayerIsAlive(Player passivePlayer) {
        if (!this.isAlive()) {
            passivePlayer.makeDeath();
        }
    }

    public void receiveDamage(Player activePlayer, Player passivePlayer, double value, Skill skill) {
        this.life -= value;
        skill.skillEffectAction(activePlayer, passivePlayer);
        validateIfPlayerIsAlive(passivePlayer);
    }

    public void receiveDamage(double value, Player passivePlayer, String effectName) {
        this.life -= value;
        validateIfPlayerIsAlive(passivePlayer);
    }

    public void receiveDamage(Trap trap, Player passivePlayer) {
        this.life -= trap.getDamage();
        validateIfPlayerIsAlive(passivePlayer);
    }

    public double calculateAllyHeal(Ally ally, Skill skill, Player activePlayer) {
        return (ally.getAllyHeal() * ally.getSkillMultiplier()) + activePlayer.getMainAttribute();
    }

    public void stateCountDown(Player actionPlayer, State state) {
        if (this instanceof OriginalState) return;

        if (this.stateDuration == 0) {
            System.out.println("😌 O efeito de defensivo terminou. " + actionPlayer.getName() + " está de volta ao estado original.");
            actionPlayer.changeState(state);
        };

        this.stateDuration--;
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

//    public abstract void receiveDamage(Player activePlayer, Player passivePlayer, double value, Skill skill);
//    public abstract void receiveDamage(double value, Player passivePlayer, String effectName);
//    public abstract void receiveDamage(Trap trap, Player passivePlayer);
    public abstract void receiveEffect(String name);

    public abstract void onDeath(Player player);
    public abstract boolean isAlive();

    public abstract double calculateDamage(Player activePlayer, Player passivePlayer, double activeSKillPowerAttack);
    public abstract double calculateEnemyAllyDamage(Ally ally, Skill skill, Player actionPlayer, Player passivePlayer);
//    public abstract double calculateAllyHeal(Ally ally, Skill skill, Player activePlayer);
    public abstract double calculateDefense();

    public abstract State withLife(double life);
//    public abstract void stateCountDown(Player activePlayer, State state);
    public abstract void receiveHeal(double value);
    public abstract boolean canAttack(String activePlayerName);
}
