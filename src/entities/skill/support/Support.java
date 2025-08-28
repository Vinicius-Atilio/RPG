package entities.skill.support;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Player;
import entities.observer.Observer;
import entities.observer.Subject;
import entities.skill.Skill;

public abstract class Support extends Skill {

    public Support(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void prepareSkillToExecute(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║      ⚡ PREPARANDO HABILIDADE DE SUPORTE: " + this.name + "     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔄 " + activePlayer.getName() + " se prepara para usar uma habilidade de suporte!");
        System.out.println("🤝 O ambiente fica tenso enquanto a habilidade é preparada...");
        System.out.println("✨ Energia e determinação se acumulam para fortalecer os aliados!");

        this.executeSelectedSkill(activePlayer, passivePlayer, battleGround);
        this.skillTypeAction(activePlayer, passivePlayer, battleGround);
    }

    @Override
    public void skillEffectAction(Player activePlayer, Player passivePlayer) {
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
}
