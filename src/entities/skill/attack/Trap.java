package entities.skill.attack;

import entities.ally.Ally;
import entities.character.Player;
import entities.BattleGround;
import entities.observer.BattleObserver;
import entities.skill.Skill;

import java.util.Random;

public abstract class Trap extends Skill {
    protected int damage;
    protected Random random = new Random();
    protected BattleObserver targetObserver;

    protected Trap(String name, String description, String skillAction,
                   int cooldown, int damage) {
        super(name, description, skillAction, cooldown);
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

    protected void markTargetObserver(BattleObserver targetObserver) {
        this.targetObserver = targetObserver;
    }

    public boolean canBeExplode() {
        if (this.targetObserver == null) {
            return false;
        }

        return this.random.nextInt(101) >= 1;
    }

    public void applyDamage() {
        this.targetObserver.onTrapActivated(this);
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        validateContext();
    }
    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        validateContext();
    }
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
}
