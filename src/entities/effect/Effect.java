package entities.effect;

import entities.character.Character;

public interface Effect {
    void addEffect(Character character, StatusEffect statusEffect);
    double damage(Character character);
}
