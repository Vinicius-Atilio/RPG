package entities.skill.paladin;

import entities.BattleGround;
import entities.character.Player;
import entities.skill.defense.Defense;

import java.util.Arrays;
import java.util.List;

public class DivineShield extends Defense {
    private static final List<String> actionList = Arrays.asList(
            " ativa uma barreira divina de proteção!",
            " invoca um escudo celestial para se proteger!",
            " envolve-se em uma aura sagrada de defesa!",
            " ergue um escudo de luz divina para bloquear ataques!",
            " convoca a proteção dos deuses para resistir aos ataques!",
            " emite um brilho sagrado que repele os inimigos!",
            "usa a luz sagrada para proteger a si ou a um aliado!"
    );

    public DivineShield(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                            HABILIDADE: ESCUDO DIVINO                                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✨ " + activePlayer.getName() + " ergue as mãos, invocando uma luz sagrada!");
        System.out.println("\n🛡️ " + activePlayer.getName() + " " + this.getAction(actionList) + this.getName());
        System.out.println("🛡️ Um escudo divino envolve você ou um aliado, concedendo proteção por 2 turnos.");
        System.out.println("⚠️ O escudo não causa dano, mas protege contra ataques inimigos.");
        System.out.println(passivePlayer.getName() + " observa o a defesa de seu inimigo aumentar.");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        activePlayer.changeStateToDefensive();
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        🛡️ HABILIDADE ATIVADA: ESCUDO DIVINO                                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✨ " + activePlayer.getName() + " invoca um escudo divino para se proteger!");
        System.out.println("🛡️ O escudo brilha intensamente, refletindo a luz sagrada.");
        System.out.println();
    }

    public static DivineShield ofDivineShield() {
        return new DivineShield("Escudo Divino",
                "Protege a si ou um aliado por 2 turnos (não causa dano).",
                "✨ Um brilho sagrado envolve você ou um aliado, concedendo proteção.",
                2, false);
    }
}
