package entities.skill.warrior;

import entities.BattleGround;
import entities.character.Character;
import entities.skill.attack.Attack;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Lunge extends Attack {
    private static final List<String> skillActionList = Arrays.asList(
            "🏃‍♂️ Como um raio cortando os céus, o guerreiro se lança contra o inimigo com brutalidade!",
            "💨 O chão estremece quando ele dispara em linha reta, como um touro em fúria.",
            "⚡ Seu corpo vira uma flecha viva — nada pode deter o avanço selvagem!",
            "💥 O impacto reverbera como o trovão após o relâmpago. O inimigo cambaleia!",
            "🔥 Com olhos em chamas, ele se projeta em velocidade inumana, rompendo defesas com pura força!",
            "💢 A fúria guia seus passos — cada metro cruzado é uma sentença para quem estiver no caminho.",
            "🌪️ A investida é tão rápida que o inimigo nem teve tempo de reagir!",
            "🚀 Ele avança como se lançado por uma catapulta ancestral, mirando o coração do inimigo!",
            "💀 O impacto faz o inimigo ver estrelas — a dor é instantânea, o medo, inevitável.",
            "🧭 O guerreiro desaparece em um borrão, e só se ouve o estrondo do corpo se chocando com o alvo!"
    );

    public Lunge(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                               🚨 HABILIDADE: INVESTIDA RELÂMPAGO                                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🏃‍♂️ " + activePlayer.getName() + " abaixa a postura e se lança com fúria sobre " + passivePlayer.getName() + "!");
        System.out.println("💨 A poeira se levanta enquanto ele rasga o campo em velocidade absurda!");
        System.out.println("💥 O Impacto no corpo e da lâmina estremecera o alvo!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║            ⚡ HABILIDADE: INVESTIDA RELÂMPAGO EXECUTADA PELO GUERREIRO             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🏃‍♂️ " + activePlayer.getName() + " abaixa a postura e dispara como um trovão em direção ao inimigo!");
        System.out.println("💢 " + this.getAction(skillActionList));
        System.out.println("💨 O chão treme sob seus pés enquanto ele corta o campo em velocidade feroz!");
        System.out.println("💥 O impacto do avanço sacode tudo ao redor!");
        System.out.println("🔁 Há uma chance de que o inimigo fique completamente atordoado pela força do golpe!");
        System.out.println();

        if (ThreadLocalRandom.current().nextBoolean()) {
            passivePlayer.changeStateToStunned();
            System.out.println("⚡ " + passivePlayer.getName() + " FOI ATORDOADO PELA FORÇA DO IMPACTO!!");
            return;
        }

        System.out.println("❌ " + passivePlayer.getName() + " conseguiu resistir ao impacto e não foi atordoado.");
        System.out.println();
    }

    public static Lunge ofLunge() {
        return new Lunge("Investida",
                "Avança até o inimigo, causa dano e chance de atordoar.",
                "🏃‍♂️ Avança rapidamente em direção ao inimigo, causando dano e tentando atordoá-lo!",
                2
        );
    }
}
