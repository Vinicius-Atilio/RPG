package entities.skill.hunter;

import entities.character.Character;
import entities.skill.attack.Attack;

public class PrecisionShot extends Attack {
    protected PrecisionShot(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║        🎯 TÉCNICA: DISPARO PRECISO BANUK ELITE        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("👤 O caçador desacelera a respiração... tudo ao redor silencia.");
        System.out.println("👁️ Seus olhos fixam o ponto fraco do seu alvo — núcleo exposto...");
        System.out.println("🏹 Ele tensiona o arco lentamente... a flecha se alinha com perfeição.");
        System.out.println("⏱️ [Concentração Máxima] — o tempo parece parar por um segundo...");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {

    }

    public static PrecisionShot ofPrecisionShot() {
        return new PrecisionShot("Disparo Preciso",
                "Ataque com alto dano crítico.",
                "🎯 Um tiro certeiro atinge o ponto fraco do inimigo!",
                1, false);
    }
}
