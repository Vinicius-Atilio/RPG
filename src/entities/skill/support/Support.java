package entities.skill.support;

import entities.character.Character;
import entities.skill.Skill;

public class Support extends Skill {

    public Support(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void prepareSkillToAttack(Character activePlayer, Character passivePlayer) {

    }

    @Override
    public void executeSelectedSkill(entities.character.Character activePlayer, entities.character.Character passivePlayer) {

    }

    @Override
    public void skillTypeAction(entities.character.Character actionPlayer) {

    }

    @Override
    public void skillAction(entities.character.Character actionPlayer, Character passivePlayer) {

    }

    public static Support ofBlessingOfLight() {
        return new Support("Bênção da Luz",
                "Cura um aliado (sem causar dano).",
                "✨ Uma luz suave envolve o aliado, curando suas feridas!",
                2, false);
    }
}
