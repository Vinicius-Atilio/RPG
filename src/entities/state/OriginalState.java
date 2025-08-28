package entities.state;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;

public class OriginalState extends State {

    public OriginalState(double life, int strength, int intelligence, int agility, int vigor, int mana, int defense) {
        super(life, strength, intelligence, agility, vigor, mana, defense);
    }

    @Override
    public double calculateDamage(Player activePlayer, Player passivePlayer, double activeSKillPowerAttack) {
        double damage = activeSKillPowerAttack + (activePlayer.getMainAttribute() * activePlayer.weaponFactor())
                - passivePlayer.originalDefenseValue();
        return Math.max(damage, 0);
    }

    @Override
    public double calculateAllyDamage(Ally ally, Skill skill, Player actionPlayer, Player passivePlayer) {
        double damage = (ally.getAllyPower() + skill.getActiveSkillPowerAttack()
                + (actionPlayer.getMainAttribute() * ally.getInvokerPower()) * ally.getSkillMultiplier())
                - passivePlayer.originalDefenseValue();

        return Math.max(damage, 0);
    }

    @Override
    public void receiveEffect(String name) {}

    @Override
    public void onDeath(Player player) {
        player.changeState(DeadState.of(new Player("* DEAD " + player.getName(), OriginalState.ofDeath())));
    }

    @Override
    public boolean isAlive() {
        return this.life > 0;
    }

    @Override
    public double calculateDefense() {
        return 0;
    }

    @Override
    public State withLife(double life) {
        this.life = life;
        return this;
    }

    @Override
    public void receiveHeal(double value) {
        this.life += value;
    }

    @Override
    public boolean canAttack(String activePlayerName) {
        return true;
    }

    public static OriginalState ofState(State state) {
        return new OriginalState(
                state.getLife(),
                state.getStrength(),
                state.getIntelligence(),
                state.getAgility(),
                state.getVigor(),
                state.getMana(),
                state.getDefense()
        );
    }

    public static OriginalState ofWarrior() {
        return new OriginalState(30, 9, 2, 4, 9, 2, 8);
    }

    public static OriginalState ofWarStandard() {
        return new OriginalState(50, 0, 1,1,1,0,1);
    }

    public static OriginalState ofMystic() {return new OriginalState(50, 1, 8,5,2,7,2);}

    public static OriginalState ofMage() {
        return new OriginalState(100, 2, 10, 4, 3, 9, 2);
    }

    public static OriginalState ofPaladin() {
        return new OriginalState(100, 7, 5, 4, 8, 5, 7);
    }

    public static OriginalState ofHunter() {
        return new OriginalState(30, 4, 4, 9, 4, 3, 3);
    }

    public static OriginalState ofGuardian() {return new OriginalState(50, 3, 3,2,6,2,8);}

    public static OriginalState ofBeast() {return new OriginalState(50, 15, 2,8,4,1,3);}

    public static OriginalState ofDeath() {return new OriginalState(0,0, 0, 0, 0, 0, 0);}

    public static OriginalState ofHeavySteelSword() {
        return new OriginalState(50, 3, 0, 1, 1, 0, 1);
    }

    public static OriginalState ofAncestralArcaneStaff() {
        return new OriginalState(50, 1, 4, 2, 1, 5, 1);
    }

    public static OriginalState ofHammerDivineLight() {
        return new OriginalState(50, 2, 0, 0, 1, 2, 2);
    }

    public static OriginalState ofElvenPrecisionBow() {
        return new OriginalState(50, 2, 0, 3, 1, 0, 1);
    }


    @Override
    public String toString() {
        String stateName = "Original State";
        return "OriginalState{" + stateName + '}';
    }
}
