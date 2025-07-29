package entities.ally;

import entities.character.Character;
import entities.skill.Skill;
import entities.state.State;

public abstract class Ally extends Skill {
    private State state;
    private int life;
    private boolean alive;

    public Ally(String name, String description, int cooldown, State state) {
        super(name, description, null, cooldown, false);
        this.state = state;
        this.life = 50;
        this.alive = true;
    }

    public void markAsAlive() {
        this.alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public State getState() {
        return state;
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        System.out.println("\n" + activePlayer.getName() + " evoca o aliado: " + this.name);
        activePlayer.evokeAlly();
        this.skillTypeAction(activePlayer);
        System.out.println("A atenção de " + passivePlayer.getName() + " foi desviada para " + this.name + "!");
    }

    @Override
    public void skillAction(Character actionPlayer, Character passivePlayer) {
        System.out.println("\n" + actionPlayer.getName() + " convoca " + this.getName() + " para a batalha!");
        System.out.println(this.description);
        this.skillTypeAction(actionPlayer);
    }
}
