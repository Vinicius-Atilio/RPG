package entities.skill;

import entities.character.Character;
import entities.ally.Heavenly;
import entities.ally.Animal;
import entities.ally.Arcane;
import entities.ally.Standard;
import entities.skill.dynamic.Dynamic;
import entities.skill.melee.Melee;
import entities.defense.AgilityDefense;
import entities.defense.HeavenlyDefense;
import entities.defense.MagicDefense;
import entities.skill.ranged.MagicRanged;
import entities.skill.ranged.StrengthDefense;
import entities.skill.ranged.StrengthRanged;
import entities.skill.support.Support;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe Skill representa uma habilidade que pode ser utilizada por um personagem.
 * Ela contém informações sobre o nome, descrição, tempo de recarga e ações associadas à habilidade.
 * Intenção: Fornecer uma base para habilidades que podem ser usadas em combate, com diferentes tipos de ações e efeitos.
 * Template Method: Define a estrutura básica de uma habilidade, permitindo que subclasses implementem ações específicas.
 */
public abstract class Skill {
    protected String name;
    protected String description;
    protected int cooldown;
    protected int currentCooldown;
    protected boolean casted;
    protected boolean special;
    protected String skillAction;
    private boolean stunned;

    protected static final List<String> voiceActionList = Arrays.asList(
            "Sinta o peso da minha lâmina!\" - grita enquanto executa ",
            " \"A vitória é minha!\" - brada com determinação enquanto executa ",
            " \"Prepare-se para sentir minha fúria!\" - grita enquanto executa ",
            " \"A justiça prevalecerá!\" - brada com convicção enquanto executa ",
            " \"A luz me guia!\" - exclama enquanto executa ",
            " \"A escuridão não tem chance contra mim!\" - grita enquanto executa ",
            " \"A vitória é inevitável!\" - brada com confiança enquanto executa ",
            " \"A força está do meu lado!\" - exclama enquanto executa ",
            " \"A batalha é minha!\" - grita enquanto executa ",
            " \"A vitória é certa!\" - brada com determinação enquanto executa ",
            " \"A justiça será feita!\" - exclama enquanto executa ",
            " \"A luz triunfará!\" - grita enquanto executa ",
            " \"A escuridão não me deterá!\" - brada com coragem enquanto executa ",
            " \"A vitória é minha!\" - exclama com confiança enquanto executa ",
            " \"A força está comigo!\" - grita enquanto executa "
    );

    protected static final List<String> hitActionList = Arrays.asList(
            " atinge o alvo com um golpe certeiro!",
            " acerta em cheio, causando grande dano!",
            " desferiu um ataque devastador!",
            " atingiu o inimigo com precisão mortal!",
            " acertou o ponto fraco do adversário!",
            " causou um impacto brutal no inimigo!",
            " atingiu o alvo com uma força avassaladora!",
            " desferiu um golpe poderoso que abalou o campo de batalha!",
            " acertou o inimigo com um ataque fulminante!",
            " atingiu o adversário com uma precisão impressionante!"
    );

    protected static final List<String> hitEffectList = Arrays.asList(
            " cambaleia, mas se mantém de pé.",
            " é atingido com força, mas ainda resiste.",
            " é atingido, mas não cai.",
            " é atingido, mas continua lutando.",
            " é atingido, mas ainda está de pé.",
            " cambaleia, mas não cai.",
            " é atingido, mas ainda está de pé."
    );

    protected static final List<String> answerVegeanceList = Arrays.asList(
            "Você sente a vingança pulsando em suas veias!",
            "A vingança é doce, e você está prestes a saboreá-la!",
            "A sede de vingança o impulsiona a lutar com mais força!",
            "A vingança é sua motivação, e você não vai desistir!",
            "A vingança é o combustível que alimenta sua determinação!",
            "Você está determinado a vingar-se a qualquer custo!",
            "Olhar de vingança!",
            "A vingança é sua única opção!"
    );

    public Skill() {}

    protected Skill(String name, String description, String skillAction, int cooldown, boolean special) {
        this.name = name;
        this.description = description;
        this.skillAction = skillAction;
        this.cooldown = cooldown;
        this.currentCooldown = cooldown;
        this.casted = false;
        this.special = special;
    }

    public Skill(String name, String description, String skillAction, int cooldown, boolean stunned, boolean special) {
        this.name = name;
        this.description = description;
        this.skillAction = skillAction;
        this.cooldown = cooldown;
        this.currentCooldown = cooldown;
        this.casted = false;
        this.special = special;
        this.stunned = stunned;
    }

    public static List<Skill> ofWarrior() {
        return Arrays.asList(
                Melee.ofHeavyAttack(),
                Melee.ofIunge(),
                StrengthDefense.ofDefensivePosture(),
                Melee.ofBattlefieldWrath(),
                Standard.ofWar());
    }

    public static List<Skill> ofMage() {
        return Arrays.asList(
                MagicRanged.ofFireBall(),
                MagicRanged.ofParalyzingIce(),
                MagicDefense.ofArcaneBarrier(),
                Dynamic.ofArcaneAmplificationOrCurseOfWeakness(),
                MagicRanged.ofElementalStorm(),
                Arcane.ofMystic());
    }

    public static List<Skill> ofPaladin() {
        return Arrays.asList(
                Melee.ofHolyBlow(),
                Support.ofBlessingOfLight(),
                HeavenlyDefense.ofDivineShield(),
                Melee.ofJusticeHammer(),
                Heavenly.ofGuardian());
    }

    public static List<Skill> ofHunter() {
        return Arrays.asList(
                StrengthRanged.ofPrecisionShot(),
                StrengthRanged.ofExplosiveTrap(),
                AgilityDefense.ofEvasion(),
                StrengthRanged.ofArrowRain(),
                StrengthRanged.ofPoisonArrow(),
                Animal.ofBeast());
    }

    public void updateSkillCooldown() {
        if (cannotBeCast()) {
            updateCooldown();
        }

        if (hasBeenCasted()) {
            updateCurrentCooldown();
        }
    }

    public String getName() {
        return name;
    }

    public boolean cannotBeCast() {
        return this.currentCooldown != 0 && !this.casted;
    }

    public boolean hasBeenCasted() {
        return this.currentCooldown == 0 && this.casted;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public void markAsCasted() {
        this.casted = true;
    }

    private void updateCooldown() {
        this.currentCooldown -= 1;
    }

    private void updateCurrentCooldown() {
        this.currentCooldown = this.cooldown;
        this.casted = false;
    }

    public boolean isSupport() {
        return this instanceof Support;
    }

    public abstract void prepareSkillToAttack(Character player1, Character player2);
    public abstract void executeSelectedSkill(Character actionPlayer, Character passivePlayer);
    public abstract void skillTypeAction(Character actionPlayer);
    public abstract void skillAction(Character actionPlayer, Character passivePlayer);

    protected String getAction(List<String> actionList) {
        return actionList.get(ThreadLocalRandom.current().nextInt(actionList.size()));
    }
}
