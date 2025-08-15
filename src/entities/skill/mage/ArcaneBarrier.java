package entities.skill.mage;

import entities.ally.Ally;
import entities.character.Character;
import entities.observer.BattleObserver;
import entities.skill.defense.Defense;

import java.util.Arrays;
import java.util.List;

public class ArcaneBarrier extends Defense {
    private static final List<String> playerActionList = Arrays.asList(
            " se abaixa e preparando uma barreira mágica de proteção! para se proteger de ",
            " ergue suas mãos, concentrando-se em uma barreira mágica! para evitar o ataque de ",
            " seus olhos brilham enquanto se prepara para conjurar uma barreira mágica! para absorver o ataque de ",
            " começa a canalizar energia mágica para criar uma barreira protetora! para se defender de "
    );


    private static final List<String> skillActionList = Arrays.asList(
            " ativa uma barreira mágica de proteção!",
            " conjura um escudo mágico para bloquear ataques!",
            " invoca uma aura mágica defensiva!",
            " emite magia de proteção!",
            " fortalece sua defesa com magia!",
            " cria um campo mágico de defesa!",
            " conjura uma barreira mágica!"
    );


    protected ArcaneBarrier(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                 🛡️ MAGIA: BARREIRA ARCANA ERGUIDA PELO MAGO                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🧙‍♂️ " + activePlayer.getName() + " traça símbolos no ar, canalizando energia arcana...");
        System.out.println("🔮 Uma barreira translúcida surge ao seu redor, cintilando com luz mística.");
        System.out.println("✨ O escudo mágico bloqueia ataques, protegendo contra ameaças iminentes!");
        System.out.println(passivePlayer.getName() + " seus olhos se arregalam ao ver a barreira mágica se formando.");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        activePlayer.changeStateToDefensive();
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                     HABILIDADE ATIVADA: BARREIRA ARCANA                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔮 " + activePlayer.getName() + " ergue uma barreira mágica protetora!");
        System.out.println("🛡️ Ataques inimigos terão dificuldade em atravessar o escudo arcano.");
        System.out.println();
    }

    public static ArcaneBarrier ofArcaneBarrier() {
        return new ArcaneBarrier("Barreira Arcana",
                "Cria escudo mágico (não causa dano).",
                "🔮 Uma barreira mágica envolve você, protegendo contra ataques.",
                2);
    }
}
