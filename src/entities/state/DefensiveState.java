package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public class DefensiveState extends State {
    private double reducedValue = 0.5;

    public DefensiveState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int stateDuration) {
        super("Estado Defensivo" ,life, strength, intelligence, agility, vigor, mana, defense, stateDuration);
    }

    public static State of(State state) {
        return new DefensiveState(
                state.getLife(),
                state.getStrength(),
                state.getIntelligence(),
                state.getAgility(),
                state.getVigor(),
                state.getMana(),
                state.getDefense() * 2, // Aumenta a defesa
                1
        );
    }

    @Override
    public double calculateDamage(Player activePlayer, Player passivePlayer, int activeSKillPowerAttack) {
        System.out.println("😤 " + passivePlayer.getName() + " está em estado defensivo e não efetua dano!");
        return 0;
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
       if (value > 0) {
           System.out.printf("😤 %s está em estado defensivo e reduz o dano recebido de %.2f para %.2f!%n",
                   passivePlayer.getName(), value, value * 0.5);
           this.life -= value * this.reducedValue;
           skill.skillEffectAction(activePlayer, passivePlayer);
           System.out.printf("😤 %s recebeu o dano reduzido de %.2f de %s!%n", passivePlayer.getName(), value * 0.5, activePlayer.getName());
           return;
        }

        System.out.println("😱 " + passivePlayer.getName() + " conseguiu se defender do ataque de " + activePlayer.getName() + "!");
    }

    @Override
    public void receiveDamage(double value, Player passivePlayer, String effectName) {
        this.life -= value * this.reducedValue;
        System.out.printf("😤 %s está em estado defensivo e reduz o dano recebido de %.2f para %.2f devido ao efeito %s! Vida atual: %.2f%n",
                passivePlayer.getName(), value, reducedValue, effectName, this.life);
    }

    @Override
    public void receiveDamage(Trap trap, Player passivePlayer) {

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
    public void receiveHeal(double value) {

    }

    @Override
    public boolean canAttack(String activePlayerName) {
        System.out.println("⚠️ " + activePlayerName + " está em estado defensivo e não pode atacar!");
        return false;
    }
}
