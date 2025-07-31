package entities;

import entities.state.OriginalState;
import entities.state.State;
import enums.WeaponType;

public class Weapon {
    private String name;
    private String description;
    private State state;
    private WeaponType type;
    private double conversionFactor;

    public Weapon(String name, String description, State state, double conversionFactor) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.conversionFactor = conversionFactor;
    }

    public static Weapon ofHeavySteelSword() {
        return new Weapon("Espada de Aço Pesado",
                "Uma lâmina robusta e balanceada, feita para combates diretos.",
               OriginalState.ofHeavySteelSword(), 1.2);
    }

    public static Weapon ofAncestralArcaneStaff() {
        return new Weapon("Cajado Arcano Ancestral",
                "Um cajado canalizador de magia pura, usado para feitiços complexos.",
                OriginalState.ofAncestralArcaneStaff(), 1.1);
    }

    public static Weapon ofHammerDivineLight() {
        return new Weapon("Martelo da Luz Divina",
                "Uma arma sagrada que combina força bruta com energia protetora.",
               OriginalState.ofHammerDivineLight(), 1.4);
    }

    public static Weapon ofElvenPrecisionBow() {
        return new Weapon("Arco de Precisão Élfico",
                "Um arco leve e preciso, feito com madeira encantada, perfeito para ataques rápidos.",
                OriginalState.ofElvenPrecisionBow(), 2.5);
    }

    public State getState() {
        return state;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}
