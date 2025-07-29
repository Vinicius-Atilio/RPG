package entities.effect;


import entities.character.Character;

public class DebuffEffect implements Effect {

    @Override
    public void applyEffect(entities.character.Character character) {
        character.applyEffect();
    }

    @Override
    public void addEffect(Character character, StatusEffect statusEffect) {
        character.addEffect(statusEffect);
    }
}
