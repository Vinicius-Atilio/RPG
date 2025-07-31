package entities.effect;

import entities.character.Character;

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
    public void addEffect(Character character, StatusEffect statusEffect) {
        character.addEffect(statusEffect);
    }

    @Override
    public double damage(Character character) {
        return character.getLife() * damage; // Retorna o dano causado pelo veneno;
    }
}
