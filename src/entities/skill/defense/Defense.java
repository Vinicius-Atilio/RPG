package entities.skill.defense;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Player;
import entities.observer.Observer;
import entities.observer.Subject;
import entities.skill.Skill;

public abstract class Defense extends Skill {
    protected int defensePower;

    public Defense() {super();}

    public Defense(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void prepareSkillToExecute(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox("🛡️ PREPARANDO HABILIDADE DE DEFESA: " + this.name);
        System.out.println("🔄 " + activePlayer.getName() + " prepara-se para se defender de " + passivePlayer.getName() + "!");
        System.out.println("🛡️ A atmosfera fica tensa enquanto a defesa é preparada...");
        System.out.println();

        this.executeSelectedSkill(activePlayer, passivePlayer);
        this.skillTypeAction(activePlayer, passivePlayer);

        System.out.println("🛡️ Defesa atual de " + activePlayer.getName() + ": " + activePlayer.getDefense());
        System.out.println();
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
    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        validateContext();
    }
    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        validateContext();
    }
}
