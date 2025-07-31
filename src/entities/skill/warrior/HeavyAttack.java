package entities.skill.warrior;

import entities.character.Character;
import entities.skill.attack.Attack;
import entities.skill.melee.Melee;

public class HeavyAttack extends Attack {
    public HeavyAttack(String name, String description, String skillAction, int cooldown, int attackPower, boolean special) {
        super(name, description, skillAction, cooldown, attackPower, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║        💥 HABILIDADE: GOLPE PESADO DESFERIDO PELO GUERREIRO    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🧍‍♂️ O guerreiro firma os pés no chão, o peso de sua armadura ressoa no campo...");
        System.out.println("🗡️ Ele empunha sua arma com ambas as mãos, canalizando toda sua força no próximo movimento.");
        System.out.println("🌬️ Um silêncio denso domina o ambiente... o ar parece tremer ao redor da lâmina.");
        System.out.println("⚠️ [PREPARAÇÃO] — Os músculos do guerreiro se contraem num impulso explosivo...");
        System.out.println("💢 Num rugido feroz, ele avança com brutalidade!");
        System.out.println();

        this.executeSelectedSkill(activePlayer, passivePlayer);
    }

    @Override
    public void skillTypeAction(Character actionPlayer) {
    }

    public static HeavyAttack ofHeavyAttack() {
        return new HeavyAttack(
                "Golpe Pesado",
                "Ataque com dano físico aumentado.",
                "🌪️ O som do aço corta o vento... ",
                1,
                1,
                false);
    }
}
