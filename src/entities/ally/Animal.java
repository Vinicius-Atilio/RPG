package entities.ally;

import entities.character.Attribute;
import entities.character.Character;

import java.util.Arrays;
import java.util.List;

public class Animal extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " surge ferozmente na batalha pronto para atacar!",
            " corre rapidamente para ajudar seu mestre!!",
            " salta alto e ataca com garras afiadas!",
            " ruge ferozmente antes de atacar!"
    );

    public Animal(String name, String description, int cooldown, Attribute attribute) {
        super(name, description, cooldown, attribute);
    }

    public static Animal ofBeast() {
        return new Animal("Fera Companheira",
                "Um aliado animal que traz força e agilidade para a batalha",
                4,  Attribute.ofBeast());
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🦁 O aliado " + actionPlayer.getAlly().getName() + this.getSkillAction(actionList));
    }
}
