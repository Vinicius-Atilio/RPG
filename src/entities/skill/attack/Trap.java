package entities.skill.attack;

import entities.ally.Ally;
import entities.character.Player;
import entities.BattleGround;
import entities.observer.Observer;
import entities.observer.Subject;
import entities.skill.Skill;
import entities.state.TrapState;

import java.util.Random;

// OBSERVAVEL
// ELA AVISA QUE EXPLODIU AO SEU INIMIGO
public abstract class Trap extends Skill {
    protected int damage;
    protected TrapState state;
    protected Random random = new Random();
    protected Subject invokerSUbject;
    protected Subject enemySubject;

    protected Trap(String name, String description, String skillAction, TrapState state,
                   int cooldown, int damage) {
        super(name, description, skillAction, cooldown);
        this.state = state;
        this.damage = damage;
    }

    @Override
    public void prepareSkillToExecute(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox("🪤 PREPARANDO ARMADILHA: " + this.name);
        System.out.println("🔄 " + activePlayer.getName() + " se prepara para instalar a armadilha contra " + passivePlayer.getName() + " no campo de batalha!");
        System.out.println("🪤 O ambiente fica tenso enquanto a armadilha é preparada...");
        System.out.println("⚡ Energia e determinação se acumulam para o golpe decisivo!");

        this.executeSelectedSkill(activePlayer, passivePlayer, battleGround);
        this.skillTypeAction(activePlayer, passivePlayer, battleGround);
        battleGround.addTrap(this);
        System.out.println(" Armadilha está pronta! " + passivePlayer.getName() + " deve ter cuidado ao se mover pelo campo de batalha.");
        System.out.println();
    }

    @Override
    public void skillEffectAction(Player activePlayer, Player passivePlayer) {
        printSkillBox("💣 ARMADILHA: " + this.name + " EXPLODIU!");
        System.out.println("💥 " + activePlayer.getName() + " ACERTA SEU ALVO!");
        System.out.println("A armadilha explode causando " + this.damage + " de dano a " + passivePlayer.getName() + "!");
        System.out.println("⚠️ " + passivePlayer.getName() + " não percebeu o perigo iminente e foi pego de surpresa!");
        System.out.println();
    }

    protected void markTargetObserver(Subject targetObserver) {
        this.invokerSUbject = targetObserver;
    }

    protected void markOwnerObserver(Subject ownerObserver) {
        this.enemySubject = ownerObserver;
    }

    public boolean canBeExplode() {
        if (this.invokerSUbject == null) {
            return false;
        }

        return this.random.nextInt(101) >= 90;
    }

    public void applyDamage() {
        this.invokerSUbject.onTrapActivated(this);
    }

    public int getDamage() {
        return damage;
    }

    public Player getOwnerPlayerObserver() {
        return enemySubject.getObserver();
    }

    public boolean hasBeenExploded() {
        return this.state.hasBeenExploded();
    };

    public abstract void contract(Subject invokerSubject, Subject enemySubject);


    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        validateContext();
    }
    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        validateContext();
    }
    @Override
    public void prepareSkillToExecute(Ally ally, Subject allySubject, Observer battleGroundObserver) {
        validateContext();
    }
    @Override
    public void prepareSkillToExecute(Ally ally, Subject allyObserver, Subject enemyObserver, Observer battleGroundObserver) {
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
}
