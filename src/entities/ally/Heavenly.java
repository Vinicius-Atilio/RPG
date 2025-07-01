package entities.ally;

import entities.character.Attribute;
import entities.character.Character;

import java.util.Arrays;
import java.util.List;

public class Heavenly  extends Ally {

    private static final List<String> actionList = Arrays.asList(
            " desce dos céus para abençoar seu aliado!",
            " surge com uma luz celestial, trazendo proteção!",
            " voa majestoso, espalhando bênçãos divinas!",
            " irradia uma aura de luz, fortalecendo seu aliado!"
    );


    public Heavenly(String name, String description, int cooldown, Attribute attribute) {
        super(name, description, cooldown, attribute);
    }

    public static Heavenly ofGuardian() {
        return new Heavenly("Divino", "Um aliado celestial que traz bênçãos e proteção",
                4, Attribute.ofGuardian());
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("✨ O aliado " + actionPlayer.getAlly().getName() + this.getSkillAction(actionList));
    }
}
