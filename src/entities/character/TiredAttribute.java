package entities.character;

public class TiredAttribute extends Attribute {
    public TiredAttribute(int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(strength, intelligence, agility, vigor, mana, defense);
    }

    @Override
    double calculateDamage(Character actionPlayer, Character passivePlayer, int activeSKillPowerAttack) {
        System.out.println("O jogador " + passivePlayer.getName() + " está cansado! Dano aumentado.");
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.tiredDefenseValue());
    }

    @Override
    public void receiveDamage(double value) {
        this.life -= value * 1.2; // Aumenta o dano recebido por cansaço
    }

    @Override
    public void receiveEffect(String name) {}
}
