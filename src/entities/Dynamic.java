package entities;

public class Dynamic extends Skill {
    public Dynamic(String name, String description, String skillAction, int cooldown, boolean special) {
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

    public static Dynamic ofArcaneAmplificationOrCurseOfWeakness() {
        return new Dynamic("Amplificação Arcana/Maldição da Fraqueza",
                "Aumenta dano mágico próprio ou de um aliado por 2 turnos/Reduz ataque do inimigo por 2 turnos",
                "🔮 Uma aura mágica envolve você ou um aliado, aumentando o poder mágico ou enfraquecendo o inimigo.",
                2, false);
    }
}
