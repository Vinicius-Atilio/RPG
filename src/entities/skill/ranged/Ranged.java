package entities.skill.ranged;

import entities.character.Character;
import entities.skill.attack.Attack;

public class Ranged extends Attack {

    protected Ranged(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    protected Ranged(String name, String description, String skillAction, int cooldown, int powerAttack, boolean special) {
        super(name, description, skillAction, cooldown, powerAttack, special);
    }

    protected Ranged(String name, String description, String skillAction, int cooldown, boolean stunned, boolean special) {
        super(name, description, skillAction, cooldown, stunned, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {

    }

    @Override
    public void skillTypeAction(Character actionPlayer) {

    }
}
