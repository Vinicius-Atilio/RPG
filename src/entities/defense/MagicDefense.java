package entities.defense;

import entities.character.Character;

import java.util.Arrays;
import java.util.List;

public class MagicDefense extends Defense {

    private static final List<String> actionList = Arrays.asList(
            " ativa uma barreira mágica de proteção!",
            " conjura um escudo mágico para bloquear ataques!",
            " invoca uma aura mágica defensiva!",
            " emite magia de proteção!",
            " fortalece sua defesa com magia!",
            " cria um campo mágico de defesa!",
            " conjura uma barreira mágica!"
    );


    public MagicDefense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    public static MagicDefense ofArcaneBarrier() {
        return new MagicDefense("Barreira Arcana",
                "Cria escudo mágico (não causa dano).",
                "🔮 Uma barreira mágica envolve você, protegendo contra ataques.",
                2, false);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🔮 " + actionPlayer.getName() + this.getSkillAction(actionList));
    }

}
