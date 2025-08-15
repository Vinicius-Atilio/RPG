package entities.skill.hunter;

import entities.character.Character;
import entities.skill.attack.Attack;

public class ArrowRain extends Attack {
    protected ArrowRain(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        printSkillBox("☄️ ATAQUE ESPECIAL: CHUVA DE FLECHAS BANUK INVOCADA");
        System.out.println("🌫️ O céu escurece enquanto o " +activePlayer.getName()+ " finca seus pés no solo...");
        System.out.println("🧿 " + activePlayer.getName() + " ergue o arco em direção ao céu — seus olhos brilham com foco absoluto.");
        System.out.println("🔁 Em um movimento rápido, ele dispara várias flechas para o alto, uma após a outra...");
        System.out.println("💨 As flechas cortam o vento em uníssono... um som agudo ecoa por toda a área...");
        System.out.println("🔔 Um silêncio sepulcral se instala por um instante...");
        System.out.println("⏳ [TENSÃO] — A chuva se aproxima. O alvo " + passivePlayer.getName() + " indefeso observa o céu...");
        System.out.println("🌧️ E então, como um dilúvio, as flechas caem do céu, atingindo tudo em seu caminho!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        printSkillBox("🌟️ HABILIDADE ESPECIAL ATIVADA: CHUVA DE FLECHAS BANUK (ESPECIAL)");
        System.out.println("🏹 " + activePlayer.getName() + " ergue o arco aos céus, invocando uma tempestade de flechas!");
        System.out.println("☁️ Nuvens densas se formam — a tensão paira no ar...");
        System.out.println("🌪️ O som cortante das flechas anuncia o caos iminente!");
        passivePlayer.receiveSpecialDamage(activePlayer, this.powerAttack, this);
        System.out.println("🩸 Alvos atingidos podem sofrer sangramento nos próximos turnos.");
        System.out.println();
    }

    public static ArrowRain ofArrowRain() {
        return new ArrowRain("Chuva de Flechas (especial)",
                "Dispara várias flechas em área com chance de sangramento.",
                "🌧️ Uma chuva de flechas cai sobre o inimigo, causando dano em área!",
                3);
    }
}
