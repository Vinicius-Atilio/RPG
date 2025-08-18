package entities.skill.warrior.ally;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Player;
import entities.observer.BattleObserver;
import entities.skill.Skill;
import entities.state.OriginalState;
import entities.state.State;

import java.util.Arrays;
import java.util.List;

public class WarStandard extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " levanta o estandarte de guerra, inspirando coragem e determinação!",
            " ergue o estandarte, fortalecendo os aliados!",
            " brandindo o estandarte, traz esperança à batalha!",
            " exibe o estandarte com bravura, unindo os aliados!"
    );

    public WarStandard(String name, String description, String skillAction, int cooldown, State state, double invokerPower, double skillMultiplier) {
        super(name, description, skillAction, cooldown, state, invokerPower, skillMultiplier);
    }

    @Override
    public void allyAction(BattleObserver battleGroundObserver) {
        battleGroundObserver.onNotifyAllyAction(this, this);
    }

    public static WarStandard ofWarriorAlly() {
        return new WarStandard("Estandarte de Guerra",
                "Estandarte usado em batalha para fortalecer seu alidado",
                "🏴 Estandarte de Guerra está fortalecendo o guerreiro com sua presença no campo de batalha!.",
                1,
                OriginalState.ofWarStandard(),
                1.1,
                1.1);
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox("🏴 ALIADO: ESTANDARTE DE GUERRA");
        System.out.println(activePlayer.getName() + "Levanta um estandarte com uma bandeira vermelha vibrante!");
        System.out.println(activePlayer.getName() + " segura firmemente o estandarte de guerra!");
        System.out.println("⚔️ " + activePlayer.getName() + " se prepara para posicionar o estandarte de guerra no campo de batalha!");
        System.out.println();
        this.markAllyObserver(activePlayer);
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        this.allyObserver.onAllyUpdateState(this);
        printSkillBox("🏴 ESTANDARTE DE GUERRA ATIVADO");
        System.out.println("✨ " + activePlayer.getName() + this.getAction(actionList));
        System.out.println("💪 Os aliados sentem-se fortalecidos e motivados para a batalha!");
        System.out.println(passivePlayer.getName() + " observa o estandarte com respeito e determinação.");
        System.out.println();
        battleGround.onAllyInvoked(this);
    }


    @Override
    public Skill allySelectSkill() {
        throw new UnsupportedOperationException("O aliado Estandarte de Guerra não possui habilidades selecionáveis.");
    }

    @Override
    public String getIcon() {
        return " 📯";
    }

    @Override
    public double getAllyPower() {
        return 0;
    }

    @Override
    public double getAllyHeal() {
        return 0;
    }

}
