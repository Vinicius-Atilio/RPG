package entities;

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
                new Attribute(3, 0, 1, 1, 0, 1),
                1.2);
    }

    public static Weapon ofAncestralArcaneStaff() {
        return new Weapon("Cajado Arcano Ancestral",
                "Um cajado canalizador de magia pura, usado para feitiços complexos.",
                new Attribute(0, 3, 1, 1, 2, 0),
                1.1);
    }

    public static Weapon ofHammerDivineLight() {
        return new Weapon("Martelo da Luz Divina",
                "Uma arma sagrada que combina força bruta com energia protetora.",
                new Attribute(2, 0, 0, 1, 2, 2),
               1.4);
    }

    public static Weapon ofElvenPrecisionBow() {
        return new Weapon("Arco de Precisão Élfico",
                "Um arco leve e preciso, feito com madeira encantada, perfeito para ataques rápidos.",
                new Attribute(2, 0, 3, 1, 0, 1),
               1.5);
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}
