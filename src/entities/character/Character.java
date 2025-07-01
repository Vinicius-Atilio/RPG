package entities.character;

import entities.Inventory;
import entities.Weapon;
import entities.ally.Ally;
import entities.skill.Skill;
import enums.Race;
import enums.Specialization;
import enums.Type;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
    private BigInteger id;
    private String name;
    private Race race;
    private Specialization specialization;
    private Attribute attribute;
    private Type type;
    private List<Skill> skills;
    private StatusEffect statusEffect;
    private Weapon weapon;
    private Ally ally;
    private Inventory inventory;
    private boolean immune;
    private double life;
    private boolean alive;

    public Character(String name, Race race, Specialization specialization, Attribute attribute,
                     Type type, List<Skill> skills, Weapon weapon, Inventory inventory) {
        this.id = new BigInteger(128, new SecureRandom());
        this.name = name;
        this.race = race;
        this.specialization = specialization;
        this.attribute = attribute;
        this.type = type;
        this.skills = skills;
        this.weapon = weapon;
        this.inventory = inventory;
        this.life = 100;
        this.alive = true;

        if (this.weapon != null) {
            this.attribute.update(this.weapon.getAttribute());
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public String getName() {
        return name;
    }

    public double getLife() {
        return life;
    }

    public Skill play() {
        Skill selectedSkill = this.skills.get(ThreadLocalRandom.current().nextInt(this.skills.size()));
        if (selectedSkill.getCurrentCooldown() == 0) {
            return selectedSkill;
        }

        return play();
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", race=" + race +
                ", specialization=" + specialization +
                ", attribute=" + attribute +
                ", type=" + type +
                ", skills=" + skills +
                ", weapon=" + weapon +
                ", ally=" + ally +
                ", inventory=" + inventory +
                ", life=" + life +
                ", alive=" + alive +
                '}';
    }

    public void decreaseAllSkillsCd() {
        this.skills.forEach(Skill::updateSkillCooldown);
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public boolean makeAttack(Character actionPlayer, Character passivePlayer, int powerAttack) {
        if (passivePlayer.immune) {
            System.out.printf("%s está imune a ataques, sob efeito de %s!%n", passivePlayer.getName(), this.statusEffect != null ? this.statusEffect.getName() : "imunidade");
            return false;
        }

        this.life -= this.attackPower(actionPlayer, powerAttack);
        return true;
    }

    private double attackPower(Character actionPlayer, int powerAttack) {
        return (actionPlayer.getMainAttribute() * actionPlayer.weapon.getConversionFactor())
                + (powerAttack - actionPlayer.attribute.getDefense());
    }

    private int getMainAttribute() {
        return switch (this.specialization) {
            case Warrior, Paladin -> this.attribute.getStrength();
            case Mage -> this.attribute.getIntelligence();
            case Hunter -> this.attribute.getAgility();
        };
    }

    public void makeDefense(Character actionPlayer, Skill skill) {
        skill.updateSkillCooldown();
    }

    public void  evokeAlly() {
        this.ally = this.specialization.ally();
    }

    public void useAllyIfAlive() {
        if (this.ally != null && this.ally.isAlive()) {
            if (this.ally.isSupport()) {
                this.attribute.update(this.ally.getAttribute());
            }
        }
    }

    public void addEffect(StatusEffect statusEffect) {
        this.statusEffect = statusEffect;
    }

    public void applyEffect() {
        if (this.statusEffect == null) {
            System.out.println("Nenhum efeito ativo.");
            return;
        }

        this.statusEffect.accept(this);
    }

    public void updateStatusEffect() {
        if (this.statusEffect == null) {
            return;
        }

        if (this.statusEffect.getTurnDuration() > 0) {
            this.statusEffect.updateTurnDuration();
            return;
        }

        System.out.println("O efeito " + this.statusEffect.getName() + " de " + this.name + " expirou.");
        this.statusEffect = null;
    }

    public Ally getAlly() {
        return ally;
    }

    public void markAsImmune() {
        this.immune = true;
    }

    public boolean canBeAttacked() {
        return this.alive && !this.immune;
    }
}
