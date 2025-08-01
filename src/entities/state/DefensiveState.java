package entities.state;

import entities.character.Character;
import entities.skill.Skill;

public class DefensiveState extends State {
    private double reducedValue = 0.5;

    public DefensiveState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int stateDuration) {
        super(life, strength, intelligence, agility, vigor, mana, defense, stateDuration);
    }

    public static State of(State state) {
        return new DefensiveState(
                state.getLife(),
                state.getStrength(),
                state.getIntelligence(),
                state.getAgility(),
                state.getVigor(),
                state.getMana(),
                state.getDefense() + 5,
                1
        );
    }

    @Override
    public double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack) {
        System.out.println("😤 " + passivePlayer.getName() + " está em estado defensivo e não efetua dano!");
        return 0;
    }

    @Override
    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill) {
       if (value > 0) {
           System.out.printf("😤 %s está em estado defensivo e reduz o dano recebido de %.2f para %.2f!%n",
                   passivePlayer.getName(), value, value * 0.5);
           this.life -= value * this.reducedValue;
           skill.skillAction(actionPlayer, passivePlayer);
           System.out.printf("😤 %s recebeu o dano reduzido de %.2f de %s!%n", passivePlayer.getName(), value * 0.5, actionPlayer.getName());
           return;
        }

        System.out.println("😱 " + passivePlayer.getName() + " conseguiu se defender do ataque de " + actionPlayer.getName() + "!");
    }

    @Override
    public void receiveDamage(double value, Character passivePlayer, String effectName) {
        this.life -= value * this.reducedValue;
        System.out.printf("😤 %s está em estado defensivo e reduz o dano recebido de %.2f para %.2f devido ao efeito %s! Vida atual: %.2f%n",
                passivePlayer.getName(), value, reducedValue, effectName, this.life);
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

    @Override
    public void stateCountDown(Character actionPlayer, State state) {
        if (this.stateDuration == 0) {
            System.out.println("😌 O efeito de defensivo terminou. " + actionPlayer.getName() + " está de volta ao estado original.");
            actionPlayer.changeState(state);
        };

        this.stateDuration--;
    }
}
