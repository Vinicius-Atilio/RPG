package entities.skill.ranged;

import entities.character.Character;
import entities.character.DebuffEffect;
import entities.character.StatusEffect;

public class PoisonArrow extends Ranged {
    private final DebuffEffect buffEffect = new DebuffEffect();

    protected PoisonArrow(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    public static PoisonArrow ofPoisonArrow() {
        System.out.println("🔫 Habilidade de Caçador: Flecha Envenenada PoisonArrow");
        return new PoisonArrow("Flecha Envenenada",
                "Aplica veneno que causa dano contínuo por 2 turnos.",
                "☠️ A flecha envenenada atinge o inimigo, causando dano ao longo do tempo!",
                2, false);
    }


    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {
        System.out.println("\n🏹 " + activePlayer.getName() + " dispara uma flecha envenenada em " + passivePlayer.getName() + "!");
        System.out.println("a" + this.description);
        System.out.println(this.skillAction);
        this.buffEffect.addEffect(passivePlayer, StatusEffect.ofPoisonArrow());
        this.buffEffect.applyEffect(passivePlayer);
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        System.out.println("🏹 " + actionPlayer.getName() + " dispara uma flecha envenenada!");
        System.out.println("☠️ A flecha envenenada causa dano ao longo do tempo!");
    }
}
