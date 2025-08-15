package entities.skill;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Character;
import entities.observer.BattleObserver;
import entities.skill.hunter.ally.BeastAttack;
import entities.skill.hunter.ally.BeastHeal;
import entities.skill.paladin.HeavenlyGuardian;
import entities.skill.hunter.ally.Beast;
import entities.skill.mage.Arcane;
import entities.skill.warrior.ally.WarStandard;
import entities.skill.hunter.*;
import entities.skill.mage.*;
import entities.skill.hunter.EvasionDefense;
import entities.skill.paladin.DivineShield;
import entities.skill.paladin.BlessingLight;
import entities.skill.paladin.HolyBlow;
import entities.skill.paladin.JusticeHammer;
import entities.skill.support.Support;
import entities.skill.warrior.BattlefieldWrath;
import entities.skill.warrior.DefensivePosture;
import entities.skill.warrior.HeavyAttack;
import entities.skill.warrior.Lunge;

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
    private static final int BOX_WIDTH = 70;

    protected String name;
    protected String description;
    protected int cooldown;
    protected int currentCooldown;
    protected boolean casted;
    protected String skillAction;
    private boolean stunned;
    private int activeSkillPowerAttack;

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

    protected Skill(String name, String description, String skillAction, int cooldown) {
        this.name = name;
        this.description = description;
        this.skillAction = skillAction;
        this.cooldown = cooldown;
        this.currentCooldown = cooldown;
        this.casted = false;
    }

    public Skill(String name, String description, String skillAction, int cooldown, int i) {
        this.name = name;
        this.description = description;
        this.skillAction = skillAction;
        this.cooldown = cooldown;
        this.currentCooldown = cooldown;
        this.casted = false;
    }

    protected static void validateContext() {
        throw new UnsupportedOperationException("This skill is not compatible with this context.");
    }

    public static List<Skill> ofWarrior() {
        return Arrays.asList(
                HeavyAttack.ofHeavyAttack(),
                Lunge.ofLunge(),
                DefensivePosture.ofDefensivePosture(),
                BattlefieldWrath.ofBattlefieldWrath(),
                WarStandard.ofWarriorAlly());
    }

    public static List<Skill> ofMage() {
        return Arrays.asList(
                FireBall.ofFireBall(),
                ParalyzingIce.ofParalyzingIce(),
                ArcaneBarrier.ofArcaneBarrier(),
//                Dynamic.ofArcaneAmplificationOrCurseOfWeakness(),
                ElementalStorm.ofElementalStorm(),
                Arcane.ofMageAlly());
    }

    public static List<Skill> ofPaladin() {
        return Arrays.asList(
                HolyBlow.ofHolyBlow(),
                BlessingLight.ofBlessingLight(),
                DivineShield.ofDivineShield(),
                JusticeHammer.ofJusticeHammer(),
                HeavenlyGuardian.ofPaladinAlly());
    }

    public static List<Skill> ofHunter() {
        return Arrays.asList(
                PrecisionShot.ofPrecisionShot(),
                ExplosiveTrap.ofExplosiveTrap(),
                EvasionDefense.ofEvasion(),
                ArrowRain.ofArrowRain(),
                PoisonArrow.ofPoisonArrow(),
                Beast.ofHunterAlly());
    }

    public static List<Skill> ofBeast() {
        return Arrays.asList(
                BeastAttack.ofAttackTarget(),
                BeastHeal.ofHealTarget()
        );
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


    // implementado somente para habilidades de aliados
    public abstract void prepareSkillToExecute(Ally ally, BattleObserver allyObserver, BattleObserver battleGroundObserver);
    public abstract void prepareSkillToExecute(Ally ally, BattleObserver allyObserver, BattleObserver enemyObserver, BattleObserver battleGroundObserver);
    public abstract void executeSelectedSkill(Ally ally, Character activePlayer, Character passivePlayer);
    public abstract void skillTypeAction(Ally ally, Character activePlayer, Character passivePlayer);
    public abstract void skillEffectAction(Ally ally, Character activePlayer, Character passivePlayer);


    public abstract void prepareSkillToExecute(Character activePlayer, Character passivePlayer, BattleGround battleGround);
    public abstract void executeSelectedSkill(Character activePlayer, Character passivePlayer);
    public abstract void executeSelectedSkill(Character activePlayer, Character passivePlayer, BattleGround battleGround);
    public abstract void skillTypeAction(Character activePlayer, Character passivePlayer);
    public abstract void skillTypeAction(Character activePlayer, Character passivePlayer, BattleGround battleGround);
    public abstract void skillEffectAction(Character activePlayer, Character passivePlayer);

    protected String getAction(List<String> actionList) {
        return actionList.get(ThreadLocalRandom.current().nextInt(actionList.size()));
    }

    public String getDescription() {
        return description;
    }

    public String getSkillAction() {
        return skillAction;
    }

    public boolean isCasted() {
        return casted;
    }

    public int getActiveSkillPowerAttack() {
        return activeSkillPowerAttack;
    }

    protected void printSkillBox(String skillTitle) {
        String border = "╔" + "═".repeat(BOX_WIDTH) + "╗";
        String bottom = "╚" + "═".repeat(BOX_WIDTH) + "╝";
        int padding = (BOX_WIDTH - skillTitle.length()) / 2;
        String line = "║" + " ".repeat(padding) + skillTitle + " ".repeat(BOX_WIDTH - skillTitle.length() - padding) + "║";
        System.out.println(border);
        System.out.println(line);
        System.out.println(bottom);
        System.out.println();
    }
}
