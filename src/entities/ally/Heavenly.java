package entities.ally;

import entities.character.Attribute;
import entities.character.Character;
import entities.state.OriginalState;
import entities.state.State;

import java.util.Arrays;
import java.util.List;

public class Heavenly  extends Ally {

    private static final List<String> actionList = Arrays.asList(
            " desce dos céus para abençoar seu aliado!",
            " surge com uma luz celestial, trazendo proteção!",
            " voa majestoso, espalhando bênçãos divinas!",
            " irradia uma aura de luz, fortalecendo seu aliado!"
    );


    public Heavenly(String name, String description, int cooldown, State state) {
        super(name, description, cooldown, state);
    }

    public static Heavenly ofGuardian() {
        return new Heavenly("Divino", "Um aliado celestial que traz bênçãos e proteção",
                4, OriginalState.ofGuardian());
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {

    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("✨ O aliado " + actionPlayer.getAlly().getName() + this.getAction(actionList));
    }
}
