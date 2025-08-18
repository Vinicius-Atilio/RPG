package entities.skill.paladin;

import entities.character.Player;
import entities.skill.support.Support;

public class BlessingLight extends Support {
    public BlessingLight(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ✨ HABILIDADE: BÊNÇÃO DA LUZ CONCEDIDA PELO PALADINO           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🛡️ " + activePlayer.getName() + " ergue o símbolo sagrado, murmurando palavras de fé...");
        System.out.println("🌟 Uma luz cálida e reconfortante desce dos céus, envolvendo o aliado ferido.");
        System.out.println("💖 Feridas se fecham e a esperança renasce no coração do companheiro!");
        System.out.println(passivePlayer.getName() + " observa a luz com gratidão e renovada determinação.");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                      HABILIDADE ATIVADA: BÊNÇÃO DA LUZ                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✨ " + activePlayer.getName() + " invoca a luz sagrada para curar um aliado!");
        System.out.println("🕊️ O poder da fé restaura as forças do companheiro.");
        System.out.println();
    }

    public static BlessingLight ofBlessingLight() {
        return new BlessingLight("Bênção da Luz",
                "Cura um aliado (sem causar dano).",
                "✨ Uma luz suave envolve o aliado, curando suas feridas!",
                2);
    }
}
