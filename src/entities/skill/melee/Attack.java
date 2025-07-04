package entities.skill.melee;

import entities.character.Character;
import entities.skill.Skill;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Attack extends Skill {
    private int powerAttack;

    protected Attack(String name, String description, String skillAction,
                     int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    protected Attack(String name, String description, String skillAction,
                     int cooldown, int powerAttack, boolean special) {
        super(name, description, skillAction, cooldown, special);
        this.powerAttack = powerAttack;
    }

    protected Attack(String name, String description, String skillAction,
                     int cooldown, boolean stunned, boolean special) {
        super(name, description, skillAction, cooldown, stunned, special);
    }


    @Override
    public void executeSelectedSkill(Character actionPlayer, Character passivePlayer) {
        boolean isAttacked = passivePlayer.makeAttack(actionPlayer, passivePlayer, this.powerAttack);
        if (isAttacked) {
            this.skillAction(actionPlayer, passivePlayer);
        } else {
            System.out.println("\n" + actionPlayer.getName() + " não conseguiu atacar " + passivePlayer.getName() + " com a habilidade: " + this.name);
            System.out.println("😞 " + actionPlayer.getName() + " " + answerVegeanceList.get(ThreadLocalRandom.current().nextInt(answerVegeanceList.size())));
        }
    }

    @Override
    public void skillAction(Character actionPlayer, Character passivePlayer) {
        System.out.println("\n" + actionPlayer.getName() + " Ataca com a sua a habilidade: " + this.name);
        System.out.println(this.description);
        this.skillTypeAction(actionPlayer);
        System.out.println("💢" + actionPlayer.getName() + " " + voiceActionList.get(ThreadLocalRandom.current().nextInt(voiceActionList.size())));
        System.out.println("💥" + actionPlayer.getName() +" " + hitActionList.get(ThreadLocalRandom.current().nextInt(hitActionList.size())));
        System.out.println("🩸 Sangue escorre... " + actionPlayer.getName() +  hitEffectList.get(ThreadLocalRandom.current().nextInt(hitEffectList.size())));
        System.out.println(" 😡🔪 " + passivePlayer.getName() + " " + answerVegeanceList.get(ThreadLocalRandom.current().nextInt(answerVegeanceList.size())));
        System.out.println("🛡️ " + passivePlayer.getName() + " agora possui " + String.format("%.2f", passivePlayer.getLife() > 0 ? passivePlayer.getLife() : 0 ) + " de vida.");

    }
}
