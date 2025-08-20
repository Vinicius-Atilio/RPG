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

    public State(String name, State state) {
    }

    private void playerIsDied(String skillName, Player passivePlayer) {
        System.out.println("💥 " + skillName + " atinge em cheio!");
        System.out.println("❤️ " + passivePlayer.getName() + " tinha apenas " +  String.format("%.2f", passivePlayer.getLife() > 0 ? passivePlayer.getLife() : 0 ) + " de vida restante...");
        System.out.println("\n💀 Ele é derrubado com o impacto. Sua armadura racha e ele cai de joelhos.");
        System.out.println("🕯️ A chama da vida se apaga em seus olhos...");
        System.out.println("⚰️ " + passivePlayer.getName() + " foi derrotado.");
    }

    protected void validateIfPlayerIsAlive(String skillName, Player passivePlayer) {
        if (!this.isAlive()) {
            passivePlayer.makeDeath();
            this.playerIsDied(skillName, passivePlayer);
        }
    }

    public void receiveDamage(Player activePlayer, Player passivePlayer, double value, Skill skill) {
        this.life -= value;
        skill.skillEffectAction(activePlayer, passivePlayer);
        System.out.printf("😤 %s recebeu o dano de %.2f devido ao ataque de %s!%n", passivePlayer.getName(), value, activePlayer.getName());
        validateIfPlayerIsAlive(skill.getName(), passivePlayer);
    }

    public void receiveDamage(double value, Player passivePlayer, String effectName) {
        this.life -= value;
        System.out.printf("😤 %s recebeu o dano de %.2f devido ao efeito %s!%n", passivePlayer.getName(), value, effectName);
        validateIfPlayerIsAlive(effectName, passivePlayer);
    }

    public void receiveDamage(Trap trap, Player passivePlayer) {
        this.life -= trap.getDamage();
        System.out.println("💣 A armadilha explode causando " + trap.getDamage() + " de dano!");
        validateIfPlayerIsAlive(trap.getName(), passivePlayer);
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

    public abstract double calculateDamage(Player activePlayer, Player passivePlayer, int activeSKillPowerAttack);
    public abstract double calculateAllyDamage(Ally ally, Skill skill, Player actionPlayer, Player passivePlayer);
//    public abstract double calculateAllyHeal(Ally ally, Skill skill, Player activePlayer);
    public abstract double calculateDefense();

    public abstract State withLife(double life);
//    public abstract void stateCountDown(Player activePlayer, State state);
    public abstract void receiveHeal(double value);
    public abstract boolean canAttack(String activePlayerName);
}
