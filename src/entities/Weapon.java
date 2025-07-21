package entities;

import entities.character.Attribute;
import entities.character.OriginalAttribute;
import enums.WeaponType;

public class Weapon {
    private String name;
    private String description;
    private Attribute attribute;
    private WeaponType type;
    private double conversionFactor;

    public Weapon(String name, String description, Attribute attribute, double conversionFactor) {
        this.name = name;
        this.description = description;
        this.attribute = attribute;
        this.conversionFactor = conversionFactor;
    }

    public static Weapon ofHeavySteelSword() {
        return new Weapon("Espada de Aço Pesado",
                "Uma lâmina robusta e balanceada, feita para combates diretos.",
               OriginalAttribute.ofHeavySteelSword(), 1.2);
    }

    public static Weapon ofAncestralArcaneStaff() {
        return new Weapon("Cajado Arcano Ancestral",
                "Um cajado canalizador de magia pura, usado para feitiços complexos.",
                OriginalAttribute.ofAncestralArcaneStaff(), 1.1);
    }

    public static Weapon ofHammerDivineLight() {
        return new Weapon("Martelo da Luz Divina",
                "Uma arma sagrada que combina força bruta com energia protetora.",
               OriginalAttribute.ofHammerDivineLight(), 1.4);
    }

    public static Weapon ofElvenPrecisionBow() {
        return new Weapon("Arco de Precisão Élfico",
                "Um arco leve e preciso, feito com madeira encantada, perfeito para ataques rápidos.",
                OriginalAttribute.ofElvenPrecisionBow(), 1.5);
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}
