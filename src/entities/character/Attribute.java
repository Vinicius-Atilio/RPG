package entities.character;

public abstract class Attribute {
    protected double life;
    protected int strength;
    protected int intelligence;
    protected int agility;
    protected int vigor;
    protected int mana;
    protected int defense;
    protected int temporallyShield;


    public Attribute(int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        this.life = 100;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.vigor = vigor;
        this.mana = mana;
        this.defense = defense;
    }

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

    public double getLife() {
        return life;
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

    abstract double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack);

    public int getVigor() {
        return vigor;
    }

    public int getMana() {
        return mana;
    }

    public abstract void receiveDamage(double value);
    public abstract void receiveEffect(String name);
}
