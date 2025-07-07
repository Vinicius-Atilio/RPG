package entities.defense;

import entities.character.BuffEffect;
import entities.character.Character;
import entities.character.StatusEffect;
import java.util.Arrays;
import java.util.List;

public class AgilityDefense extends Defense {
    private BuffEffect buffEffect = new BuffEffect();

    private static final List<String> playerActionList = Arrays.asList(
            " se prepara para esquivar de ",
            " se concentra para esquivar do ",
            " ativa sua agilidade! desviando do ",
            " se posiciona para uma esquiva rápida! de ",
            " se prepara para um movimento ágil para evitar o ataque de ",
            " se concentra na esquiva! desviando do "
    );


    private static final List<String> actionList = Arrays.asList(
            " ativa uma esquiva ágil!",
            " se esquiva habilidosamente, tornando-se mais difícil de atingir!",
            " realiza um movimento ágil, evitando ataques inimigos!",
            " executa uma manobra evasiva, esquivando-se de ataques!",
            " usa sua agilidade para evitar ataques inimigos!",
            " realiza um salto ágil, esquivando-se de ataques!"
    );

    public AgilityDefense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character player1, Character player2) {
        System.out.println("\n🤸 " + player1.getName() + this.getAction(playerActionList) + this.getName());
        this.executeSelectedSkill(player1, player2);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        this.buffEffect.addEffect(activePlayer);
        this.buffEffect.applyEffect(activePlayer,  StatusEffect.ofEvasion());
        System.out.println(activePlayer.getName() + this.skillAction + passivePlayer.getName());
        this.skillTypeAction(activePlayer);
    }

    public static AgilityDefense ofEvasion() {
        return new AgilityDefense("Evasão",
                "Aumenta a esquiva por 2 turnos (não causa dano).",
                "️ se esquiva habilidosamente, tornando-se mais difícil de ser atingido por ",
                3, false);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🏃‍♂️ " + actionPlayer.getName() + this.getAction(actionList));
    }

}
