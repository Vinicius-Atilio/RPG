package entities.skill.hunter.ally;

import entities.ally.Ally;
import entities.character.Character;
import entities.skill.ally.AllyAttack;

public class BeastAttack extends AllyAttack {
    protected BeastAttack(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Ally ally, Character activePlayer, Character passivePlayer) {
        printSkillBox("🐾 ALIADO: ATAQUE DA FERA");
        System.out.println(activePlayer.getName() + " ordena a fera companheira a atacar " + passivePlayer.getName() + "!");
        System.out.println("A fera salta sobre o inimigo, dilacerando-o com suas garras afiadas!");
        System.out.println("⚔️ " + activePlayer.getName() + " executa o ataque da fera com precisão!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Ally ally, Character activePlayer, Character passivePlayer) {
        printSkillBox("🐾 FERA COMPANHEIRA ATACA");
        System.out.println(activePlayer.getName() + " ordena a fera companheira a atacar " + passivePlayer.getName() + "!");
        System.out.println("A fera salta sobre o " + passivePlayer.getName() + ", dilacerando-o com suas garras afiadas!");
        System.out.println("⚔️ " + activePlayer.getName() + " executa o ataque da fera com precisão!");
        System.out.println();
        passivePlayer.receiveAllyDamage(ally, this, activePlayer, passivePlayer);
        System.out.println();
    }

    @Override
    public double getAllyPower() {
        return this.state.getStrength();
    }

    public static BeastAttack ofAttackTarget() {
        return new BeastAttack(
                "Ataque da Fera",
                "Fera companheira ataca o alvo selecionado",
                "Fera salta sobre o inimigo dilacerando-o",
                0
        );
    }
}
