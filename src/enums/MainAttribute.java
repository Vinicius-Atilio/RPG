package enums;

import entities.character.Attribute;

public enum MainAttribute {
    Strength,
    Intelligence,
    Agility,
    Vigor,
    Mana,
    Defense;

    public static int ofWarrior(Attribute attribute) {
        return attribute.getStrength();
    }
}
