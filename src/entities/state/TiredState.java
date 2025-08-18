package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public class TiredState extends State {
    public TiredState(int life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(life, strength, intelligence, agility, vigor, mana, defense);
    }

    @Override
    public double calculateDamage(Player actionPlayer, Player passivePlayer, int activeSKillPowerAttack) {
        System.out.println("O jogador " + passivePlayer.getName() + " está cansado! Dano aumentado.");
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.tiredDefenseValue());
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
    public void receiveDamage(Player actionPlayer, Player passivePlayer, double value, Skill skill) {
        this.life -= value * 1.2; // Aumenta o dano recebido por cansaço
        skill.skillEffectAction(actionPlayer, passivePlayer);
    }

    @Override
    public void receiveDamage(double value, Player passivePlayer, String effectName) {
    }

    @Override
    public void receiveDamage(Trap trap) {

    }

    @Override
    public void receiveEffect(String name) {}

    @Override
    public void onDeath(Player player) {
        player.changeState(DeadState.of(new Player("* DEAD " + player.getName(), OriginalState.ofDeath())));
    }

    @Override
    public boolean isAlive() {
        return this.life > 0;
    }

    @Override
    public String toString() {
        String stateName = "Tired State";
        return "TiredState{" + stateName + '}';
    }
}
