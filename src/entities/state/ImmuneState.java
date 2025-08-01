package entities.state;

import entities.character.Character;
import entities.skill.Skill;

public class ImmuneState extends State {
    public ImmuneState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(life, strength, intelligence, agility, vigor, mana, defense);
    }

    public ImmuneState(String name, State state) {
        super(name, state);
    }

    public static ImmuneState of(State state) {
        return new ImmuneState(
                state.getLife(),
                state.getStrength(),
                state.getIntelligence(),
                state.getAgility(),
                state.getVigor(),
                state.getMana(),
                state.getDefense()
        );
    }

    @Override
    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill) {
        System.out.printf(" Está imune a ataques, sob efeito de %s!%n");
    }

    @Override
    public void receiveDamage(double value, Character passivePlayer, String effectName) {
    }


    @Override
    public void receiveEffect(String name) {

    }

    @Override
    public void onDeath(Character character) {
        character.changeState(DeadState.of(new Character("* DEAD " + character.getName(), OriginalState.ofDeath())));
    }

    @Override
    public boolean isAlive() {
        return this.life > 0;
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
        this.life = life;
        return this;
    }

    @Override
    public void stateCountDown(Character actionPlayer, State state) {
    }

    @Override
    public String toString() {
        String stateName = "Immune State";
        return "ImmuneState{" + stateName + '}';
    }
}
