package entities.effect;

import entities.character.Player;

public class PoisonedEffect implements Effect {
    private double damage;

    /**
     * Construtor da classe PoisonedEffect.
     * Inicializa o dano causado pelo veneno.
     */
    public PoisonedEffect(double damage) {
        this.damage = damage;
    }

    @Override
    public void addEffect(Player player, StatusEffect statusEffect) {
        player.addEffect(statusEffect);
    }

    @Override
    public double damage(Player player) {
        return player.getLife() * damage; // Retorna o dano causado pelo veneno;
    }
}
