package entities.skill.defense;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Character;
import entities.observer.BattleObserver;
import entities.skill.Skill;

public abstract class Defense extends Skill {
    protected int defensePower;

    public Defense() {super();}

    public Defense(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║      🛡️ PREPARANDO HABILIDADE DE DEFESA: " + this.name + "     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔄 " + activePlayer.getName() + " prepara-se para se defender de " + passivePlayer.getName() + "!");
        System.out.println("🛡️ A atmosfera fica tensa enquanto a defesa é preparada...");
        System.out.println();

        this.executeSelectedSkill(activePlayer, passivePlayer);
        this.skillTypeAction(activePlayer, passivePlayer);

        System.out.println("🛡️ Defesa atual de " + activePlayer.getName() + ": " + activePlayer.getDefense());
        System.out.println();
    }

    @Override
    public void skillEffectAction(Character activePlayer, Character passivePlayer) {
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
    public void prepareSkillToExecute(Ally ally, BattleObserver allyObserver, BattleObserver battleGroundObserver) {
        validateContext();
    }
    @Override
    public void prepareSkillToExecute(Ally ally, BattleObserver allyObserver, BattleObserver enemyObserver, BattleObserver battleGroundObserver) {
        validateContext();
    }
    @Override
    public void executeSelectedSkill(Ally ally, Character activePlayer, Character passivePlayer) {
        validateContext();
    }
    @Override
    public void skillTypeAction(Ally ally, Character activePlayer, Character passivePlayer) {
        validateContext();
    }
    @Override
    public void skillEffectAction(Ally ally, Character activePlayer, Character passivePlayer) {
        validateContext();
    }
    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        validateContext();
    }
    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        validateContext();
    }
}
