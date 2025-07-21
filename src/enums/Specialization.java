package enums;

import entities.*;
import entities.ally.*;
import entities.character.Attribute;
import entities.character.OriginalAttribute;
import entities.skill.Skill;

import java.util.List;

public enum Specialization {
    Warrior(Skill.ofWarrior(), OriginalAttribute.ofWarrior(), Standard.ofWar(), Weapon.ofHeavySteelSword()),
    Mage(Skill.ofMage(), OriginalAttribute.ofMage(), Arcane.ofMystic(), Weapon.ofAncestralArcaneStaff()),
    Hunter(Skill.ofHunter(), OriginalAttribute.ofPaladin(), Animal.ofBeast(), Weapon.ofElvenPrecisionBow()),
    Paladin(Skill.ofPaladin(), OriginalAttribute.ofHunter(), Heavenly.ofGuardian(), Weapon.ofHammerDivineLight());

    private List<Skill> skills;
    private Attribute attribute;
    private Ally ally;
    private Weapon weapon;

    Specialization(List<Skill> skills, Attribute attribute, Ally ally, Weapon weapon) {
        this.skills = skills;
        this.attribute = attribute;
        this.ally = ally;
        this.weapon = weapon;
    }

    public List<Skill> skills() {
        return skills;
    }

    public Attribute attribute() {
        return attribute;
    }

    public Ally ally() {
        return ally;
    }

    public Weapon weapon() {
        return weapon;
    }
}
