package entities.state;

import entities.character.Character;

public interface State {
    void onDeath(Character character);
    boolean isAlive();
}
