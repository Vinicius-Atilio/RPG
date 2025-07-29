package entities.state;

import entities.character.Character;
import entities.effect.StatusEffect;
import entities.skill.Skill;

public class DeadState extends State {

    public DeadState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(life, strength, intelligence, agility, vigor, mana, defense);
    }

    public DeadState(String name, State state) {
        super(name, state);
    }

    public static DeadState of(Character character) {
        return new DeadState(character.getName(), character.getState());
    }

    @Override
    public double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack) {
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
    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill, StatusEffect statusEffect) {

    }

    @Override
    public void receiveEffect(String name) {

    }

    /**
     * This method is called when the character dies.
     * It can be overridden by subclasses to provide specific behavior.
     *
     * @param character The character that has died.
     */
    @Override
    public void onDeath(Character character) {
        System.out.println("ESTOU MORRENDO " + character.getName() + "!");
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
