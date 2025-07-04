package entities.skill.melee;

import entities.character.Character;
import entities.skill.Skill;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe Melee representa uma habilidade de ataque corpo a corpo.
 * Ela estende a classe Attack e implementa as ações específicas de ataque corpo a corpo.
 */
public class Melee extends Attack {
    private static final List<String> playerActionList = Arrays.asList(
            " corre em direção ao inimigo com determinação!",
            " se aproxima do inimigo com um olhar feroz!",
            " avança com um ataque poderoso!",
            " pula em direção ao inimigo com um golpe devastador!",
            " com agilidade, se aproxima do inimigo!",
            " grita e avança com um ataque furioso!"
    );

    private static final List<String> skillActionList = Arrays.asList(
            " ergue sua espada com determinação!",
            " avança com um grito de guerra!",
            " desfere um golpe poderoso!",
            " ataca com precisão mortal!",
            " corre em direção ao seu oponente com olhos flamejantes!",
            " desfere um ataque devastador!",
            " lança um golpe certeiro!",
            " ataca com fúria incontrolável!",
            " desfere um golpe mortal!",
            " ataca com sua espada!",
            " avança com um ataque devastador!",
            " desfere um golpe com toda a sua força!",
            " ataca com um movimento ágil!"
    );

    public Melee(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character player1, Character player2) {
        System.out.println("\n⚔️ " + player1.getName() + " " + this.getSkillAction(playerActionList) + " " + this.getName());
        this.executeSelectedSkill(player1, player2);
    }

    public Melee(String name, String description, String skillAction, int cooldown, int attackPower, boolean special) {
        super(name, description, skillAction, cooldown, attackPower, special);
    }

    public Melee(String name, String description, String skillAction, int cooldown, boolean stunned, boolean special) {
        super(name, description, skillAction, cooldown, stunned, special);
    }

    public static Melee ofHeavyAttack() {
        return new Melee("Golpe Pesado",
                "Ataque com dano físico aumentado.",
                "🌪️ O som do aço corta o vento... ",
                1, 1, false);
    }

    public static Melee ofIunge() {
        return new Melee("Investida",
                "Avança até o inimigo, causa dano e chance de atordoar.",
                "🏃‍♂️ Avança rapidamente em direção ao inimigo, causando dano e tentando atordoá-lo!", 2, false,
                ThreadLocalRandom.current().nextBoolean());
    }

    public static Melee ofBattlefieldWrath() {
        return new Melee("Ira do Campo de Batalha (especial)",
                "Aumenta o dano físico por 3 turnos.",
                "💥Você sente uma fúria incontrolável tomar conta do campo de batalha!", 3, 5, true);
    }

    public static Melee ofHolyBlow() {
        return new Melee("Golpe Sagrado",
                "Ataque com dano extra contra inimigos sombrios.",
                "✨ Uma luz sagrada envolve sua arma ao atingir o alvo.", 1, false);
    }

    public static Skill ofJusticeHammer() {
        return new Melee("Martelo da Justiça (especial)",
                "Golpe poderoso com dano alto e atordoamento.",
                "⚡ O martelo brilha com luz divina... ", 3,
                false, ThreadLocalRandom.current().nextBoolean());
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("⚔️ " + actionPlayer.getName() +
                this.getSkillAction(skillActionList));
    }
}
