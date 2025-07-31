package entities.skill.hunter;

import entities.character.Character;
import entities.skill.attack.Attack;

public class ArrowRain extends Attack {
    protected ArrowRain(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║       ☄️ ATAQUE ESPECIAL: CHUVA DE FLECHAS BANUK INVOCADA      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🌫️ O céu escurece enquanto o caçador finca seus pés no solo...");
        System.out.println("🧿 Ele ergue o arco em direção ao céu — seus olhos brilham com foco absoluto.");
        System.out.println("🔁 Em um movimento rápido, ele dispara várias flechas para o alto, uma após a outra...");
        System.out.println("💨 As flechas cortam o vento em uníssono... um som agudo ecoa por toda a área...");
        System.out.println("🔔 Um silêncio sepulcral se instala por um instante...");
        System.out.println("⏳ [TENSÃO] — A chuva se aproxima. Alvos indefesos observam o céu...");
        System.out.println("🌧️ E então, como um dilúvio, as flechas caem do céu, atingindo tudo em seu caminho!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {

    }

    public static ArrowRain ofArrowRain() {
        return new ArrowRain("Chuva de Flechas (especial)",
                "Dispara várias flechas em área com chance de sangramento.",
                "🌧️ Uma chuva de flechas cai sobre o inimigo, causando dano em área!",
                3, true);
    }
}
