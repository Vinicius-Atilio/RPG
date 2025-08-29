package entities.skill.attack;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Player;
import entities.observer.Observer;
import entities.observer.Subject;
import entities.skill.Skill;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Attack extends Skill {
    protected int powerAttack;

    protected Attack(String name, String description, String skillAction,
                     int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    protected Attack(String name, String description, String skillAction,
                     int cooldown, int powerAttack) {
        super(name, description, skillAction, cooldown);
        this.powerAttack = powerAttack;
    }

    @Override
    public void prepareSkillToExecute(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox( "⚔️ PREPARANDO HABILIDADE DE ATAQUE: " + this.name);
        System.out.println("🔄 " + activePlayer.getName() + " se prepara para atacar " + passivePlayer.getName() + " no campo de batalha!");
        System.out.println("🗡️ O ambiente fica tenso enquanto o ataque é preparado...");
        System.out.println("⚡ Energia e determinação se acumulam para o golpe decisivo!");
        this.executeSelectedSkill(activePlayer, passivePlayer);
        this.skillTypeAction(activePlayer, passivePlayer);
        System.out.println();
    }

    @Override
    public void skillEffectAction(Player activePlayer, Player passivePlayer) {
        System.out.println();
        System.out.println("💢" + activePlayer.getName() + " " + voiceActionList.get(ThreadLocalRandom.current().nextInt(voiceActionList.size())));
        System.out.println("💥" + activePlayer.getName() +" " + hitActionList.get(ThreadLocalRandom.current().nextInt(hitActionList.size())));
        System.out.println(passivePlayer.getName() +  hitEffectList.get(ThreadLocalRandom.current().nextInt(hitEffectList.size())));
        System.out.println(" 😡🔪 " + passivePlayer.getName() + " " + answerVegeanceList.get(ThreadLocalRandom.current().nextInt(answerVegeanceList.size())));
        System.out.println();
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        validateContext();
    }
    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
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
    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        validateContext();
    }
    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        validateContext();
    }
    public abstract double calculateSkillDamage(Player activePlayer);
}
