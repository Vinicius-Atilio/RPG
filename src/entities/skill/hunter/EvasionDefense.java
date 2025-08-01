package entities.skill.hunter;

import entities.defense.Defense;
import entities.effect.BuffEffect;
import entities.character.Character;

import java.util.Arrays;
import java.util.List;

public class EvasionDefense extends Defense {
    private final BuffEffect buffEffect = new BuffEffect();

    private static final List<String> playerActionList = Arrays.asList(
            " se prepara para esquivar de ",
            " se concentra para esquivar do ",
            " ativa sua agilidade! desviando do ",
            " se posiciona para uma esquiva rápida! de ",
            " se prepara para um movimento ágil para evitar o ataque de ",
            " se concentra na esquiva! desviando do "
    );


    private static final List<String> actionList = Arrays.asList(
            " ativa uma esquiva ágil!",
            " se esquiva habilidosamente, tornando-se mais difícil de atingir!",
            " realiza um movimento ágil, evitando ataques inimigos!",
            " executa uma manobra evasiva, esquivando-se de ataques!",
            " usa sua agilidade para evitar ataques inimigos!",
            " realiza um salto ágil, esquivando-se de ataques!"
    );

    public EvasionDefense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {
        System.out.println("\n🤸 " + activePlayer.getName() + this.getAction(playerActionList) + this.getName());
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   🎯 SE POSICIONA PARA: Evasão                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("👣 " + activePlayer.getName() + " analisa o terreno ao seu redor...");
        System.out.println("🪶 Seus pés se movem com leveza sobre a terra, como se não deixassem pegadas.");
        System.out.println("🎯 Ele se prepara para esquivar de qualquer golpe que vier!");
        System.out.println("⚠️ A Evasão será ativada — tornar-se-á difícil de ser atingido por 2 turnos.");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
//        this.buffEffect.addEffect(activePlayer,  StatusEffect.ofEvasion());
        System.out.println(activePlayer.getName() + this.skillAction + passivePlayer.getName());
        this.skillTypeAction(activePlayer);
    }

    public static EvasionDefense ofEvasion() {
        return new EvasionDefense("Evasão",
                "Aumenta a esquiva por 2 turnos (não causa dano).",
                "️ se esquiva habilidosamente, tornando-se mais difícil de ser atingido por ",
                3, false);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
        actionPlayer.changeStateToEvasion();
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║          🪶 HABILIDADE DEFENSIVA ATIVADA: EVASÃO                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("🎯 " + actionPlayer.getName() + " desaparece em um movimento ágil.");
        System.out.println("🏃‍♂️ " + actionPlayer.getName() + this.getAction(actionList));
        System.out.println("🕸️ As tentativas de ataque tornam-se inúteis — ele se move como uma sombra.");
        System.out.println("👁️ Seu olhar permanece atento, pronto para qualquer ação inimiga.");
        System.out.println("⏳ Evasão ativa por 2 turnos. Será difícil atingi-lo enquanto durar o efeito!");
    }
}
