package entities.observer;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.attack.Trap;

public interface Observer {
    void update(Player player, Trap trap);
    void update(Player player, Ally ally);
}
