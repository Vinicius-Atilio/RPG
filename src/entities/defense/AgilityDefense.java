package entities.defense;

import entities.character.BuffEffect;
import entities.character.Character;
import entities.character.Effect;

import java.util.Arrays;
import java.util.List;

public class AgilityDefense extends Defense {
    private BuffEffect buffEffect = new BuffEffect();


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
    public void executeSelectedSkill(Character actionPlayer, Character passivePlayer) {
        this.buffEffect.evasionEffect(actionPlayer);
        System.out.println(actionPlayer.getName() + this.skillAction + passivePlayer.getName());
        this.skillTypeAction(actionPlayer);
    }

    public static AgilityDefense ofEvasion() {
        return new AgilityDefense("Evasão",
                "Aumenta a esquiva por 1 turnos (não causa dano).",
                "️ se esquiva habilidosamente, tornando-se mais difícil de atingir de ",
                0, false);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🏃‍♂️ " + actionPlayer.getName() + this.getSkillAction(actionList));
    }

}
