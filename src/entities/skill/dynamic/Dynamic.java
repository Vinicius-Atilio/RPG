package entities.skill.dynamic;

import entities.character.Character;
import entities.skill.Skill;

public class Dynamic extends Skill {
    public Dynamic(String name, String description, String skillAction, int cooldown, boolean special) {
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

    public static Dynamic ofArcaneAmplificationOrCurseOfWeakness() {
        return new Dynamic("Amplificação Arcana/Maldição da Fraqueza",
                "Aumenta dano mágico próprio ou de um aliado por 2 turnos/Reduz ataque do inimigo por 2 turnos",
                "🔮 Uma aura mágica envolve você ou um aliado, aumentando o poder mágico ou enfraquecendo o inimigo.",
                2, false);
    }
}
