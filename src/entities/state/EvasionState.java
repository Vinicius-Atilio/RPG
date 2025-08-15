package entities.state;

import entities.ally.Ally;
import entities.character.Character;
import entities.skill.Skill;
import entities.skill.attack.Trap;

import java.util.concurrent.ThreadLocalRandom;

public class EvasionState extends State {

    public EvasionState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int stateDuration) {
        super(life, strength, intelligence, agility, vigor, mana, defense, stateDuration);
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

    @Override
    public double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack) {
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.originalDefenseValue());
    }

    @Override
    public double calculateAllyDamage(Ally ally, Skill skill, Character actionPlayer, Character passivePlayer) {
        return 0;
    }

    @Override
    public double calculateAllyHeal(Ally ally, Skill skill, Character actionPlayer) {
        return 0;
    }

    @Override
    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.out.printf("😱 %s conseguiu desviar do ataque de %s!%n", passivePlayer.getName(), actionPlayer.getName());
            return;
        }

        this.life -= value;
        skill.skillEffectAction(actionPlayer, passivePlayer);
        System.out.printf("😤 %s recebeu o dano de %.2f de %s!%n", passivePlayer.getName(), value, actionPlayer.getName());
    }

    @Override
    public void receiveDamage(double value, Character passivePlayer, String effectName) {

    }

    @Override
    public void receiveDamage(Trap trap) {

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
        if (this.stateDuration == 0) {
            System.out.println("😌 O efeito de evasão terminou. " + actionPlayer.getName() + " está de volta ao estado original.");
            actionPlayer.changeState(state);
        };

        this.stateDuration--;
    }

    @Override
    public void receiveHeal(double value) {

    }

    @Override
    public String toString() {
        String stateName = "Evasion State";
        return "EvasionState{" + stateName + '}';
    }
}
