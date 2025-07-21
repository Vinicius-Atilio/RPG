package entities.state;

import entities.character.Character;

public class DeadState implements State {

    public DeadState() {}

    public DeadState(Character character) {
    }

    public static DeadState of(Character character) {
        return new DeadState(character);
    }

    /**
     * This method is called when the character dies.
     * It can be overridden by subclasses to provide specific behavior.
     *
     * @param character The character that has died.
     */
    @Override
    public void onDeath(Character character) {
        System.out.println("ESTOU MORRENDO " + character.getName() + "!");
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
