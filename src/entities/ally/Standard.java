package entities.ally;

import entities.character.Attribute;
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

    public static Standard ofWar() {
        return new Standard("Estandarte de Guerra",
                "Estandarte usado em batalha para fortalecer seu alidado",
                4,  OriginalState.ofWarStandard());
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {

    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🏴 O aliado " + actionPlayer.getAlly().getName() + this.getAction(actionList));
    }
}
