package entities.character;

import entities.Weapon;

public interface Stats {
    double calculateDamage(Character actionPlayer, Character passivePlayer, int powerAttack);
}
