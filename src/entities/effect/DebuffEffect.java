package entities.effect;
import entities.character.Character;

public class DebuffEffect implements Effect {

    @Override
    public void addEffect(Character character, StatusEffect statusEffect) {
    }

    @Override
    public double damage(Character character) {
        return 0;
    }
}
