package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

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
    public void receiveDamage(Player actionPlayer, Player passivePlayer, double value, Skill skill) {
        System.out.printf(" Está imune a ataques, sob efeito de %s!%n");
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
        player.changeState(DeadState.of(new Player("* DEAD " + player.getName(), OriginalState.ofDeath())));
    }

    @Override
    public boolean isAlive() {
        return this.life > 0;
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
        this.life = life;
        return this;
    }

    @Override
    public void stateCountDown(Player actionPlayer, State state) {
    }

    @Override
    public void receiveHeal(double value) {

    }

    @Override
    public String toString() {
        String stateName = "Immune State";
        return "ImmuneState{" + stateName + '}';
    }
}
