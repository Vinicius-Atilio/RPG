package entities.skill.hunter;

import entities.character.Player;
import entities.skill.attack.Attack;

public class PrecisionShot extends Attack {
    protected PrecisionShot(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        printSkillBox("🎯 TÉCNICA: DISPARO PRECISO BANUK ELITE");
        System.out.println(activePlayer.getName() + " desacelera a respiração... tudo ao redor silencia.");
        System.out.println("👁️ Seus olhos fixam o ponto fraco do " + passivePlayer.getName() + " — núcleo exposto...");
        System.out.println("🏹 Ele tensiona o arco lentamente... a flecha se alinha com perfeição.");
        System.out.println("⏱️ [Concentração Máxima] — o tempo parece parar por um segundo...");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        printSkillBox("🎯️ HABILIDADE ATIVADA: DISPARO PRECISO (ATAQUE)");
        System.out.println("🏹 " + activePlayer.getName() + " dispara uma flecha certeira!");
        System.out.println(passivePlayer.getName() + " observa enquanto o tempo parece desacelerar...");
        System.out.println(passivePlayer.getName() + " a flecha está vindo em sua direção!!");
        passivePlayer.receiveDamage(activePlayer, this.powerAttack, this);
        System.out.println();
    }

    public static PrecisionShot ofPrecisionShot() {
        return new PrecisionShot("Disparo Preciso",
                "Ataque com alto dano crítico.",
                "🎯 Um tiro certeiro atinge o ponto fraco do inimigo!",
                1);
    }
}
