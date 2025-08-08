package entities;

import entities.ally.Ally;
import entities.character.Character;
import entities.observer.BattleInvoker;
import entities.observer.BattleObserver;
import entities.skill.Skill;

import java.util.ArrayList;
import java.util.List;

public class BattleGround implements BattleObserver {
    private final List<BattleObserver> observersList;
    private Character player1;
    private Character player2;
    private BattleInvoker allyInvoker;
    private int turn;

    public BattleGround(Character player1, Character player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Os jogadores são obrigatórios.");
        }

        this.observersList = new ArrayList<>();
        this.player1 = player1;
        this.player2 = player2;
        this.turn = 0;
    }

    public boolean isGameOver() {
        return !this.player1.isAlive() || !this.player2.isAlive();
    }

    public void nextTurn() {
        System.out.println();
        System.out.println("\n⏳ A tensão cresce durante a batalha...");
        System.out.println();

        this.turn++;

        this.player1.prepareToPlay();
        this.player2.prepareToPlay();

        if (this.player1.isAlive()) {
            this.player1.useAllyIfAlive();
            Skill selectedSkill = this.player1.play();
            selectedSkill.prepareSkillToExecute(this.player1, this.player2, this);
            selectedSkill.markAsCasted();
            verifyIfPlayerDied(this.player2, selectedSkill);
        }

        System.out.println();

        if (this.player2.isAlive()) {
            this.player2.useAllyIfAlive();
            Skill selectedSkill = this.player2.play();
            selectedSkill.prepareSkillToExecute(this.player2, this.player1, this);
            selectedSkill.markAsCasted();
            verifyIfPlayerDied(this.player1, selectedSkill);
        }
    }

    private void verifyIfPlayerDied(entities.character.Character player, Skill selectedSkill) {
        if (!player.isAlive()) {
            player.makeDeath();
            this.playerIsDied(player, selectedSkill);
        }
    }

    private void playerIsDied(entities.character.Character player, Skill selectedSkill) {

        System.out.println("💥 O " + selectedSkill.getName() + " atinge em cheio!");
        System.out.println("❤️ " + player.getName() + " tinha apenas " +  String.format("%.2f", player.getLife() > 0 ? player.getLife() : 0 ) + " de vida restante...");

        System.out.println("\n💀 Ele é derrubado com o impacto. Sua armadura racha e ele cai de joelhos.");
        System.out.println("🕯️ A chama da vida se apaga em seus olhos...");
        System.out.println("⚰️ " + player.getName() + " foi derrotado.");
    }

    public void welcome() {
        System.out.println("⚔️ Bem vindos ao Campo de Batalha Guerreiros! ⚔️");
        System.out.println();
        System.out.println("Sua missão é simples: DERROTE SEU INIMIGO!.\n");
    }

    @Override
    public void onAllyInvoked(Ally ally) {
        for (BattleObserver observer : observersList) {
            observer.onNotifyObserver(ally);
        }
    }

    @Override
    public void onAddObserver(BattleObserver observer) {
        this.observersList.add(observer);
    }

    @Override
    public void onNotifyObserver(Ally ally) {
        System.out.println( ally.getIcon() + " Aliado invocado: " + ally.getName());
    }

    public void victory() {
        if (this.player1.isAlive()) {
            this.winner(this.player1);
        }

        if (this.player2.isAlive()) {
            this.winner(this.player2);
        }
    }

    private void winner(Character player) {
        System.out.println("\n🏆 Vitória de " + player.getName() + "!");
        System.out.println("🎁 Recompensas disponíveis no inventário...");
    }
}
