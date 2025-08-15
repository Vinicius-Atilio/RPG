package entities.skill.mage;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Character;
import entities.observer.BattleObserver;
import entities.skill.Skill;
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

    public Arcane(String name, String description, String skillAction, int cooldown, State state, double invokerPower, double skillMultiplier) {
        super(name, description, skillAction, cooldown, state, invokerPower, skillMultiplier);
    }

    @Override
    public void allyAction(BattleObserver battleGroundObserver) {

    }

    @Override
    public Skill allySelectSkill() {
        return null;
    }

    @Override
    public String getIcon() {
        return " 🔮 ";
    }

    @Override
    public double getAllyPower() {
        return 0;
    }

    @Override
    public double getAllyHeal() {
        return 0;
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


    public static Arcane ofMageAlly() {
        return new Arcane("Arcano Místico",
                "Um aliado que conjura feitiços poderosos para ajudar na batalha",
                "✨ Aliado místico esta conjurando feitiços poderosos!",
                4,
                OriginalState.ofMystic(),
                2.0,
                1.5);
    }
}
