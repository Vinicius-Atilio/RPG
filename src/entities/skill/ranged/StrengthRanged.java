package entities.skill.ranged;

import entities.skill.attack.Attack;
import entities.character.Character;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StrengthRanged extends Attack {
    private static final List<String> playerActionList = Arrays.asList(
            " ergue seu arco com determinação!",
            " concentra sua força para um tiro certeiro!",
            " mira com precisão mortal!",
            " prepara sua besta para um ataque devastador!",
            " ajusta a mira, pronto para disparar!",
            " puxa a corda do arco com firmeza!",
            " posiciona-se estrategicamente, pronto para atacar!",
            " respira fundo, focando na flecha!",
            " levanta o arco, mirando no alvo!",
            " ajusta a flecha, pronto para disparar!"
    );

    private static final List<String> skillActionList = Arrays.asList(
            " dispara uma flecha certeira!",
            " lança uma flecha explosiva!",
            " atira com precisão mortal!",
            " dispara um tiro certeiro!",
            " lança uma flecha venenosa!",
            " atira com sua besta!",
            " dispara um tiro preciso!",
            " lança uma armadilha explosiva!",
            " dispara uma flecha incendiária!",
            " atira uma flecha congelante!",
            " dispara uma flecha de eletricidade!",
            " lança uma flecha de luz!",
            " dispara uma flecha de sombra!",
            " atira uma flecha de energia!",
            " dispara uma flecha de vento!",
            " lança uma flecha de terra!",
            " dispara uma flecha de fogo!"
    );

    public StrengthRanged(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {
        System.out.println("\n🏹 " + activePlayer.getName() + " " + this.getAction(playerActionList) + " " + this.getName());
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    public static StrengthRanged ofPrecisionShot() {
        return new StrengthRanged("Disparo Preciso",
                "Ataque com alto dano crítico.",
                "🎯 Um tiro certeiro atinge o ponto fraco do inimigo!",
                1, false);
    }

    public static StrengthRanged ofExplosiveTrap() {
        return new StrengthRanged("Armadilha Explosiva",
                "Instala armadilha que explode ao ser acionada.",
                "💣 Uma armadilha mortal é colocada no campo de batalha!",
                2, false);
    }

    public static StrengthRanged ofArrowRain() {
        return new StrengthRanged("Chuva de Flechas (especial)",
                "Dispara várias flechas em área com chance de sangramento.",
                "🌧️ Uma chuva de flechas cai sobre o inimigo, causando dano em área!",
                3, true);
    }

//    public static StrengthRanged ofPoisonArrow() {
//        return new StrengthRanged("Flecha Envenenada",
//                "Aplica veneno que causa dano contínuo por 2 turnos.",
//                "☠️ A flecha envenenada atinge o inimigo, causando dano ao longo do tempo!",
//                2, false);
//    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🏹 " + actionPlayer.getName() + skillActionList.get(ThreadLocalRandom.current().nextInt(skillActionList.size())));
    }
}
