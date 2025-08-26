package entities.character;

import entities.Inventory;
import entities.Weapon;
import entities.ally.Ally;
import entities.effect.StatusEffect;
import entities.observer.BattleObserver;
import entities.skill.Skill;
import entities.skill.attack.Trap;
import entities.state.*;
import enums.Race;
import enums.Specialization;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements BattleObserver {
    private BigInteger id;
    private String name;
    private Race race;
    private Specialization specialization;
    private List<Skill> skills;
    // refatorar mandar pro estado do jogador
    private List<StatusEffect> effects;
    private Weapon weapon;
    private Ally ally;
    private BattleObserver enemyObserver;
    private Inventory inventory;
    private State state;

    public Player(String name, Race race, Specialization specialization, Inventory inventory) {
        this.id = new BigInteger(128, new SecureRandom());
        this.name = name;
        this.race = race;
        this.specialization = specialization;
        this.weapon = this.specialization.weapon();
        this.inventory = inventory;
        this.skills = this.specialization.skills();
        this.state = OriginalState.ofState(this.specialization.state());
        this.effects = new ArrayList<>();

        if (this.weapon != null) {
            this.state.update(this.weapon.getState());
        }
    }

    public Player(String name, State state) {
        this.name = name;
        this.state = state;
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
        this.enemyObserver = observer;
    }

    @Override
    public void onNotifyAllyAction(Ally ally, Skill skill) {}

    @Override
    public void onTrapActivated(Trap trap) {
        this.state.receiveDamage(trap, this);
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
    public void onAllyContract(BattleObserver enemyObserver) {
        for (Skill skill : this.skills) {
            if (skill instanceof Ally allySkill) {
                allySkill.contract(this, enemyObserver);
                this.ally = allySkill;
                break;
            }
        }
    }

    public boolean isAlive() {
        return this.state.isAlive();
    }

    public boolean canAttack(Player player2) {
        return this.state.canAttack(this.name) && this.state.isAlive() && player2.state.isAlive();
    }

    public String getName() {
        return name;
    }

    public Skill selectSkill(Player enemy) {
        Skill selectedSkill = this.skills.get(ThreadLocalRandom.current().nextInt(this.skills.size()));
        if (selectedSkill.getCurrentCooldown() == 0) {
            if (selectedSkill instanceof Ally) {
                this.onAllyContract(enemy);
            }
            return selectedSkill;
        }

        return selectSkill(enemy);
    }

    public void receiveDamage(Player activePlayer, int activeSKillPowerAttack, Skill skill) {
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

    public void receiveSpecialDamage(Player activePlayer, int activeSKillPowerAttack, Skill skill) {
        if (isAllyAlive()) {
            this.ally.receiveDamage(activePlayer, this, activeSKillPowerAttack, skill);
            this.receiveDamage(activePlayer, activeSKillPowerAttack, skill);
            return;
        }

        this.receiveDamage(activePlayer, activeSKillPowerAttack, skill);
        skill.skillEffectAction(activePlayer, this);
    }


    public void receiveAllyDamage(Ally ally, Skill skill, Player activePlayer, Player passivePlayer) {
        double damage = this.state.calculateAllyDamage(ally, skill, activePlayer, passivePlayer);
        if (damage <= 0) {
            System.out.println("😱 " + this.name + " conseguiu se defender do ataque da fera de " + activePlayer.getName() + "!");
            return;
        }

        this.state.receiveDamage(activePlayer, passivePlayer, damage, skill);
        skill.skillEffectAction(activePlayer, this);
        System.out.println("😤 " + this.name + " recebeu o dano de " + String.format("%.2f", damage) + " da fera de " + activePlayer.getName() + "!");
    }

    public void receiveAllyHeal(Ally ally, Skill skill, Player activePlayer) {
        double heal = this.state.calculateAllyHeal(ally, skill, activePlayer);
        activePlayer.state.receiveHeal(heal);
        skill.skillEffectAction(activePlayer, this);
    }

    public int getMainAttribute() {
        return switch (this.specialization) {
            case Warrior, Paladin -> this.state.getStrength();
            case Mage -> this.state.getIntelligence();
            case Hunter -> this.state.getAgility();
        };
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
        System.out.println(ally.toString());
        return ally;
    }

    @Override
    public Player getObserver() {
        return this;
    }

    public void changeStateToImmune() {
        this.state = ImmuneState.of(this.state);
    }

    public void changeStateToWrath() {
        this.state = WrathState.of(this.state);
    }

    public void changeStateToTired(State state) {
        this.state = TiredState.of(state);
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

    public int tiredDefenseValue() {
        return this.state.getDefense() - (int) (this.state.getDefense() * 0.5);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public State getState() {
        return state;
    }

    public void removeEffect(StatusEffect statusEffect) {
        this.effects.remove(statusEffect);
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
