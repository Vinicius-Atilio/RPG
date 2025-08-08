package entities.skill.melee;

import entities.BattleGround;
import entities.character.Character;
import entities.skill.attack.Attack;

import java.util.Arrays;
import java.util.List;

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

    public Melee(String name, String description, String skillAction, int cooldown, boolean stunned, boolean special) {
        super(name, description, skillAction, cooldown, stunned, special);
    }

    public Melee(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println("\n⚔️ " + activePlayer.getName() + " " + this.getAction(playerActionList) + " " + this.getName());
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println("⚔️ " + activePlayer.getName() +
                this.getAction(skillActionList));
    }
}
