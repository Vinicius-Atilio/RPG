package entities.skill.warrior;

import entities.character.Character;
import entities.skill.attack.Attack;

import java.util.Arrays;
import java.util.List;

public class BattlefieldWrath extends Attack {
    private static final List<String> skillActionList = Arrays.asList(
            "Você sente uma fúria incontrolável tomar conta do campo de batalha!",
            "Seu sangue ferve... os músculos se contraem, prontos para a destruição!",
            "A raiva explode como um trovão — você não pode mais ser contido!",
            "A lâmina vibra em sua mão — o instinto de guerra domina sua alma!",
            "Um rugido bestial ecoa — o campo de batalha pertence a você agora."
    );

    public BattlefieldWrath(String name, String description, String skillAction, int cooldown, int powerAttack, boolean special) {
        super(name, description, skillAction, cooldown, powerAttack, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║     🔥 ESPECIAL ATIVADO: IRA DO CAMPO DE BATALHA TOMBA O EQUILÍBRIO!      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("😡 " + activePlayer.getName() + " crava os pés na terra, soltando um rugido que ecoa pela arena!");
        System.out.println("🌪️ Uma aura vermelha flamejante envolve seu corpo — sua respiração fica pesada...");
        System.out.println("🔺 A fúria o domina! Por 3 turnos, seu poder de ataque será brutalmente amplificado!");
        System.out.println("⚠️ No entanto... sua defesa está reduzida enquanto a fúria consome sua razão!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║      🌟 HABILIDADE ESPECIAL ATIVADA: IRA DO CAMPO DE BATALHA (ESPECIAL)    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔥 " + actionPlayer.getName() + " entra em estado de pura determinação!");
        System.out.println("💢 " + this.getAction(skillActionList));
        System.out.println("🛡️ Efeitos colaterais serão aplicados pelos próximos turnos.");
        System.out.println();
    }

    public static BattlefieldWrath ofBattlefieldWrath() {
        return new BattlefieldWrath("Ira do Campo de Batalha (especial)",
                "Aumenta o dano físico por 3 turnos. Defesa reduzida.",
                "💥 Você sente uma fúria incontrolável tomar conta do campo de batalha!", 3, 5, true);
    }
}
