package entities.skill.ranged;

import entities.skill.melee.Attack;
import entities.character.Character;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MagicRanged extends Attack {
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

    public MagicRanged(String name, String description, String skillAction,
                          int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character player1, Character player2) {
        System.out.println("\n🔮 " + player1.getName() + " " + this.getAction(playerActionList) + " " + this.getName());
        this.executeSelectedSkill(player1, player2);
    }

    public static MagicRanged ofParalyzingIce() {
        return new MagicRanged("Gelo Paralizante",
                "Causa dano e reduz velocidade do inimigo.",
                "❄️ O frio intenso congela o inimigo, reduzindo sua velocidade!",
                2, false);
    }

    public static MagicRanged ofFireBall() {
        return new MagicRanged("Bola de Fogo",
                "Causa dano mágico em área.",
                "🔥 Uma explosão de chamas incendeia o inimigo!",
                1, false);
    }

    public static MagicRanged ofElementalStorm() {
        return new MagicRanged("Tempestade Elemental (Especial)",
                "Conjura ataque massivo com múltiplos elementos em área, aumenta dano do aliado.",
                "🌪️ Uma tempestade de fogo, gelo e eletricidade atinge o campo de batalha!",
                3, true);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🔮 " + actionPlayer.getName() +  skillActionList.get(ThreadLocalRandom.current().nextInt(skillActionList.size())));
    }
}
