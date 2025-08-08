package entities.skill.paladin;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Character;
import entities.state.OriginalState;
import entities.state.State;

import java.util.Arrays;
import java.util.List;

public class HeavenlyGuardian extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " desce dos céus para abençoar seu aliado!",
            " surge com uma luz celestial, trazendo proteção!",
            " voa majestoso, espalhando bênçãos divinas!",
            " irradia uma aura de luz, fortalecendo seu aliado!"
    );

    public HeavenlyGuardian(String name, String description, int cooldown, State state) {
        super(name, description, cooldown, state);
    }

    @Override
    public String getIcon() {
        return " 👼 ";
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                          👼 ALIADO: GUARDIÃO CELESTIAL                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println(activePlayer.getName() + " convoca um guardião celestial para o campo de batalha!");
        System.out.println(activePlayer.getName() + " ergue as mãos, invocando uma presença divina!");
        System.out.println("⚡ " + activePlayer.getName() + " se prepara para abençoar seu aliado com proteção celestial!");
        System.out.println();
        activePlayer.onAllyInvoked(this);
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        activePlayer.buffStateBy(this);
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                   👼 GUARDIÃO CELESTIAL ATIVADO                                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✨ " + activePlayer.getName() + this.getAction(actionList));
        System.out.println("💫 O guardião celestial irradia luz, fortalecendo e protegendo seu aliado!");
        System.out.println();
        battleGround.onAllyInvoked(this);
    }

    public static HeavenlyGuardian ofGuardian() {
        return new HeavenlyGuardian("Guardião Celestial", "Um aliado celestial que traz bênçãos e proteção",
                4, OriginalState.ofGuardian());
    }
}
