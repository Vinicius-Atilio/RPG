package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;

public class WrathState extends  State {
    public WrathState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int turns) {
        super("Estado de ira", life, strength, intelligence, agility, vigor, mana, defense, turns);
    }

    public static WrathState of(State state) {
        return new WrathState(
                state.getLife(),
                (int) (state.getStrength() * 1.3), // aumento de 30% na força
                state.getIntelligence(),
                state.getAgility(),
                state.getVigor(),
                state.getMana(),
                (int) ( state.getDefense()  * 0.2),
                2
        );
    }

    @Override
    public double calculateDamage(Player activePlayer, Player passivePlayer, double activeSKillPowerAttack) {
        double damage = activeSKillPowerAttack + (activePlayer.getMainAttribute() * activePlayer.weaponFactor()) * 1.2;
        return Math.max(damage, 0);
    }

    @Override
    public double calculateEnemyAllyDamage(Ally ally, Skill skill, Player actionPlayer, Player passivePlayer) {
        double damage = (ally.getAllyPower() + skill.getActiveSkillPowerAttack()
                + (actionPlayer.getMainAttribute() * ally.getInvokerPower()) * ally.getSkillMultiplier()) * 1.2;
        return Math.max(damage, 0);
    }

    @Override
    public double calculateAllyHeal(Ally ally, Skill skill, Player activePlayer) {
        return 0;
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
        if (this.stateDuration == 0) {
            System.out.println("😌 O efeito de defensivo terminou. " + actionPlayer.getName() + " está de volta ao estado original.");
            actionPlayer.changeStateToTired(state);
        };

        this.stateDuration--;
    }

    @Override
    public void receiveHeal(double value) {
        this.life += value;
    }

    @Override
    public boolean canAttack(String activePlayerName) {
        return false;
    }
}
