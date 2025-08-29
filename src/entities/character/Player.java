package entities.character;

import entities.Inventory;
import entities.Weapon;
import entities.ally.Ally;
import entities.effect.StatusEffect;
import entities.observer.Observer;
import entities.observer.Subject;
import entities.skill.Skill;
import entities.skill.attack.Trap;
import entities.state.*;
import entities.turn.Game;
import enums.Race;
import enums.Specialization;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements Subject, Game {
    private List<Observer> observersList;
    private BigInteger id;
    private String name;
    private Race race;
    private Specialization specialization;
    private List<Skill> skills;
    // refatorar mandar pro estado do jogador
    private List<StatusEffect> effects;
    private Weapon weapon;
    private Ally ally;
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
        this.observersList = new ArrayList<>();
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
    public void registerObserver(Observer observer) {
        this.observersList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observersList.remove(observer);
    }

    @Override
    public void onAllyInvoked(Ally ally) {
        this.ally = ally;
        this.observersList.forEach(observer -> observer.onAllyInvoked(this, ally));
    }

    @Override
    public void onAllyAttack(Ally ally) {
        System.out.println("👤 " + this.name + " diz: Meu aliado " + ally.getName() + " está atacando!");
    }

    @Override
    public void onNotifyAllyAction(Ally ally, Skill skill) {}

    @Override
    public void onTrapActivated(Trap trap) {
        this.state.receiveDamage(trap, this);
        this.observersList.forEach(observer -> observer.onTrapDamage(this, trap));
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

    private void onTrapContract(Player enemy) {
        for (Skill skill : this.skills) {
            if (skill instanceof Trap trapSkill && trapSkill.hasBeenExploded()) {
                trapSkill.contract(this, enemy);
                this.observersList.forEach(observer -> observer.onContract(this, trapSkill));
                break;
            }
        }
    }

    @Override
    public void onAllyContract(Subject enemyObserver) {
        for (Skill skill : this.skills) {
            if (skill instanceof Ally allySkill) {
                allySkill.contract(this, enemyObserver);
                this.observersList.forEach(observer -> observer.onContract(this, allySkill));
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

            if (selectedSkill instanceof Trap) {
                this.onTrapContract(enemy);
            }

            return selectedSkill;
        }

        return selectSkill(enemy);
    }

    private void verifyIfPlayerDied(Player activePlayer, Skill skill) {
        if (!this.state.isAlive()) {
            this.observersList.forEach(observer -> observer.onPlayerDied(activePlayer, this, skill));
            return;
        }

        this.observersList.forEach(observer -> observer.onUpdateLifeStatus(this));
    }

    public void receiveDamage(Player activePlayer, double activeSKillPowerAttack, Skill skill) {
        var damage = this.state.calculateDamage(activePlayer, this, activeSKillPowerAttack);
        if (damage <= 0) {
            this.observersList.forEach(observer -> observer.onDefendEnemyAttack(activePlayer, this));
            return;
        }

        this.state.receiveDamage(activePlayer,
                this,
                damage,
                skill);

        this.observersList.forEach(observer -> observer.onReceiveEnemyAttack(activePlayer, this, damage));
        skill.skillEffectAction(activePlayer, this);
        verifyIfPlayerDied(activePlayer, skill);
    }

    public void receiveSpecialDamage(Player activePlayer, double activeSKillPowerAttack, Skill skill) {
        if (isAllyAlive()) {
            this.ally.receiveDamage(activePlayer, this, activeSKillPowerAttack, skill);
            this.receiveDamage(activePlayer, activeSKillPowerAttack, skill);
            this.observersList.forEach(observer -> observer.onReceiveSpecialDamage(activePlayer, this, this.ally));
            return;
        }

        this.receiveDamage(activePlayer, activeSKillPowerAttack, skill);
        skill.skillEffectAction(activePlayer, this);
        verifyIfPlayerDied(activePlayer, skill);
    }

    public void receiveEnemyAllyDamage(Ally ally, Skill skill, Player activePlayer) {
        double damage = this.state.calculateEnemyAllyDamage(ally, skill, activePlayer, this);
        if (damage <= 0) {
            this.observersList.forEach(observer -> observer.onDefendAgainstAllyAttack(this, ally));
            return;
        }

        this.state.receiveDamage(activePlayer, this, damage, skill);
        this.observersList.forEach(observer -> observer.onReceiveEnemyAllyAttack(this, ally, damage));
        skill.skillEffectAction(activePlayer, this);
        verifyIfPlayerDied(activePlayer, skill);
    }

    public void receiveAllyHeal(Ally ally, Skill skill, Player activePlayer) {
        double heal = this.state.calculateAllyHeal(ally, skill, activePlayer);
        activePlayer.state.receiveHeal(heal);
        this.observersList.forEach(observer -> observer.onAllySupport(this, ally, heal));
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
//        this.observersList.forEach(observer -> observer.onAllyUpdateState(this.ally));
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
//        verifyIfPlayerDied(activePlayer, skill);
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
        this.state = DefensiveState.of(this.state);
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
