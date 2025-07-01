package entities.character;

public class Attribute {
    private int strength;
    private int intelligence;
    private int agility;
    private int vigor;
    private int mana;
    private int defense;

    public Attribute(int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.vigor = vigor;
        this.mana = mana;
        this.defense = defense;
    }

    public static Attribute ofWarrior() {
        return new Attribute(9, 2, 4, 9, 2, 8);
    }

    public static Attribute ofWarStandard() {
        return new Attribute(0, 1,1,1,0,1);
    }

    public static Attribute ofMystic() {return new Attribute(1, 8,5,2,7,2);}

    public static Attribute ofMage() {
        return new Attribute(2, 10, 4, 3, 9, 2);
    }

    public static Attribute ofPaladin() {
        return new Attribute(7, 5, 4, 8, 5, 7);
    }

    public static Attribute ofHunter() {
        return new Attribute(4, 4, 9, 4, 3, 3);
    }

    public static Attribute ofGuardian() {return new Attribute(3, 3,2,6,2,8);}

    public static Attribute ofBeast() {return new Attribute(5, 2,8,4,1,3);}

    public void update(Attribute attribute) {
        if (attribute == null) return;
        this.strength += attribute.strength;
        this.intelligence += attribute.intelligence;
        this.agility += attribute.agility;
        this.vigor += attribute.vigor;
        this.mana += attribute.mana;
        this.defense += attribute.defense;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "strength=" + strength +
                ", intelligence=" + intelligence +
                ", agility=" + agility +
                ", vigor=" + vigor +
                ", mana=" + mana +
                ", defense=" + defense +
                '}';
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }
}
