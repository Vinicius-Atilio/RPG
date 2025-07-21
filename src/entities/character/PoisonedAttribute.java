package entities.character;

public class PoisonedAttribute extends Attribute {

    public PoisonedAttribute(Attribute attribute) {
        super(attribute.getStrength(), attribute.getIntelligence(), attribute.getAgility(),
                attribute.getVigor(), attribute.getMana(), attribute.getDefense());
    }

    public PoisonedAttribute(int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(strength, intelligence, agility, vigor, mana, defense);
    }

    public static PoisonedAttribute of(Attribute attribute) {
        double factor = 0.9;
        return new PoisonedAttribute(
                (int)(attribute.getStrength() * factor),
                (int)(attribute.getIntelligence() * factor),
                (int)(attribute.getAgility() * factor),
                (int)(attribute.getVigor() * factor),
                (int)(attribute.getMana() * factor),
                (int)(attribute.getDefense() * factor)
        );
    }

    @Override
    public double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack) {
        System.out.println("O jogador " + passivePlayer.getName() + " está envenenado! Dano aumentado.");
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.poisonedDefenseValue());

    }

    @Override
    public void receiveDamage(double value) {
        this.life -= value * 1.1; // Aumenta o dano recebido por envenenamento
    }

    @Override
    public void receiveEffect(String name) {
        this.life = this.life * 0.95; // Reduz a vida em 5% por envenenamento
        System.out.println("O jogador " + name + " está envenenado! Vida reduzida em 5%." + " Vida atual: " + this.life);
    }
}
