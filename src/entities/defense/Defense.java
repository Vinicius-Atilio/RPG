package entities.defense;

import entities.character.Character;
import entities.skill.Skill;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Defense extends Skill {
    private int defensePower;

    public Defense() {super();}

    public Defense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    public Defense(String name, String description, String skillAction, int cooldown, int defensePower, boolean special) {
        super(name, description, skillAction, cooldown, special);
        this.defensePower = defensePower;
    }

    @Override
    public void executeSelectedSkill(Character activePlayer, Character passivePlayer) {
        passivePlayer.makeDefense(activePlayer, this);
        this.skillAction(activePlayer, passivePlayer);
    }

    @Override
    public void skillAction(Character actionPlayer, Character passivePlayer) {
        System.out.println("\n" + actionPlayer.getName() + " usa a habilidade de defesa: " + this.name);
        System.out.println(this.description);
        this.skillTypeAction(actionPlayer);
        System.out.println("💪 " + actionPlayer.getName() + " " + voiceActionList.get(ThreadLocalRandom.current().nextInt(voiceActionList.size())));
        System.out.println("😤 " + passivePlayer.getName() + " sente o impacto da defesa!");
    }
}
