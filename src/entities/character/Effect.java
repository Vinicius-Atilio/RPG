package entities.character;

public interface Effect {
    void addEffect(Character character, StatusEffect statusEffect);
    void applyEffect(Character character);
}
