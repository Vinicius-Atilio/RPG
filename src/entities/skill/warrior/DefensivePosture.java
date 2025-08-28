package entities.skill.warrior;

import entities.character.Player;
import entities.skill.defense.Defense;

public class DefensivePosture extends Defense {
    protected DefensivePosture(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        printSkillBox("⏳ SE PREPARA PARA ATIVAR POSTURA DEFENSIVA 🛡️");
        System.out.println("🧭 " + activePlayer.getName() + " fixa o olhar em seu alvo...");
        System.out.println("📣 Você sente que algo poderoso está por vir!");
        System.out.println("🔋 A energia ao redor começa a se concentrar...");
        System.out.println("⚠️ " + this.name + " está sendo preparada. No próximo turno, a ação será devastadora!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        activePlayer.changeStateToDefensive();
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        printSkillBox("🛡️ HABILIDADE ATIVADA: POSTURA DEFENSIVA");
        System.out.println("🧍 " + activePlayer.getName() + " firma os pés no chão com autoridade.");
        System.out.println("🛡️ Ele ergue seu escudo e se posiciona com precisão inabalável.");
        System.out.println("🔔 Tudo ao redor parece desacelerar... ele se torna um muro intransponível.");
        System.out.println("📣 Nos próximos 2 turnos, qualquer ataque recebido será drasticamente reduzido!");
        System.out.println();
    }

    public static DefensivePosture ofDefensivePosture() {
        return new DefensivePosture("Postura Defensiva",
                "Reduz o dano recebido por 2 turnos (não causa dano).",
                "🛡️ Você assume uma postura defensiva, preparando-se para bloquear ataques.",
                2);
    }
}
