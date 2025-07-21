package entities.character;


public class DebuffEffect implements Effect {

    @Override
    public void applyEffect(Character character) {
        character.applyEffect();
    }

    @Override
    public void addEffect(Character character, StatusEffect statusEffect) {
        character.addEffect(statusEffect);
    }
}
