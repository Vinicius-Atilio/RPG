package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public class TiredState extends State {
    public TiredState(int life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int turns) {
        super("Estado Cansado", life, strength, intelligence, agility, vigor, mana, defense, turns);
    }

    public static TiredState of(State state) {
        return new TiredState(
                (int) state.getLife(),
                (int) (state.getStrength() * 0.8),
                (int) (state.getIntelligence() * 0.8),
                (int) (state.getAgility() * 0.8),
                (int) (state.getVigor() * 0.8),
                (int) (state.getMana() * 0.8),
                (int) (state.getDefense() * 0.8),
                1
        );
    }

    @Override
    public double calculateDamage(Player activePlayer, Player passivePlayer, int activeSKillPowerAttack) {
        System.out.println("O jogador " + passivePlayer.getName() + " está cansado! Dano aumentado.");
        return (activePlayer.getMainAttribute() * activePlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.tiredDefenseValue());
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
        this.life = life;
        return this;
    }

    @Override
    public void receiveHeal(double value) {

    }

    @Override
    public void receiveDamage(Player activePlayer, Player passivePlayer, double value, Skill skill) {
        this.life -= value * 1.2; // Aumenta o dano recebido por cansaço
        skill.skillEffectAction(activePlayer, passivePlayer);
    }

    @Override
    public void receiveDamage(double value, Player passivePlayer, String effectName) {
    }

    @Override
    public void receiveDamage(Trap trap, Player passivePlayer) {

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

    @Override
    public boolean canAttack(String activePlayerName) {
        System.out.println("O jogador " + activePlayerName + " está cansado e não pode atacar!");
        return false;
    }
}
