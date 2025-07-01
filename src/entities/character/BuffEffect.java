package entities.character;

public class BuffEffect implements Effect {

    @Override
    public void applyEffect(Character character) {
        character.applyEffect();
    }

    @Override
    public void evasionEffect(Character character) {
        character.addEffect(new StatusEffect("Evasão", 2, Character::markAsImmune));
    }
}
