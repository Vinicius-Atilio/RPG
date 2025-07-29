package entities.ally;

import entities.character.Attribute;
import entities.character.Character;
import entities.state.OriginalState;
import entities.state.State;

import java.util.Arrays;
import java.util.List;

public class Animal extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " surge ferozmente na batalha pronto para atacar!",
            " corre rapidamente para ajudar seu mestre!!",
            " salta alto e ataca com garras afiadas!",
            " ruge ferozmente antes de atacar!"
    );

    public Animal(String name, String description, int cooldown, State state) {
        super(name, description, cooldown, state);
    }

    public static Animal ofBeast() {
        return new Animal("Fera Companheira",
                "Um aliado animal que traz força e agilidade para a batalha",
                4,  OriginalState.ofBeast());
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {

    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🦁 O aliado " + actionPlayer.getAlly().getName() + this.getAction(actionList));
    }
}
