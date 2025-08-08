package entities.skill.mage;

import entities.character.Character;
import entities.skill.attack.Attack;

import java.util.Arrays;
import java.util.List;

public class ParalyzingIce extends Attack  {

    private static final List<String> playerActionList = Arrays.asList(
            " ergue sua varinha mágica com determinação!",
            " concentra sua energia mágica!",
            " levanta as mãos para o céu, invocando poder arcano!",
            " canaliza a essência mágica do universo!",
            " levanta o cajado mágico, pronto para lançar um feitiço!",
            " fecha os olhos, sentindo a energia mágica fluir!",
            " invoca a magia ancestral com um gesto poderoso!",
            " concentra-se profundamente, preparando um feitiço poderoso!",
            " ergue o grimório, recitando palavras arcanas!",
            " estende as mãos, convocando a magia do elemento!",
            " invoca a força mágica com um grito de poder!"
    );

    private static final List<String> skillActionList = Arrays.asList(
            " conjura um feitiço poderoso!",
            " lança uma magia arcana!",
            " emite uma explosão de energia mágica!",
            " invoca um poder ancestral!",
            " conjura uma tempestade de energia!",
            " libera uma onda de magia destrutiva!",
            " canaliza a essência arcana!",
            " emite um brilho mágico intenso!",
            " conjura uma barreira mágica protetora!",
            " lança um raio de energia pura!",
            " invoca um elemental de fogo!",
            " conjura uma esfera de energia!",
            " emite uma onda de choque mágica!",
            " libera uma rajada de vento mágico!",
            " conjura uma aura de proteção!",
            " lança um feitiço de cura!",
            " invoca uma tempestade de gelo!",
            " conjura uma explosão de luz!",
            " emite uma onda de energia arcana!",
            " libera uma magia de teletransporte!",
            " conjura uma barreira de energia!"
    );

    protected ParalyzingIce(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║            ❄️ MAGIA: GELO PARALISANTE LANÇADO PELO MAGO                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🧙‍♂️ " + activePlayer.getName() + " ergue as mãos, canalizando energia gélida...");
        System.out.println("🌬️ O ar ao redor se torna cortante, cristais de gelo começam a se formar.");
        System.out.println("❄️ Um raio de frio intenso atinge o inimigo, paralisando seus movimentos!");
        System.out.println("🐢 A velocidade do alvo é drasticamente reduzida.");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                  HABILIDADE ATIVADA: GELO PARALISANTE                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("❄️ " + activePlayer.getName() + " conjura um feitiço de gelo paralisante!");
        passivePlayer.changeStateToParalyzed();
        System.out.println("🧊 O inimigo sente o frio tomar conta de seu corpo, ficando mais lento.");
        System.out.println();
    }

    public static ParalyzingIce ofParalyzingIce() {
        return new ParalyzingIce("Gelo Paralizante",
                "Causa dano e reduz velocidade do inimigo.",
                "❄️ O frio intenso congela o inimigo, reduzindo sua velocidade!",
                2);
    }
}
