package entities.state;

import entities.character.Attribute;
import entities.character.Character;
import entities.effect.StatusEffect;
import entities.skill.Skill;

public class PoisonedState extends State {

    public PoisonedState(int life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(life, strength, intelligence, agility, vigor, mana, defense);
    }

    public static PoisonedState of(State state) {
        double factor = 0.9;
        return new PoisonedState(
                (int)(state.getLife() * factor),
                (int)(state.getStrength() * factor),
                (int)(state.getIntelligence() * factor),
                (int)(state.getAgility() * factor),
                (int)(state.getVigor() * factor),
                (int)(state.getMana() * factor),
                (int)(state.getDefense() * factor)
        );
    }

    @Override
    public double calculateDamage(entities.character.Character actionPlayer, entities.character.Character passivePlayer, int activeSKillPowerAttack) {
        System.out.println("O jogador " + passivePlayer.getName() + " está envenenado! Dano aumentado.");
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.poisonedDefenseValue());

    }

    @Override
    public double calculateDefense() {
        return 0;
    }

    @Override
    public State withLife(double life) {
        this.life = life;
        return this;
    }

    @Override
    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill, StatusEffect statusEffect) {
        System.out.println("O jogador " + passivePlayer.getName() + " está envenenado! Recebendo dano aumentado.");
        this.life -= value * 1.1; // Aumenta o dano recebido por envenenamento
        skill.skillAction(actionPlayer, passivePlayer);
    }

    @Override
    public void receiveEffect(String name) {
        this.life = this.life * 0.95; // Reduz a vida em 5% por envenenamento
        System.out.println("O jogador " + name + " está envenenado! Vida reduzida em 5%." + " Vida atual: " + String.format("%.2f",  this.life ));

    }

    @Override
    public void onDeath(Character character) {
        character.changeState(DeadState.of(new Character("* DEAD " + character.getName(), OriginalState.ofDeath())));
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
