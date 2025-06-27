package enums;

import entities.*;

import java.util.List;

public enum Specialization {
    Warrior(Skill.ofWarrior(), Attribute.ofWarrior(), Standard.ofWar(), Weapon.ofHeavySteelSword() , Type.Melee),
    Mage(Skill.ofMage(), Attribute.ofMage(), Arcane.ofMystic(), Weapon.ofAncestralArcaneStaff(), Type.Ranged),
    Hunter(Skill.ofHunter(), Attribute.ofPaladin(), Animal.ofBeast(), Weapon.ofElvenPrecisionBow(), Type.Ranged),
    Paladin(Skill.ofPaladin(), Attribute.ofHunter(), Heavenly.ofGuardian(), Weapon.ofHammerDivineLight(), Type.Melee);

    private List<Skill> skills;
    private Attribute attribute;
    private Type type;
    private Ally ally;
    private Weapon weapon;

    Specialization(List<Skill> skills, Attribute attribute, Ally ally, Weapon weapon, Type type) {
        this.skills = skills;
        this.attribute = attribute;
        this.ally = ally;
        this.weapon = weapon;
        this.type = type;
    }

    public List<Skill> skills() {
        return skills;
    }

    public Attribute attribute() {
        return attribute;
    }

    public Type type() {
        return type;
    }

    public Ally ally() {
        return ally;
    }

    public Weapon weapon() {
        return weapon;
    }
}
