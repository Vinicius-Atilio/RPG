package entities.skill.mage;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Character;
import entities.state.OriginalState;
import entities.state.State;

import java.util.Arrays;
import java.util.List;

public class Arcane extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " surge de outra dimensão, conjurando feitiços poderosos!",
            " conjura um feitiço místico, envolvendo o campo de batalha em magia!",
            " entra em batalha com um brilho arcano, lançando feitiços devastadores!"
    );

    public Arcane(String name, String description, int cooldown, State state) {
        super(name, description, cooldown, state);
    }

    @Override
    public String getIcon() {
        return " 🔮 ";
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                 🔮 PREPARANDO ARCANO MÍSTICO PARA A BATALHA                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println(activePlayer.getName() + " ergue as mãos, canalizando energia mágica...");
        System.out.println(activePlayer.getName() + " conjura um feitiço poderoso, envolvendo o campo de batalha em magia!");
        System.out.println(passivePlayer.getName() + " observa com espanto enquanto o algo surge de outra dimensão");
        System.out.println();
        activePlayer.onAllyInvoked(this);
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                   🔮 ARCANO MÍSTICO ATIVADO                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✨ " + activePlayer.getName() + this.getAction(actionList));
        System.out.println("💫 O aliado conjura feitiços poderosos, ajudando na batalha!");
        System.out.println("✨ " + activePlayer.getName() + " está pronto para lançar feitiços devastadores!");
        System.out.println("⚡️ " + passivePlayer.getName() + " sente a energia mágica no ar, preparando-se para enfrentar algo misterioso.!");
        System.out.println();
        battleGround.onAllyInvoked(this);
    }


    public static Arcane ofMystic() {
        return new Arcane("Arcano Místico",
                "Um aliado que conjura feitiços poderosos para ajudar na batalha",
                4,
                OriginalState.ofMystic());
    }
}
