package entities.skill;

import entities.character.Character;

public class Support extends Skill {

    public Support(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void executeSelectedSkill(entities.character.Character actionPlayer, entities.character.Character passivePlayer) {

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
