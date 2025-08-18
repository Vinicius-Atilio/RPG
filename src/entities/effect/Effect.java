package entities.effect;

import entities.character.Player;

public interface Effect {
    void addEffect(Player player, StatusEffect statusEffect);
    double damage(Player player);
}
