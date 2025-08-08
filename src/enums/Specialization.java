package enums;

import entities.*;
import entities.ally.*;
import entities.skill.hunter.Animal;
import entities.skill.mage.Arcane;
import entities.skill.paladin.HeavenlyGuardian;
import entities.skill.warrior.Standard;
import entities.state.OriginalState;
import entities.skill.Skill;
import entities.state.State;

import java.util.List;

public enum Specialization {
    Warrior(Skill.ofWarrior(), OriginalState.ofWarrior(), Standard.ofWar(), Weapon.ofHeavySteelSword()),
    Mage(Skill.ofMage(), OriginalState.ofMage(), Arcane.ofMystic(), Weapon.ofAncestralArcaneStaff()),
    Hunter(Skill.ofHunter(), OriginalState.ofPaladin(), Animal.ofBeast(), Weapon.ofElvenPrecisionBow()),
    Paladin(Skill.ofPaladin(), OriginalState.ofHunter(), HeavenlyGuardian.ofGuardian(), Weapon.ofHammerDivineLight());

    private List<Skill> skills;
    private State state;
    private Ally ally;
    private Weapon weapon;

    Specialization(List<Skill> skills, State state, Ally ally, Weapon weapon) {
        this.skills = skills;
        this.state = state;
        this.ally = ally;
        this.weapon = weapon;
    }

    public List<Skill> skills() {
        return skills;
    }

    //alterar para recuperar o estado original com a vida atualizada
    public State state() {
        return state;
    }

    public State state(double life) {
        return this.state.withLife(life);
    }

    public Ally ally() {
        return ally;
    }

    public Weapon weapon() {
        return weapon;
    }
}
