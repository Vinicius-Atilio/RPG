package entities.state;

import entities.character.Character;

public abstract class CharacterState {
    protected Character character;

    public CharacterState(Character character) {
        this.character = character;
    }
}
