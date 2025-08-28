package entities.skill.warrior;

import entities.character.Player;
import entities.skill.attack.Attack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HeavyAttack extends Attack {
    private Random random = new Random();
    public HeavyAttack(String name, String description, String skillAction, int cooldown, int attackPower) {
        super(name, description, skillAction, cooldown, attackPower);
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer) {
        printSkillBox("💥 HABILIDADE SELECIONADA: GOLPE PESADO DESFERIDO PELO GUERREIRO");
        System.out.println("🧍‍♂️ O guerreiro firma os pés no chão, o peso de sua armadura ressoa no campo...");
        System.out.println("🗡️ Ele empunha sua arma com ambas as mãos, canalizando toda sua força no próximo movimento.");
        System.out.println("🌬️ Um silêncio denso domina o ambiente... o ar parece tremer ao redor da lâmina.");
        System.out.println("⚠️ [PREPARAÇÃO] — Os músculos do guerreiro se contraem num impulso explosivo...");
        System.out.println("💢 Num rugido feroz, ele avança com brutalidade!");
        System.out.println();
        System.out.println(this.description);
        System.out.println(this.skillAction);
        System.out.println();
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer) {
        printSkillBox("💥 HABILIDADE ATIVADA: GOLPE PESADO");
        System.out.println();
        System.out.println("💢" + activePlayer.getName() + " " + voiceActionList.get(ThreadLocalRandom.current().nextInt(voiceActionList.size())));
        System.out.println("💥" + activePlayer.getName() +" " + hitActionList.get(ThreadLocalRandom.current().nextInt(hitActionList.size())));
        passivePlayer.receiveDamage(activePlayer, this.calculateSkillDamage(activePlayer), this);
        System.out.println("⚔️ O impacto reverbera pelo campo de batalha, fazendo o chão tremer!");
        System.out.println();
    }

    @Override
    public double calculateSkillDamage(Player activePlayer) {
        double damage = this.powerAttack + ( activePlayer.getMainAttribute() * 2.5); // Golpe pesado causa 50% a mais de dano
        if (this.random.nextInt(101) >= 75) {
            System.out.println("🎯 GOLPE CRÍTICO! O ataque atinge um ponto vital!");
            damage *= 2.5;
        }

        return damage;
    }

    public static HeavyAttack ofHeavyAttack() {
        return new HeavyAttack(
                "Golpe Pesado",
                "Ataque com dano físico aumentado.",
                "🌪️ O som do aço corta o vento... ",
                1,
                1);
    }
}
