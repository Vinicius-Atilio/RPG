package entities.defense;

import entities.character.Character;

import java.util.Arrays;
import java.util.List;

public class MagicDefense extends Defense {

    private static final List<String> playerActionList = Arrays.asList(
            " se abaixa e preparando uma barreira mágica de proteção! para se proteger de ",
            " ergue suas mãos, concentrando-se em uma barreira mágica! para evitar o ataque de ",
            " seus olhos brilham enquanto se prepara para conjurar uma barreira mágica! para absorver o ataque de ",
            " começa a canalizar energia mágica para criar uma barreira protetora! para se defender de "
    );


    private static final List<String> skillActionList = Arrays.asList(
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

    @Override
    public void prepareSkillToAttack(Character player1, Character player2) {
        System.out.println("\n🔮 " + player1.getName() + " " + this.getAction(playerActionList) + " " + this.getName());
        this.executeSelectedSkill(player1, player2);
    }

    public static MagicDefense ofArcaneBarrier() {
        return new MagicDefense("Barreira Arcana",
                "Cria escudo mágico (não causa dano).",
                "🔮 Uma barreira mágica envolve você, protegendo contra ataques.",
                2, false);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🔮 " + actionPlayer.getName() + this.getAction(skillActionList));
    }

}
