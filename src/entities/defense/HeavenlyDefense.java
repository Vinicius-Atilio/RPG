package entities.defense;

import entities.character.Character;

import java.util.Arrays;
import java.util.List;

public class HeavenlyDefense extends Defense {

    public HeavenlyDefense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    private static final List<String> actionList = Arrays.asList(
            " ativa uma barreira divina de proteção!",
            " invoca um escudo celestial para se proteger!",
            " envolve-se em uma aura sagrada de defesa!",
            " ergue um escudo de luz divina para bloquear ataques!",
            " convoca a proteção dos deuses para resistir aos ataques!",
            " emite um brilho sagrado que repele os inimigos!",
            "usa a luz sagrada para proteger a si ou a um aliado!"
    );

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("✨ " + actionPlayer.getName() +  this.getSkillAction(actionList));
    }

    public static HeavenlyDefense ofDivineShield() {
        return new HeavenlyDefense("Escudo Divino",
                "Protege a si ou um aliado por 2 turnos (não causa dano).",
                "✨ Um brilho sagrado envolve você ou um aliado, concedendo proteção.",
                2, false);
    }
}
