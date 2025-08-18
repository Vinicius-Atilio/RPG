package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public class StunnedState extends State {
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
    public void receiveDamage(Player actionPlayer, Player passivePlayer, double value, Skill skill) {

    }

    @Override
    public void receiveDamage(double value, Player passivePlayer, String effectName) {

    }

    @Override
    public void receiveDamage(Trap trap) {

    }

    @Override
    public void receiveEffect(String name) {

    }

    @Override
    public void onDeath(Player player) {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public double calculateDamage(Player actionPlayer, Player passivePlayer, int activeSKillPowerAttack) {
        return 0;
    }

    @Override
    public double calculateAllyDamage(Ally ally, Skill skill, Player actionPlayer, Player passivePlayer) {
        return 0;
    }

    @Override
    public double calculateAllyHeal(Ally ally, Skill skill, Player actionPlayer) {
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
}
