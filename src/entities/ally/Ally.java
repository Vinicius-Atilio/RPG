package entities.ally;

import entities.BattleGround;
import entities.character.Player;
import entities.observer.BattleObserver;
import entities.skill.Skill;
import entities.state.State;

import java.util.List;

public abstract class Ally extends Skill {
    protected State state;
    protected List<Skill> skills;
    protected BattleObserver invokerObserver; // Observador que invoca o aliado
    protected BattleObserver enemyObserver; // Observador que invoca o inimigo
    protected double invokerPower; // Quanto do poder do invocador influencia
    protected double skillMultiplier; // Quanto a habilidade influencia

    public Ally(String name, String description, String skillAction, int cooldown, State state, double invokerPower, double skillMultiplier) {
        super(name, description, skillAction, cooldown);
        this.invokerPower = invokerPower;
        this.skillMultiplier = skillMultiplier;
        this.state = state;
    }

    public Ally(String name, String description, String skillAction, int cooldown, State state, List<Skill> skills, double invokerPower, double skillMultiplier) {
        super(name, description, skillAction, cooldown);
        this.skills = skills;
        this.invokerPower = invokerPower;
        this.skillMultiplier = skillMultiplier;
        this.state = state;
    }

    public Ally(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void prepareSkillToExecute(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox("🛡️ PREPARANDO EVOCAÇÃO DO ALIADO: " + this.name);
        System.out.println("🔄 " + activePlayer.getName() + " se prepara para invocar o aliado " + this.name + " no campo de batalha!");
        System.out.println("⚔️ O aliado se posiciona, pronto para ajudar na batalha!");

        this.executeSelectedSkill(activePlayer, passivePlayer, battleGround);
        this.skillTypeAction(activePlayer, passivePlayer, battleGround);
        battleGround.addAlly(this);
        System.out.println(" ❤️ Vida atual do aliado " + this.name + ": " + String.format("%.2f", Math.max(passivePlayer.getLife(), 0)) );
        System.out.println();
    }

    @Override
    public void skillEffectAction(Player activePlayer, Player passivePlayer) {
        System.out.println("✨ " + activePlayer.getName() + " teve ajuda de " + this.name + " na batalha!");
        System.out.println("💪 Com a ajuda do aliado, " + activePlayer.getName() + " sente-se mais confiante e determinado!");
        System.out.println();
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        validateContext();
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        validateContext();
    }

    // OS METODOS ABAIXO DEVEM SER IMPLEMENTADOS NAS CLASSES FILHAS DA HABILIDADE DO ALIADO
    @Override
    public void prepareSkillToExecute(Ally ally, BattleObserver allyObserver, BattleObserver battleGroundObserver) {
        validateContext();
    }

    @Override
    public void prepareSkillToExecute(Ally ally, BattleObserver allyObserver, BattleObserver enemyObserver, BattleObserver battleGroundObserver) {
        validateContext();
    }

    @Override
    public void executeSelectedSkill(Ally ally, Player activePlayer, Player passivePlayer) {
        validateContext();
    }

    @Override
    public void skillTypeAction(Ally ally, Player activePlayer, Player passivePlayer) {
        validateContext();
    }

    @Override
    public void skillEffectAction(Ally ally, Player activePlayer, Player passivePlayer) {
        validateContext();
    }
    // OS METODOS ACIMA DEVEM SER IMPLEMENTADOS NAS CLASSES FILHAS DA HABILIDADE DO ALIADO

    public abstract void contract(BattleObserver invokerObserver, BattleObserver enemyObserver);
    public abstract void doAction(BattleObserver battleGroundObserver);
    public abstract Skill allySelectSkill();
    public abstract String getIcon();
    public abstract double getAllyPower();
    public abstract double getAllyHeal();

    public boolean isAlive() {
        return this.state.isAlive();
    }

    public State getState() {
        return state;
    }

    public double getLife() {
        return this.state.getLife();
    }

    public double getInvokerPower() {
        return invokerPower;
    }

    public double getSkillMultiplier() {
        return skillMultiplier;
    }

    public void receiveDamage(Player actionPlayer, Player passivePlayer, double activeSKillPowerAttack, Skill skill) {
        this.state.receiveDamage(actionPlayer,
                passivePlayer,
                this.state.calculateDamage(
                        actionPlayer,
                        passivePlayer,
                        activeSKillPowerAttack),
                skill);
    }
}
