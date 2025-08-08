package entities.skill.defense;

import entities.BattleGround;
import entities.character.Character;

import java.util.Arrays;
import java.util.List;

public class StrengthDefense extends Defense {
    private static final List<String> playerActionList = Arrays.asList(
            " assume uma postura defensiva!",
            " ergue seu escudo para bloquear o ataque!",
            " prepara-se para absorver o próximo golpe!",
            " ativa uma barreira mágica de proteção!",
            " assume uma posição firme e resistente!",
            " fortalece sua defesa com determinação!"
    );

    private static final List<String> skillActionList = Arrays.asList(
            " grita enquanto se defende!",
            " bloqueia o ataque com firmeza!",
            " resiste ao impacto com bravura!",
            " mantém a posição defensiva!",
            " segura o escudo com força, enquanto o inimigo ataca!",
            " responde ao ataque com uma defesa sólida!"
    );

    public StrengthDefense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println("\n🛡️ " + activePlayer.getName() + " " + this.getAction(playerActionList) + " " + this.getName());
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    public static StrengthDefense ofDefensivePosture() {
        return new StrengthDefense("Postura Defensiva",
                "Reduz o dano recebido por 2 turnos (não causa dano).",
                "🛡️ Você assume uma postura defensiva, preparando-se para bloquear ataques.",
                2, false);
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println("🛡️ " + activePlayer.getName() + this.getAction(skillActionList));
    }
}
