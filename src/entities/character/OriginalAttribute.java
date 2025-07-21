package entities.character;

public class OriginalAttribute extends Attribute {
    public OriginalAttribute(int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(strength, intelligence, agility, vigor, mana, defense);
    }

    @Override
    double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack) {
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.originalDefenseValue());
    }

    @Override
    public void receiveDamage(double value) {
        this.life -= value;
    }

    @Override
    public void receiveEffect(String name) {}

    public static OriginalAttribute ofWarrior() {
        return new OriginalAttribute(9, 2, 4, 9, 2, 8);
    }

    public static OriginalAttribute ofWarStandard() {
        return new OriginalAttribute(0, 1,1,1,0,1);
    }

    public static OriginalAttribute ofMystic() {return new OriginalAttribute(1, 8,5,2,7,2);}

    public static OriginalAttribute ofMage() {
        return new OriginalAttribute(2, 10, 4, 3, 9, 2);
    }

    public static OriginalAttribute ofPaladin() {
        return new OriginalAttribute(7, 5, 4, 8, 5, 7);
    }

    public static OriginalAttribute ofHunter() {
        return new OriginalAttribute(4, 4, 9, 4, 3, 3);
    }

    public static OriginalAttribute ofGuardian() {return new OriginalAttribute(3, 3,2,6,2,8);}

    public static OriginalAttribute ofBeast() {return new OriginalAttribute(5, 2,8,4,1,3);}

    public static OriginalAttribute ofDeath() {return new OriginalAttribute(0, 0, 0, 0, 0, 0);}

    public static OriginalAttribute ofHeavySteelSword() {
        return new OriginalAttribute(3, 0, 1, 1, 0, 1);
    }

    public static OriginalAttribute ofAncestralArcaneStaff() {
        return new OriginalAttribute(1, 4, 2, 1, 5, 1);
    }

    public static OriginalAttribute ofHammerDivineLight() {
        return new OriginalAttribute(2, 0, 0, 1, 2, 2);
    }

    public static OriginalAttribute ofElvenPrecisionBow() {
        return new OriginalAttribute(2, 0, 3, 1, 0, 1);
    }

}
