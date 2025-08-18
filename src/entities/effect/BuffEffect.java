package entities.effect;

import entities.character.Player;

/**
 * Classe BuffEffect representa um efeito de buff que pode ser aplicado a um personagem.
 * Ela implementa a interface Effect e define o comportamento do efeito de buff.
 * Intenção: Aplicar um efeito de buff ao personagem, aumentando suas habilidades ou resistência.
 * Visitor Pattern: Permite que o efeito de buff seja aplicado a diferentes tipos de personagens.
 */
public class BuffEffect implements Effect {

    @Override
    public void addEffect(Player player, StatusEffect statusEffect) {
        player.addEffect(statusEffect);
    }

    @Override
    public double damage(Player player) {
        return 0;
    }

}
