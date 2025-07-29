package entities.state;

import entities.character.Character;
import entities.effect.StatusEffect;
import entities.skill.Skill;

import java.util.concurrent.ThreadLocalRandom;

public class EvasionState extends State {

    public EvasionState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(life, strength, intelligence, agility, vigor, mana, defense);
    }

    public EvasionState(String name, State state) {
        super(name, state);
    }

    public static State of(State state) {
        return new EvasionState(
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
    public double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack) {
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.originalDefenseValue());
    }

    @Override
    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill, StatusEffect statusEffect) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.out.printf("😱 %s conseguiu desviar do ataque de %s!%n", passivePlayer.getName(), actionPlayer.getName());
            return;
        }

        this.life -= value;
        skill.skillAction(actionPlayer, passivePlayer);
        System.out.printf("😤 %s recebeu o dano de %.2f de %s!%n", passivePlayer.getName(), value, actionPlayer.getName());
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
        return true;
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
}
