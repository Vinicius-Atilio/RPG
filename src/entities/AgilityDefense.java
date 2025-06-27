package entities;

import java.util.Arrays;
import java.util.List;

public class AgilityDefense extends Defense {

    private static final List<String> actionList = Arrays.asList(
            " ativa uma esquiva ágil!",
            " se esquiva habilidosamente, tornando-se mais difícil de atingir!",
            " realiza um movimento ágil, evitando ataques inimigos!",
            " executa uma manobra evasiva, esquivando-se de ataques!",
            " usa sua agilidade para evitar ataques inimigos!",
            " realiza um salto ágil, esquivando-se de ataques!"
    );

    public AgilityDefense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }


    public static AgilityDefense ofEvasion() {
        return new AgilityDefense("Evasão",
                "Aumenta a esquiva por 1 turnos (não causa dano).",
                "🏃‍♂️ Você se esquiva habilidosamente, tornando-se mais difícil de atingir.",
                2, false);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🏃‍♂️ " + actionPlayer.getName() + this.getSkillAction(actionList));
    }
}
