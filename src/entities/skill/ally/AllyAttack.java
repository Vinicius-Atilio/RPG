package entities.skill.ally;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Character;
import entities.observer.BattleObserver;
import entities.skill.Skill;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AllyAttack extends Ally {
    protected AllyAttack(String name, String description, String skillAction,
                     int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void prepareSkillToExecute(Ally ally, BattleObserver allyObserver, BattleObserver enemyObserver, BattleObserver battleGroundObserver) {
        printSkillBox("🛡️ PREPARANDO ATAQUE DO ALIADO: " + this.name);
        System.out.println("🔄 " + ally.getName() + " se prepara para atacar com " + this.name + " no campo de batalha!");

        this.executeSelectedSkill(ally, allyObserver.getObserver(), enemyObserver.getObserver());
        allyObserver.onAllyAttack(ally);
        enemyObserver.onReceiveAllyAttack(ally, this);
        this.skillTypeAction(ally, allyObserver.getObserver(), enemyObserver.getObserver());
        battleGroundObserver.onNotifyAllyAction(ally, this);
        System.out.println();
    }

    @Override
    public void skillEffectAction(Ally ally, Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("💢 " + activePlayer.getName() + " " + voiceActionList.get(ThreadLocalRandom.current().nextInt(voiceActionList.size())));
        System.out.println("💥 " + activePlayer.getName() + " " + hitActionList.get(ThreadLocalRandom.current().nextInt(hitActionList.size())));
        System.out.println(passivePlayer.getName() +  hitEffectList.get(ThreadLocalRandom.current().nextInt(hitEffectList.size())));
        System.out.println(" 😡🔪 " + passivePlayer.getName() + " " + answerVegeanceList.get(ThreadLocalRandom.current().nextInt(answerVegeanceList.size())));
        System.out.println();
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        validateContext();
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        validateContext();
    }

    @Override
    public void allyAction(BattleObserver battleGroundObserver) {

    }

    @Override
    public Skill allySelectSkill() {
        return null;
    }

    @Override
    public String getIcon() {
        return "";
    }

    @Override
    public double getAllyHeal() {
        return 0;
    }
}
