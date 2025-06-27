package entities;

public class Support extends Skill {

    public Support(String name, String description, String skillAction, int cooldown, boolean special) {
        super(name, description, skillAction, cooldown, special);
    }

    @Override
    public void executeSelectedSkill(Character actionPlayer, Character passivePlayer) {

    }

    @Override
    public void skillTypeAction(Character actionPlayer) {

    }

    @Override
    public void skillAction(Character actionPlayer, Character passivePlayer) {

    }

    public static Support ofBlessingOfLight() {
        return new Support("Bênção da Luz",
                "Cura um aliado (sem causar dano).",
                "✨ Uma luz suave envolve o aliado, curando suas feridas!",
                2, false);
    }
}
