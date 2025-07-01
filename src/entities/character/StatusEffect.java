package entities.character;

import java.util.function.Consumer;

public class StatusEffect {
    private String name;
    private int turnDuration;
    private Consumer<Character> characterConsumer;

    public StatusEffect(String name, int turnDuration, Consumer<Character> characterConsumer) {
        this.name = name;
        this.turnDuration = turnDuration;
        this.characterConsumer = characterConsumer;
    }
}
