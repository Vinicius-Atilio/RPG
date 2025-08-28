package entities;

import entities.ally.Ally;
import entities.character.Player;
import entities.observer.Observer;
import entities.skill.Skill;
import entities.skill.attack.Trap;
import entities.turn.Game;

import java.util.ArrayList;
import java.util.List;

public class BattleGround implements Observer, Game {
    private Player player1;
    private Player player2;
//    private BattleInvoker allyInvoker;
    private List<Trap> traps;
    private List<Ally> allies;
    private int turn;

    public BattleGround(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Os jogadores são obrigatórios.");
        }

        this.player1 = player1;
        this.player2 = player2;
        this.turn = 0;
    }

    public boolean isGameOver() {
        return !this.player1.isAlive() || !this.player2.isAlive();
    }

    public void nextTurn() {
        if (this.player1.canAttack(this.player2)) {
            System.out.println("\n⏳ Ação de " + this.player1.getName());
            Skill selectedSkill = this.player1.selectSkill(this.player2);
            selectedSkill.prepareSkillToExecute(this.player1, this.player2, this);
            selectedSkill.markAsCasted();
        }

        System.out.println();

        if (this.player2.canAttack(this.player1)) {
            System.out.println("\n⏳ Ação de " + this.player2.getName());
            Skill selectedSkill = this.player2.selectSkill(this.player1);
            selectedSkill.prepareSkillToExecute(this.player2, this.player1, this);
            selectedSkill.markAsCasted();
        }
    }

    public void welcome() {
        System.out.println("⚔️ Bem vindos ao Campo de Batalha Guerreiros! ⚔️");
        System.out.println();
        System.out.println("Sua missão é simples: DERROTE SEU INIMIGO!.\n");
    }

    @Override
    public void onTurnStart() {
        this.turn++;
        System.out.println("🔄 Iniciando o turno " + this.turn + "...");
        System.out.println("⚔️ " + this.player1.getName() + " vs " + this.player2.getName());
        System.out.println(this.player1.getName() + " - " + this.player1.getSpecialization() +  " ❤️ " + String.format("%.2f", this.player1.getLife()));
        System.out.println(this.player2.getName() + " - " + this.player2.getSpecialization() +  " ❤️ " + String.format("%.2f", this.player2.getLife()));
        this.player1.onTurnStart();
        this.player2.onTurnStart();

        if (this.hasTraps()) {
            List<Trap> trapsToRemove = new ArrayList<>();

            for (Trap trap : this.traps) {
                if (trap.canBeExplode()) {
                    this.onTrapActivated(trap);
                    trapsToRemove.add(trap);
                }
            }

            this.traps.removeAll(trapsToRemove);
        }

        if (this.hasAllies()) {
            for (Ally ally : this.allies) {
                ally.doAction(this);
            }
        }
    }

    public void onTrapActivated(Trap trap) {
        System.out.println("🪤 Armadilha ativada: " + trap.getName());
        trap.skillEffectAction(this.player1, this.player2);
        trap.applyDamage();
        trap.markAsCasted();
    }

    public void victory() {
        if (this.player1.isAlive()) {
            this.winner(this.player1);
        }

        if (this.player2.isAlive()) {
            this.winner(this.player2);
        }
    }

    private void winner(Player player) {
        System.out.println("\n🏆 Vitória de " + player.getName() + "!");
        System.out.println("🎁 Recompensas disponíveis no inventário...");
    }

    public void addTrap(Trap trap) {
        if (trap == null) {
            throw new IllegalArgumentException("A armadilha não pode ser nula.");
        }

        if (this.traps == null) {
            this.traps = new ArrayList<>();
        }

        this.traps.add(trap);
    }

    public void addAlly(Ally ally) {
        if (ally == null) {
            throw new IllegalArgumentException("O aliado não pode ser nulo.");
        }

        if (this.allies == null) {
            this.allies = new ArrayList<>();
        }

        this.allies.add(ally);
    }

    private boolean hasTraps() {
        return this.traps != null && !this.traps.isEmpty();
    }

    private boolean hasAllies() {
        return this.allies != null && !this.allies.isEmpty();
    }

    @Override
    public void update(Player player, Trap trap) {
        System.out.println("👤 Campo de batalha notifica: O jogador " + player.getName() + " sofreu dano da armadilha " + trap.getName() + "!");
    }

    @Override
    public void update(Player player, Ally ally) {
        System.out.println("👤 Campo de batalha notifica: O jogador " + player.getName() + " contratou seu aliado " + ally.getName() + "!");
    }
}
