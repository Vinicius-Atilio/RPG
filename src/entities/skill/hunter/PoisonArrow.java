package entities.skill.hunter;

import entities.character.Player;
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
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        printSkillBox("⏳ 🧪 🏹 PREPARAÇÃO: FLECHA ENVENENADA");
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
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        this.poisonedEffect.addEffect(passivePlayer, statusEffect.ofPoisonArrow());
        printSkillBox("HABILIDADE ATIVADA: FLECHA ENVENENADA");
        System.out.println("🏹 " + activePlayer.getName() + " dispara uma flecha banhada em veneno mortal!");
        System.out.println("☠️ O  " + passivePlayer.getName() +  " é atingido e começa a sofrer dano contínuo pelo veneno.");
        System.out.println("🌫️ O efeito de envenenamento pode enfraquecer o inimigo por 2 turnos.");
        System.out.println();
    }

    public static PoisonArrow ofPoisonArrow() {
        return new PoisonArrow("Flecha Envenenada",
                "Aplica veneno que causa dano contínuo por 2 turnos.",
                "☠️ A flecha envenenada atinge o inimigo, causando dano ao longo do tempo!",
                2);
    }
}
