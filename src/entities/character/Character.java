package entities.character;

import entities.Inventory;
import entities.Weapon;
import entities.ally.Ally;
import entities.effect.StatusEffect;
import entities.observer.BattleObserver;
import entities.skill.Skill;
import entities.skill.ally.AllyAttack;
import entities.skill.hunter.ally.BeastAttack;
import entities.skill.attack.Trap;
import entities.skill.hunter.ally.BeastHeal;
import entities.state.*;
import enums.Race;
import enums.Specialization;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Character implements BattleObserver {
    private BigInteger id;
    private String name;
    private Race race;
    private Specialization specialization;
    private List<Skill> skills;
    private List<StatusEffect> effects;
    private Weapon weapon;
    private Ally ally;
    private Inventory inventory;
    private State state;
    private boolean immune;

    public Character(String name, Race race, Specialization specialization, State state, List<Skill> skills, Weapon weapon, Inventory inventory) {
        this.id = new BigInteger(128, new SecureRandom());
        this.name = name;
        this.race = race;
        this.specialization = specialization;
        this.skills = skills;
        this.weapon = weapon;
        this.inventory = inventory;
        this.state = OriginalState.ofState(state);
        this.effects = new ArrayList<>();

        if (this.weapon != null) {
            this.state.update(this.weapon.getState());
        }
    }

    public Character(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public boolean isAlive() {
        return this.state.isAlive();
    }

    public String getName() {
        return name;
    }

    public void prepareToPlay() {

    }

    public Skill selectSkill() {
        Skill selectedSkill = this.skills.get(ThreadLocalRandom.current().nextInt(this.skills.size()));
        if (selectedSkill.getCurrentCooldown() == 0) {
            return selectedSkill;
        }

        return selectSkill();
    }

    public void receiveDamage(Character activePlayer, int activeSKillPowerAttack, Skill skill) {
        var damage = this.state.calculateDamage(activePlayer, this, activeSKillPowerAttack);
        if (damage <= 0) {
            System.out.println("😱 " + this.name + " conseguiu se defender do ataque de " + activePlayer.getName() + "!");
            return;
        }

        this.state.receiveDamage(activePlayer,
                this,
                damage,
                skill);

        System.out.println("😤 " + this.name+ " recebeu o dano de " + String.format("%.2f", damage) + " de " + activePlayer.getName() + "!");
        skill.skillEffectAction(activePlayer, this);
    }

    public void receiveSpecialDamage(Character activePlayer, int activeSKillPowerAttack, Skill skill) {
        if (this.ally.isAlive()) {
            this.ally.receiveDamage(activePlayer, this, activeSKillPowerAttack, skill);
            this.receiveDamage(activePlayer, activeSKillPowerAttack, skill);
            return;
        }

        this.receiveDamage(activePlayer, activeSKillPowerAttack, skill);
    }


    public void receiveAllyDamage(Ally ally, Skill skill, Character activePlayer, Character passivePlayer) {
        double damage = this.state.calculateAllyDamage(ally, skill, activePlayer, passivePlayer);
        if (damage <= 0) {
            System.out.println("😱 " + this.name + " conseguiu se defender do ataque da fera de " + activePlayer.getName() + "!");
            return;
        }

        this.state.receiveDamage(activePlayer, passivePlayer, damage, skill);
        System.out.println("😤 " + this.name + " recebeu o dano de " + String.format("%.2f", damage) + " da fera de " + activePlayer.getName() + "!");
    }

    public void receiveAllyHeal(Ally ally, Skill skill, Character activePlayer) {
        double heal = this.state.calculateAllyHeal(ally, skill, activePlayer);
        activePlayer.state.receiveHeal(heal);
    }

    public int getMainAttribute() {
        return switch (this.specialization) {
            case Warrior, Paladin -> this.state.getStrength();
            case Mage -> this.state.getIntelligence();
            case Hunter -> this.state.getAgility();
        };
    }

    public void makeDefense(Character actionPlayer, Skill skill) {
        skill.updateSkillCooldown();
    }

    public void addEffect(StatusEffect statusEffect) {
        this.effects.add(statusEffect);
    }

    public void applyEffect() {
        if (this.effects == null || this.effects.isEmpty()) {
            return;
        }

        // Atualiza a duração dos efeitos ativos
        for (StatusEffect effect : new ArrayList<>(this.effects)) {
            effect.updateTurnDuration(this, effect.getName());
        }

        // Verifica se algum efeito foi adicionado e ainda nao esta ativo
        for (StatusEffect effect : this.effects) {
            if (!effect.isActive() && this.effects.stream().noneMatch(e -> e.getId().equals(effect.getId()))) {
                System.out.println("Efeito " + effect.getName() + " aplicado a " + this.name + ".");
                effect.accept(this);
                effect.makeActive();
            }
        }

    }

    public double getLife() {
        return this.state.getLife();
    }


    public void receiveEffect(double value, String effectName) {
        System.out.println("O jogador " + this.name + " está sob efeito de " + effectName + " Vida reduzida em 5%." + " Vida atual: " + String.format("%.2f",  this.getLife() ));
        this.state.receiveDamage(value, this, effectName);
    }

    public Ally getAlly() {
        return ally;
    }

    public void makeImmune() {
        this.state = ImmuneState.of(this.state);
    }

    public void changeStateToStunned() {
        this.state = StunnedState.of(this.state);
    }

    public void changeStateToEvasion() {
        this.state = EvasionState.of(this.state);
    }

    public void changeStateToDefensive() {

    }

    public void makeDeath() {
        this.state.onDeath(this);
    }

    public void changeState(State state) {
        this.state = state;
    }
    
    public double weaponFactor() {
        return this.weapon.getConversionFactor();
    }

    public int originalDefenseValue() {
        return this.state.getDefense();
    }

    public int poisonedDefenseValue() {
        return this.state.getDefense() - (int) (this.state.getDefense() * 0.1);
    }

    public int tiredDefenseValue() {
        return this.state.getDefense() - (int) (this.state.getDefense() * 0.5);
    }

    public BigInteger getId() {
        return id;
    }

    public Race getRace() {
        return race;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public State getState() {
        return state;
    }

    public boolean isImmune() {
        return immune;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", race=" + race +
                ", specialization=" + specialization +
                ", attribute=" + state +
                ", skills=" + skills +
                ", weapon=" + weapon +
                ", ally=" + ally +
                ", inventory=" + inventory +
                ", life=" + this.state.isAlive() +
                '}';
    }

    public void removeEffect(StatusEffect statusEffect) {
        this.effects.remove(statusEffect);
    }

    public void makeSupport(Character passivePlayer, Skill support) {

    }

    @Override
    public void onTurnStart() {
        this.state.stateCountDown(this, this.specialization.state(this.state.getLife()));
        this.applyEffect();
        this.skills.forEach(Skill::updateSkillCooldown);
    }

    @Override
    public void onAllyInvoked(Ally ally) {
        System.out.println("👤 " + this.name + " diz: Um aliado chegou! " + ally.getName() + " e está no campo de batalha .");
    }

    @Override
    public void onAllyAttack(Ally ally) {
        System.out.println("👤 " + this.name + " diz: Meu aliado " + ally.getName() + " está atacando!");
    }

    @Override
    public void onAddObserver(BattleObserver observer) {

    }

    @Override
    public void onNotifyAllyAction(Ally ally, Skill skill) {

    }

    @Override
    public void onTrapActivated(Trap trap) {
        this.state.receiveDamage(trap);
        System.out.println("👤 " + this.name + " diz: Você me pagará por isso!");
    }

    @Override
    public void onReceiveAllyAttack(Ally ally, Skill skill) {
        System.out.println("👤 " + this.name + " diz: O aliado " + ally.getName() + " está me atacando com " + skill.getName() + "!");
    }

    @Override
    public void onAllySupport(Ally ally) {

    }

    @Override
    public void onAllyUpdateState(Ally ally) {
        this.state.update(ally.getState());
    }

    @Override
    public Character getObserver() {
        return this;
    }

    public void changeStatusToWrath() {
    }

    public int getDefense() {
        return this.state.getDefense();
    }

    public boolean isAllyAlive() {
        return this.ally != null && this.ally.isAlive();
    }

    public void buffAlly() {

    }

    public void removeBuffStateBy(Ally ally) {
        this.state.update(OriginalState.ofState(ally.getState()));
    }

    public void changeStateToParalyzed() {
    }
}
