package entities.state;

import entities.character.Character;
import entities.character.OriginalAttribute;

public class AliveState implements State {

    @Override
    public void onDeath(Character character) {
        character.changeState(DeadState.of(new Character("* DEAD " + character.getName(), OriginalAttribute.ofDeath())));
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
