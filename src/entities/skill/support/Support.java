package entities.skill.support;

import entities.BattleGround;
import entities.character.Character;
import entities.skill.Skill;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Support extends Skill {

    public Support(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ⚡ PREPARANDO HABILIDADE DE SUPORTE: " + this.name + "                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔄 " + activePlayer.getName() + " se prepara para usar uma habilidade de suporte!");
        System.out.println("🤝 O ambiente fica tenso enquanto a habilidade é preparada...");
        System.out.println("✨ Energia e determinação se acumulam para fortalecer os aliados!");

        this.executeSelectedSkill(activePlayer, passivePlayer, battleGround);
        this.skillTypeAction(activePlayer, passivePlayer, battleGround);
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
    }
}
