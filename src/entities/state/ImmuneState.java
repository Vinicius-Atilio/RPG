package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public class ImmuneState extends State {
    public ImmuneState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense, int turns) {
        super("Estado Imune", life, strength, intelligence, agility, vigor, mana, defense, turns);
    }

    public static ImmuneState of(State state) {
        return new ImmuneState(
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

    public void printImmuneState(Player activePlayer) {
        System.out.println("🛡️ " + activePlayer.getName() + " está sob efeito de imunidade!");
        System.out.println("Nenhum dano será recebido enquanto este estado estiver ativo.");
    }

    @Override
    public void receiveDamage(Player activePlayer, Player passivePlayer, double value, Skill skill) {
        this.printImmuneState(activePlayer);
    }

    @Override
    public void receiveDamage(double value, Player passivePlayer, String effectName) {
        this.printImmuneState(passivePlayer);
    }

    @Override
    public void receiveDamage(Trap trap, Player passivePlayer) {
        System.out.println("💣 A armadilha não causa dano devido ao estado de imunidade!");
    }


    @Override
    public void receiveEffect(String name) {
        System.out.println("🛡️ O efeito " + name + " não afeta o jogador devido ao estado de imunidade.");
    }

    @Override
    public void onDeath(Player player) {
        this.printImmuneState(player);
    }

    @Override
    public boolean isAlive() {
        return this.life > 0;
    }

    @Override
    public double calculateDamage(Player activePlayer, Player passivePlayer, int activeSKillPowerAttack) {
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
        String stateName = "Immune State";
        return "ImmuneState{" + stateName + '}';
    }
}
