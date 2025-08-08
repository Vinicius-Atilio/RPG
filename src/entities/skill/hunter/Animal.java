package entities.skill.hunter;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Character;
import entities.state.OriginalState;
import entities.state.State;

import java.util.Arrays;
import java.util.List;

public class Animal extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " surge ferozmente na batalha pronto para atacar!",
            " corre rapidamente para ajudar seu mestre!!",
            " salta alto e ataca com garras afiadas!",
            " ruge ferozmente antes de atacar!"
    );

    public Animal(String name, String description, int cooldown, State state) {
        super(name, description, cooldown, state);
    }

    public static Animal ofBeast() {
        return new Animal("Fera Companheira",
                "Um aliado animal que traz força e agilidade para a batalha",
                4,
                OriginalState.ofBeast());
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                             🐺 PREPARANDO FERA COMPANHEIRA                                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println(activePlayer.getName() + " chama sua fera companheira para a batalha!");
        System.out.println(activePlayer.getName() + " assobia e a fera aparece rapidamente ao seu lado!");
        System.out.println("⚔️ " + activePlayer.getName() + " se prepara para posicionar sua fera companheira no campo de batalha!");
        System.out.println();
        activePlayer.onAllyInvoked(this);
    }

    @Override
    public String getIcon() {
        return " 🐺 ";
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                      🐾 FERA COMPANHEIRA ATIVADA                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🐾 O aliado de " + activePlayer.getName() + this.getAction(actionList));
        System.out.println("💪 A fera companheira está pronta para atacar e ajudar na batalha!");
        System.out.println();
        battleGround.onAllyInvoked(this);
    }
}
