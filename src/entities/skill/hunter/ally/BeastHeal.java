package entities.skill.hunter.ally;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.ally.AllySupport;

public class BeastHeal extends AllySupport {
    public BeastHeal(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Ally ally, Player activePlayer, Player passivePlayer) {
        printSkillBox(" 🐾 ALIADO: CURA DA FERA");
        System.out.println(activePlayer.getName() + " ordena a fera companheira a curar " + passivePlayer.getName() + "!");
        System.out.println("A fera usa suas habilidades naturais para restaurar parte da vida do aliado.");
        System.out.println("💚 " + activePlayer.getName() + " executa a cura da fera com sucesso!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Ally ally, Player activePlayer, Player passivePlayer) {
        printSkillBox(" 🐾 FERA COMPANHEIRA CURA");
        System.out.println(activePlayer.getName() + " ordena a fera companheira a curar " + passivePlayer.getName() + "!");
        System.out.println("A fera usa suas habilidades naturais para restaurar parte da vida do aliado.");
        System.out.println("💚 " + activePlayer.getName() + " executa a cura da fera com sucesso!");
        System.out.println();
        passivePlayer.receiveAllyHeal(ally, this, activePlayer);
        System.out.println();
    }

    @Override
    public double getAllyHeal() {
        return this.state.getAgility();
    }

    public static BeastHeal ofHealTarget() {
        return new BeastHeal("Cura da Fera",
                "Cura seu aliado, restaurando parte de sua vida.",
                "Cura o aliado com as habilidades naturais da fera.",
                0);
    }
}
