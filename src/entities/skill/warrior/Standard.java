package entities.skill.warrior;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Character;
import entities.state.OriginalState;
import entities.state.State;

import java.util.Arrays;
import java.util.List;

public class Standard extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " levanta o estandarte de guerra, inspirando coragem e determinação!",
            " ergue o estandarte, fortalecendo os aliados!",
            " brandindo o estandarte, traz esperança à batalha!",
            " exibe o estandarte com bravura, unindo os aliados!"
    );

    public Standard(String name, String description, int cooldown, State state) {
        super(name, description, cooldown, state);
    }

    @Override
    public String getIcon() {
        return " 📯";
    }

    public static Standard ofWar() {
        return new Standard("Estandarte de Guerra",
                "Estandarte usado em batalha para fortalecer seu alidado",
                4,
                OriginalState.ofWarStandard());
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                 🏴 ALIADO: ESTANDARTE DE GUERRA                                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println(activePlayer.getName() + "Levanta um estandarte com uma bandeira vermelha vibrante!");
        System.out.println(activePlayer.getName() + " segura firmemente o estandarte de guerra!");
        System.out.println("⚔️ " + activePlayer.getName() + " se prepara para posicionar o estandarte de guerra no campo de batalha!");
        System.out.println();
        activePlayer.onAllyInvoked(this);
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        activePlayer.buffStateBy(this);
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                   🏴 ESTANDARTE DE GUERRA ATIVADO                                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✨ " + activePlayer.getName() + this.getAction(actionList));
        System.out.println("💪 Os aliados sentem-se fortalecidos e motivados para a batalha!");
        System.out.println(passivePlayer.getName() + " observa o estandarte com respeito e determinação.");
        System.out.println();
        battleGround.onAllyInvoked(this);
    }


}
