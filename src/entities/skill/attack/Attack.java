package entities.skill.attack;

import entities.BattleGround;
import entities.character.Character;
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
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║        ⚔️ PREPARANDO HABILIDADE DE ATAQUE: " + this.name + "                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔄 " + activePlayer.getName() + " se prepara para atacar " + passivePlayer.getName() + " no campo de batalha!");
        System.out.println("🗡️ O ambiente fica tenso enquanto o ataque é preparado...");
        System.out.println("⚡ Energia e determinação se acumulam para o golpe decisivo!");

        this.executeSelectedSkill(activePlayer, passivePlayer);
        this.skillTypeAction(activePlayer, passivePlayer);

        System.out.println(" ❤️ Vida atual de " + passivePlayer.getName() + ": " + String.format("%.2f", Math.max(passivePlayer.getLife(), 0)) );
        System.out.println();
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        throw new UnsupportedOperationException("This skill does not use BattleGround context.");
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        throw new UnsupportedOperationException("This skill does not use BattleGround context.");
    }

    @Override
    public void skillEffectAction(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("💢" + activePlayer.getName() + " " + voiceActionList.get(ThreadLocalRandom.current().nextInt(voiceActionList.size())));
        System.out.println("💥" + activePlayer.getName() +" " + hitActionList.get(ThreadLocalRandom.current().nextInt(hitActionList.size())));
        System.out.println(passivePlayer.getName() +  hitEffectList.get(ThreadLocalRandom.current().nextInt(hitEffectList.size())));
        System.out.println(" 😡🔪 " + passivePlayer.getName() + " " + answerVegeanceList.get(ThreadLocalRandom.current().nextInt(answerVegeanceList.size())));
        System.out.println();
    }
}
