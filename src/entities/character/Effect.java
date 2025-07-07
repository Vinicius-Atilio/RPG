package entities.character;

public interface Effect {
    void addEffect(Character character);
    void applyEffect(Character character, StatusEffect statusEffect);
}
