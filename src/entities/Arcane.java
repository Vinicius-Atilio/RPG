package entities;

import java.util.Arrays;
import java.util.List;

public class Arcane extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " surge de outra dimensão, conjurando feitiços poderosos!",
            " conjura um feitiço místico, envolvendo o campo de batalha em magia!",
            " entra em batalha com um brilho arcano, lançando feitiços devastadores!"
    );

    public Arcane(String name, String description, int cooldown, Attribute attribute) {
        super(name, description, cooldown, attribute);
    }

    public static Arcane ofMystic() {
        return new Arcane("Arcano Místico",
                "Um aliado que conjura feitiços poderosos para ajudar na batalha",
                4,  Attribute.ofMystic());
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🔮 O aliado " + actionPlayer.getAlly().getName() + this.getSkillAction(actionList));
    }
}
