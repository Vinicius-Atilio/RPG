package entities.state;

import entities.character.Character;
import entities.effect.StatusEffect;
import entities.skill.Skill;

public class TiredState extends State {
    public TiredState(int life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(life, strength, intelligence, agility, vigor, mana, defense);
    }

    @Override
    public double calculateDamage(entities.character.Character actionPlayer, entities.character.Character passivePlayer, int activeSKillPowerAttack) {
        System.out.println("O jogador " + passivePlayer.getName() + " está cansado! Dano aumentado.");
        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.tiredDefenseValue());
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
        this.life -= value * 1.2; // Aumenta o dano recebido por cansaço
        skill.skillAction(actionPlayer, passivePlayer);
    }

    @Override
    public void receiveEffect(String name) {}

    @Override
    public void onDeath(Character character) {
        character.changeState(DeadState.of(new Character("* DEAD " + character.getName(), OriginalState.ofDeath())));
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
