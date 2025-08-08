package entities.state;

import entities.character.Character;
import entities.skill.Skill;

public class StunnedState extends State{
    public StunnedState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int turns) {
        super(life, strength, intelligence, agility, vigor, mana, defense, turns);
    }

    public static State of(State state) {
        return new StunnedState(
                state.getLife(),
                state.getStrength(),
                state.getIntelligence(),
                state.getAgility(),
                state.getVigor(),
                state.getMana(),
                state.getDefense(),
                1
        );
    }

    @Override
    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill) {

    }

    @Override
    public void receiveDamage(double value, Character passivePlayer, String effectName) {

    }

    @Override
    public void receiveEffect(String name) {

    }

    @Override
    public void onDeath(Character character) {

    }

    @Override
    public boolean isAlive() {
        return false;
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
    public void stateCountDown(Character actionPlayer, State state) {

    }
}
