package entities.skill.mage;

import entities.character.Character;
import entities.skill.attack.Attack;

public class ElementalStorm extends Attack {
    public ElementalStorm(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║        ⚡️🔥❄️ ESPECIAL ATIVADO: TEMPESTADE ELEMENTAL DEVASTA O CAMPO!                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🧙‍♂️ " + activePlayer.getName() + " ergue o cajado, canalizando forças primordiais...");
        System.out.println("🌪️ Ventos furiosos giram, faíscas elétricas cortam o ar, chamas e gelo se entrelaçam!");
        if (activePlayer.isAllyAlive()) {
            System.out.println("💥 Uma explosão de elementos atinge todos os inimigos, enquanto aliados sentem seu poder crescer!");
            System.out.println("⚡️ Efeitos de dano em área e aumento de ataque para aliados por 2 turnos.");
            activePlayer.buffAlly();
        }
        System.out.println("🔥 " + passivePlayer.getName() + " observa em sua frente uma tempestade de fogo, gelo e eletricidade!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      🌟 HABILIDADE ESPECIAL ATIVADA: TEMPESTADE ELEMENTAL (ESPECIAL)            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🌪️ " + activePlayer.getName() + " invoca uma tempestade de fogo, gelo e eletricidade!");
        System.out.println("🔥 Todos os inimigos sofrem dano massivo.");
        passivePlayer.receiveSpecialDamage(activePlayer, this.powerAttack, this);
        System.out.println();

    }

    public static ElementalStorm ofElementalStorm() {
        return new ElementalStorm("Tempestade Elemental (Especial)",
                "Conjura ataque massivo com múltiplos elementos em área, aumenta dano do aliado.",
                "🌪️ Uma tempestade de fogo, gelo e eletricidade atinge o campo de batalha!",
                3);
    }
}
