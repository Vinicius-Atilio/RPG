package entities.skill.hunter;

import entities.BattleGround;
import entities.character.Character;
import entities.effect.PoisonedEffect;
import entities.effect.StatusEffect;
import entities.skill.attack.Attack;

public class PoisonArrow extends Attack {
    private final PoisonedEffect poisonedEffect = new PoisonedEffect(0.05); // 5% de dano contínuo
    private StatusEffect statusEffect = new StatusEffect();

    protected PoisonArrow(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║          ⏳ 🧪 🏹 PREPARAÇÃO: FLECHA ENVENENADA                            ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🖐️" + activePlayer.getName() + "  desliza os dedos sobre a aljava, puxando uma flecha negra...");
        System.out.println("🧪 Um frasco de veneno é aberto... Um líquido esverdeado escorre lentamente.");
        System.out.println("💉 A ponta da flecha brilha ao ser banhada no veneno mortal...");
        System.out.println("💀 [Efeito Ativo] → Envenenamento | Chance de Corromper: 5%");
        System.out.println("🌪️ O vento sopra "+ passivePlayer.getName() + " sente um cheiro pungente de morte e destruição...");
        System.out.println("🌫️ Uma leve fumaça escapa da ponta... o veneno está instável.");
        System.out.println("🎯 O "  + passivePlayer.getName() + " está na mira...");
        System.out.println("🔥 Flecha envenenada PRONTA PARA DISPARO!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        this.poisonedEffect.addEffect(passivePlayer, statusEffect.ofPoisonArrow());
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                       HABILIDADE ATIVADA: FLECHA ENVENENADA                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🏹 " + activePlayer.getName() + " dispara uma flecha banhada em veneno mortal!");
        System.out.println("☠️ O  " + passivePlayer.getName() +  " é atingido e começa a sofrer dano contínuo pelo veneno.");
        System.out.println("🌫️ O efeito de envenenamento pode enfraquecer o inimigo por 2 turnos.");
        System.out.println();
    }

    public static PoisonArrow ofPoisonArrow() {
        System.out.println("🔫 Habilidade de Caçador: Flecha Envenenada PoisonArrow");
        return new PoisonArrow("Flecha Envenenada",
                "Aplica veneno que causa dano contínuo por 2 turnos.",
                "☠️ A flecha envenenada atinge o inimigo, causando dano ao longo do tempo!",
                2);
    }
}
