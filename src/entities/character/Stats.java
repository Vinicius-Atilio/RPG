package entities.character;

public interface Stats {
    double calculateDamage(Player actionPlayer, Player passivePlayer, int powerAttack);
}
