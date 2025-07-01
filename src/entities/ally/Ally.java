package entities.ally;

import entities.character.Attribute;
import entities.character.Character;
import entities.skill.Skill;

public abstract class Ally extends Skill {
    private Attribute attribute;
    private int life;
    private boolean alive;

    public Ally(String name, String description, int cooldown, Attribute attribute) {
        super(name, description, null, cooldown, false);
        this.attribute = attribute;
        this.life = 50;
        this.alive = true;
    }

    public void markAsAlive() {
        this.alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public void executeSelectedSkill(Character actionPlayer, Character passivePlayer) {
        System.out.println("\n" + actionPlayer.getName() + " evoca o aliado: " + this.name);
        actionPlayer.evokeAlly();
        this.skillTypeAction(actionPlayer);
        System.out.println("A atenção de " + passivePlayer.getName() + " foi desviada para " + this.name + "!");
    }

    @Override
    public void skillAction(Character actionPlayer, Character passivePlayer) {
        System.out.println("\n" + actionPlayer.getName() + " convoca " + this.getName() + " para a batalha!");
        System.out.println(this.description);
        this.skillTypeAction(actionPlayer);
    }
}
