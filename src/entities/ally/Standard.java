package entities.ally;

import entities.character.Attribute;
import entities.character.Character;

import java.util.Arrays;
import java.util.List;

public class Standard extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " levanta o estandarte de guerra, inspirando coragem e determinação!",
            " ergue o estandarte, fortalecendo os aliados!",
            " brandindo o estandarte, traz esperança à batalha!",
            " exibe o estandarte com bravura, unindo os aliados!"
    );

    public Standard(String name, String description, int cooldown, Attribute attribute) {
        super(name, description, cooldown, attribute);
    }

    public static Standard ofWar() {
        return new Standard("Estandarte de Guerra",
                "Estandarte usado em batalha para fortalecer seu alidado",
                4,  Attribute.ofWarStandard());
    }

    @Override
    public void prepareSkillToAttack(Character player1, Character player2) {

    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🏴 O aliado " + actionPlayer.getAlly().getName() + this.getAction(actionList));
    }
}
