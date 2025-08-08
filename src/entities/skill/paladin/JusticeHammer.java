package entities.skill.paladin;

import entities.BattleGround;
import entities.character.Character;
import entities.skill.Skill;
import entities.skill.attack.Attack;
import entities.skill.melee.Melee;

import java.util.concurrent.ThreadLocalRandom;

public class JusticeHammer extends Attack {

    protected JusticeHammer(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      ⚡️ ESPECIAL ATIVADO: MARTELADA DA JUSTIÇA CAI COM FÚRIA DIVINA!              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🛡️ " + activePlayer.getName() + " ergue o martelo aos céus, invocando a luz sagrada...");
        System.out.println("🌩️ Raios dourados envolvem a arma, iluminando todo o campo de batalha.");
        System.out.println("⚡️ Com um grito de fé, o paladino desfere um golpe devastador sobre o inimigo!");
        System.out.println("💫 O impacto é tão forte que pode atordoar o alvo, impedindo sua reação!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                🌟 HABILIDADE ESPECIAL ATIVADA: MARTELADA DA JUSTIÇA (ESPECIAL)                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("⚡️ " + activePlayer.getName() + " invoca o poder divino e desce o martelo com justiça!");
        System.out.println("🌀 O inimigo pode ficar atordoado e vulnerável no próximo turno.");
        System.out.println();
    }

    public static Skill ofJusticeHammer() {
        return new Melee("Martelo da Justiça (especial)",
                "Golpe poderoso com dano alto e atordoamento.",
                "⚡ O martelo brilha com luz divina... ", 3,
                false, ThreadLocalRandom.current().nextBoolean());
    }
}
