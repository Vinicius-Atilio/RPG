package entities.skill.paladin;

import entities.BattleGround;
import entities.character.Character;
import entities.skill.attack.Attack;
import entities.skill.melee.Melee;

public class HolyBlow extends Attack {
    protected HolyBlow(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                 ✨ HABILIDADE: GOLPE SAGRADO DESFERIDO PELO PALADINO           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🛡️ " + activePlayer.getName() + " ergue sua arma, invocando a luz divina...");
        System.out.println("🌟 Um brilho intenso envolve a lâmina, afastando as sombras ao redor.");
        System.out.println("⚔️ O golpe é desferido com fé inabalável, mirando o coração do inimigo sombrio!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                      HABILIDADE ATIVADA: GOLPE SAGRADO                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✨ " + activePlayer.getName() + " canaliza energia sagrada em sua arma!");
        System.out.println("🗡️ O ataque causa dano extra contra inimigos sombrios.");
        System.out.println();
    }

    public static Melee ofHolyBlow() {
        return new Melee("Golpe Sagrado",
                "Ataque com dano extra contra inimigos sombrios.",
                "✨ Uma luz sagrada envolve sua arma ao atingir o alvo.", 1, false);
    }
}
