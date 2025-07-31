package entities.state;

import entities.character.Character;
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
        System.out.println(" defesa do jogador passivo: " + passivePlayer.originalDefenseValue());
        System.out.println(" fator de arma do jogador ativo: " + actionPlayer.weaponFactor());
        System.out.println(" atributo principal do jogador ativo: " + actionPlayer.getMainAttribute());
        System.out.println(" dano do ataque ativo: " + activeSKillPowerAttack);

        System.out.println(" dano calculado: " + (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.originalDefenseValue()));
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.originalDefenseValue());
    }

    @Override
    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.out.printf("😱 %s conseguiu desviar do ataque de %s!%n", passivePlayer.getName(), actionPlayer.getName());
            return;
        }

        this.life -= value;
        skill.skillAction(actionPlayer, passivePlayer);
        System.out.printf("😤 %s recebeu o dano de %.2f de %s!%n", passivePlayer.getName(), value, actionPlayer.getName());
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
    public String toString() {
        String stateName = "Evasion State";
        return "EvasionState{" + stateName + '}';
    }
}
