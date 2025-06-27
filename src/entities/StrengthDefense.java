package entities;

import java.util.Arrays;
import java.util.List;

public class StrengthDefense extends Defense {

    private static final List<String> actionList = Arrays.asList(
            " assume uma postura defensiva!",
            " ergue seu escudo para bloquear o ataque!",
            " prepara-se para absorver o próximo golpe!",
            " ativa uma barreira mágica de proteção!",
            " assume uma posição firme e resistente!",
            " fortalece sua defesa com determinação!"
    );

    public StrengthDefense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    public static StrengthDefense ofDefensivePosture() {
        return new StrengthDefense("Postura Defensiva",
                "Reduz o dano recebido por 2 turnos (não causa dano).",
                "🛡️ Você assume uma postura defensiva, preparando-se para bloquear ataques.",
                2, false);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🛡️ " + actionPlayer.getName() + " se prepara para defender com força!");
    }
}
