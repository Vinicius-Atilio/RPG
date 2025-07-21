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

    public static StatusEffect ofEvasion() {
        return new StatusEffect("Evasão", 2, Character::markAsImmune);
    }

    public static StatusEffect ofPoisonArrow() {
        return new StatusEffect("Flecha Envenenada", 2, Character::makePoisoned);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StatusEffect{" +
                "name='" + name + '\'' +
                ", turnDuration=" + turnDuration +
                ", characterConsumer=" + characterConsumer +
                '}';
    }

    public void accept(Character character) {
        characterConsumer.accept(character);
    }

    public int getTurnDuration() {
        return turnDuration;
    }

    public void updateTurnDuration() {
        this.turnDuration--;
    }
}
