package entities.skill.warrior;

import entities.character.Player;
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

    public BattlefieldWrath(String name, String description, String skillAction, int cooldown, int powerAttack) {
        super(name, description, skillAction, cooldown, powerAttack);
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        printSkillBox("🌟 ESPECIAL SELECIADO: IRA DO CAMPO DE BATALHA TOMBA O EQUILÍBRIO!");
        System.out.println("😡 " + activePlayer.getName() + " crava os pés na terra, soltando um rugido que ecoa pela arena!");
        System.out.println("🌪️ Uma aura vermelha flamejante envolve seu corpo — sua respiração fica pesada...");
        System.out.println("🔥 Sua pele queima com a energia da fúria — você sente o poder crescendo dentro de você!");
        System.out.println(passivePlayer.getName() + " observa, aterrorizado, enquanto a fúria toma conta do campo de batalha!");
        System.out.println("🔺 A fúria o domina! Por 3 turnos, seu poder de ataque será brutalmente amplificado!");
        System.out.println("⚠️ No entanto... sua defesa está reduzida enquanto a fúria consome sua razão!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
        activePlayer.changeStateToWrath();
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        printSkillBox("🌟 HABILIDADE ESPECIAL ATIVADA: IRA DO CAMPO DE BATALHA (ESPECIAL)");
        System.out.println("🔥 " + activePlayer.getName() + " entra em estado de furia!");
        System.out.println("💥 " + activePlayer.getName() + " sente a força do campo de batalha pulsando em suas veias!");
        System.out.println("⚔️ O ataque queima a pele dos seus inimigos!");
        System.out.println("💢 " + this.getAction(skillActionList));
        passivePlayer.receiveSpecialDamage(activePlayer,this.calculateSkillDamage(activePlayer), this);
        System.out.println("🛡️ Efeitos colaterais serão aplicados pelos próximos turnos.");
        System.out.println();
    }

    @Override
    public double calculateSkillDamage(Player activePlayer) {
        return this.powerAttack;
    }

    public static BattlefieldWrath ofBattlefieldWrath() {
        return new BattlefieldWrath("Ira do Campo de Batalha (especial)",
                "Aumenta o dano físico por 3 turnos. Defesa reduzida.",
                "💥 Você sente uma fúria incontrolável tomar conta do campo de batalha!",
                3,
                5);
    }
}
