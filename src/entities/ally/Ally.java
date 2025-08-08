package entities.ally;

import entities.BattleGround;
import entities.character.Character;
import entities.skill.Skill;
import entities.state.State;

public abstract class Ally extends Skill {
    private State state;

    public Ally(String name, String description, int cooldown, State state) {
        super(name, description, null, cooldown);
        this.state = state;
    }

    public boolean isAlive() {
        return this.state.isAlive();
    }

    public State getState() {
        return state;
    }

    @Override
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                 🛡️ PREPARANDO EVOCAÇÃO DO ALIADO: " + this.getName() + "          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔄 " + activePlayer.getName() + " se prepara para invocar o aliado " + this.name + " no campo de batalha!");
        System.out.println("⚔️ O aliado se posiciona, pronto para ajudar na batalha!");

        this.executeSelectedSkill(activePlayer, passivePlayer, battleGround);
        this.skillTypeAction(activePlayer, passivePlayer, battleGround);

        System.out.println(" ❤️ Vida atual do aliado " + this.name + ": " + String.format("%.2f", Math.max(passivePlayer.getLife(), 0)) );
        System.out.println();
    }

    @Override
    public void skillEffectAction(Character activePlayer, Character passivePlayer) {

    }

    public void receiveDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack, Skill skill) {
        this.state.receiveDamage(actionPlayer,
                passivePlayer,
                this.state.calculateDamage(
                        actionPlayer,
                        passivePlayer,
                        activeSKillPowerAttack),
                skill);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        throw new UnsupportedOperationException("This skill must use BattleGround context.");
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        throw new UnsupportedOperationException("This skill must use BattleGround context.");
    }

    public abstract String getIcon();
}
