package entities.skill.mage;

import entities.character.Character;
import entities.skill.attack.Attack;

public class FireBall extends Attack {


    protected FireBall(String name, String description, String skillAction, int cooldown) {
        super(name, description, skillAction, cooldown);
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                🔥 MAGIA: BOLA DE FOGO ARREMESSADA PELO MAGO                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🧙‍♂️ " + activePlayer.getName() + " concentra energia arcana nas mãos...");
        System.out.println("🔴 Uma esfera flamejante cresce, pulsando com calor intenso.");
        System.out.println("💥 Com um gesto rápido, a bola de fogo é lançada em direção ao inimigo!");
        System.out.println("🌪️ O ar vibra com a explosão, chamas se espalham pelo campo de batalha.");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                🔥 HABILIDADE ATIVADA: BOLA DE FOGO                                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔥 " + activePlayer.getName() + " invoca uma poderosa bola de fogo!");
        System.out.println("💫 O calor é tão intenso que distorce o ar ao redor.");
        passivePlayer.receiveDamage(activePlayer, this.powerAttack, this);
        System.out.println("⚠️ Inimigos atingidos podem sofrer queimaduras nos próximos turnos.");
        System.out.println();
    }


    public static FireBall ofFireBall() {
        return new FireBall("Bola de Fogo",
                "Causa dano mágico em área.",
                "🔥 Uma explosão de chamas incendeia o inimigo!",
                1);
    }
}
