package entities.skill.defense;

import entities.BattleGround;
import entities.character.Character;
import entities.skill.Skill;

public abstract class Defense extends Skill {
    protected int defensePower;

    public Defense() {super();}

    public Defense(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    public Defense(String name, String description, String skillAction, int cooldown, int defensePower) {
        super(name, description, skillAction, cooldown);
        this.defensePower = defensePower;
    }

    @Override
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        🛡️ PREPARANDO HABILIDADE DE DEFESA: " + this.name + "           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔄 " + activePlayer.getName() + " prepara-se para se defender de " + passivePlayer.getName() + "!");
        System.out.println("🛡️ A atmosfera fica tensa enquanto a defesa é preparada...");
        System.out.println();

        this.executeSelectedSkill(activePlayer, passivePlayer);
        this.skillTypeAction(activePlayer, passivePlayer);

        System.out.println("🛡️ Defesa atual de " + activePlayer.getName() + ": " + String.format("%.2f", Math.max(activePlayer.getDefense(), 0)));
        System.out.println();
    }

    @Override
    public void skillEffectAction(Character activePlayer, Character passivePlayer) {

    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        throw new UnsupportedOperationException("This skill does not use BattleGround context.");
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        throw new UnsupportedOperationException("This skill does not use BattleGround context.");
    }
}
