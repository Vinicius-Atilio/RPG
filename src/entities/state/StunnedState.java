package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;

public class StunnedState extends State {
    public StunnedState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int turns) {
        super("Estado Atordoado", life, strength, intelligence, agility, vigor, mana, defense, turns);
    }

    public static StunnedState of(State state) {
        return new StunnedState(
                state.getLife(),
                state.getStrength(),
                state.getIntelligence(),
                state.getAgility(),
                state.getVigor(),
                state.getMana(),
                state.getDefense(),
                1
        );
    }

    @Override
    public double calculateDamage(Player activePlayer, Player passivePlayer, double activeSKillPowerAttack) {
        System.out.println("⚠️ " + passivePlayer.getName() + " está atordoado e não pode se defender!");
        double damage = activeSKillPowerAttack + (activePlayer.getMainAttribute() * activePlayer.weaponFactor());
        return Math.max(damage, 0);
    }

    @Override
    public double calculateEnemyAllyDamage(Ally ally, Skill skill, Player actionPlayer, Player passivePlayer) {
        System.out.println("⚠️ " + passivePlayer.getName() + " está atordoado e não pode se defender do ataque do aliado.!");
        double damage = (ally.getAllyPower() + skill.getActiveSkillPowerAttack()
                + (actionPlayer.getMainAttribute() * ally.getInvokerPower()) * ally.getSkillMultiplier());

        return Math.max(damage, 0);
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
        this.life += value;
    }

    @Override
    public boolean canAttack(String activePlayerName) {
        System.out.println("⚠️ " + activePlayerName + " está atordoado e não pode atacar!");
        return false;
    }
}
