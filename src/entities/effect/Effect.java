package entities.effect;

import entities.character.Character;

public interface Effect {
    void addEffect(entities.character.Character character, StatusEffect statusEffect);
    void applyEffect(Character character);
}
