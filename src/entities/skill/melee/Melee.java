package entities.skill.melee;

import entities.character.Character;
import entities.skill.Skill;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Melee extends Attack {
    private boolean stunned;
    private static final List<String> actionList = Arrays.asList(
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

    public Melee(String name, String description, String skillAction, int cooldown, int attackPower, boolean special) {
        super(name, description, skillAction, cooldown, attackPower, special);
    }

    public Melee(String name, String description, String skillAction, int cooldown, boolean special, boolean stunned) {
        super(name, description, skillAction, cooldown, special);
        this.stunned = stunned;
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
                this.getSkillAction(actionList));
    }
}
