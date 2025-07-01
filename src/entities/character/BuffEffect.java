package entities.character;

public class BuffEffect implements Effect {

    @Override
    public void evasionEffect(Character character) {
        character.addEffect(new StatusEffect("Evasão", 1, Character::markAsImmune));
    }
}
