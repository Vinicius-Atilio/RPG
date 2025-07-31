package entities.skill.hunter;

import entities.character.Character;
import entities.skill.attack.Attack;

public class ExplosiveTrap extends Attack {

    protected ExplosiveTrap(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║       💣 PREPARAÇÃO: ARMADILHA EXPLOSIVA BANUK       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("👣 O caçador ajoelha silenciosamente no solo congelado...");
        System.out.println("🧰 Ele retira uma carga instável da bolsa e um detonador artesanal.");
        System.out.println("🛠️ Posiciona a carga entre pedras e folhas, conectando os fios com precisão...");
        System.out.println("🔥 Um pequeno LED vermelho pisca no núcleo da bomba... armada com sucesso.");

    }

    @Override
    public void skillTypeAction(Character actionPlayer) {

    }

    public static ExplosiveTrap ofExplosiveTrap() {
        return new ExplosiveTrap("Armadilha Explosiva",
                "Instala armadilha que explode ao ser acionada.",
                "💣 Uma armadilha mortal é colocada no campo de batalha!",
                2, false);
    }
}
