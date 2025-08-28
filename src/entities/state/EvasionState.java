package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

import java.util.concurrent.ThreadLocalRandom;

public class EvasionState extends State {

    public EvasionState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int stateDuration) {
        super("Estado Evasivo", life, strength, intelligence, agility, vigor, mana, defense, stateDuration);
    }

    public static State of(State state) {
        return new EvasionState(
                state.getLife(),
                state.getStrength(),
                state.getIntelligence(),
                state.getAgility(),
                state.getVigor(),
                state.getMana(),
                state.getDefense(),
                2 // Duração do estado de evasão em turnos
        );
    }

    private static boolean chanceToEvade(Player activePlayer, Player passivePlayer) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.out.printf("😱 %s conseguiu desviar do ataque de %s!%n", passivePlayer.getName(), activePlayer.getName());
            return true;
        }

        System.out.printf("😢 %s não conseguiu desviar do ataque de %s.%n", passivePlayer.getName(), activePlayer.getName());
        return false;
    }

    @Override
    public double calculateDamage(Player activePlayer, Player passivePlayer, double activeSKillPowerAttack) {
        return (activePlayer.getMainAttribute() * activePlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.originalDefenseValue());
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
    public void receiveDamage(Player activePlayer, Player passivePlayer, double value, Skill skill) {
        if (chanceToEvade(activePlayer, passivePlayer)) return;
        super.receiveDamage(activePlayer, passivePlayer, value, skill);
    }

    @Override
    public void receiveDamage(double value, Player passivePlayer, String effectName) {
        if (chanceToEvade(passivePlayer, passivePlayer)) return;
        super.receiveDamage(value, passivePlayer, effectName);
    }

    @Override
    public void receiveDamage(Trap trap, Player passivePlayer) {
        if (chanceToEvade(trap.getOwnerPlayerObserver(), passivePlayer)) return;
        super.receiveDamage(trap.getDamage(), passivePlayer, trap.getName());
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
    public boolean canAttack(String activePlayerName) {
        return true;
    }

    @Override
    public String toString() {
        String stateName = "Evasion State";
        return "EvasionState{" + stateName + '}';
    }
}
