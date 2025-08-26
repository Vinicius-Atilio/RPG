package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public class DeadState extends State {

    public DeadState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(life, strength, intelligence, agility, vigor, mana, defense);
    }

    public DeadState(String name) {
        super(name);
    }

    public static DeadState of(Player player) {
        return new DeadState("*MORTO* " + player.getName());
    }

    public static DeadState of(String allyName) {
        return new DeadState("*MORTO* " + allyName);
    }

    @Override
    public double calculateDamage(Player activePlayer, Player passivePlayer, int activeSKillPowerAttack) {
        return 0;
    }

    @Override
    public double calculateAllyDamage(Ally ally, Skill skill, Player actionPlayer, Player passivePlayer) {
        return 0;
    }

    @Override
    public double calculateAllyHeal(Ally ally, Skill skill, Player activePlayer) {
        return 0;
    }

    @Override
    public double calculateDefense() {
        return 0;
    }

    @Override
    public State withLife(double life) {
        return null;
    }

    @Override
    public void stateCountDown(Player actionPlayer, State state) {
    }

    @Override
    public void receiveHeal(double value) {

    }

    @Override
    public boolean canAttack(String activePlayerName) {
        return false;
    }

    @Override
    public void receiveEffect(String name) {

    }

    /**
     * This method is called when the character dies.
     * It can be overridden by subclasses to provide specific behavior.
     *
     * @param player The character that has died.
     */
    @Override
    public void onDeath(Player player) {
        System.out.println("ESTOU MORRENDO " + player.getName() + "!");
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public String toString() {
        String stateName = "Dead State";
        return "DeadState{" + stateName + '}';
    }
}
