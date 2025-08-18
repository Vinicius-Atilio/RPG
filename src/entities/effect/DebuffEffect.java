package entities.effect;
import entities.character.Player;

public class DebuffEffect implements Effect {

    @Override
    public void addEffect(Player player, StatusEffect statusEffect) {
    }

    @Override
    public double damage(Player player) {
        return 0;
    }
}
