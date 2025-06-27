package entities;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Defense extends Skill {
    private int defensePower;

    public Defense(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
        this.defensePower = defensePower;
    }

    public Defense(String name, String description, String skillAction, int cooldown, int defensePower, boolean special) {
        super(name, description, skillAction, cooldown, special);
        this.defensePower = defensePower;
    }

    @Override
    public void executeSelectedSkill(Character actionPlayer, Character passivePlayer) {
        passivePlayer.makeDefense(actionPlayer, this);
        this.skillAction(actionPlayer, passivePlayer);
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
