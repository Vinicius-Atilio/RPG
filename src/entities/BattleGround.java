package entities;

public class BattleGround {
    private Character player1;
    private Character player2;
    private int turn;

    public BattleGround(Character player1, Character player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Os jogadores são obrigatórios.");
        }

        this.player1 = player1;
        this.player2 = player2;
        this.turn = 0;
    }

    public boolean isGameOver() {
        return this.player1.isAlive() && this.player2.isAlive();
    }

    public void nextTurn() {
        System.out.println();
        System.out.println("\n⏳ A tensão cresce durante a batalha...");
        System.out.println();

        this.turn++;
        this.player1.decreaseAllSkillsCd();
        this.player2.decreaseAllSkillsCd();

        if (this.player1.isAlive()) {
            this.player1.useAllyIfAlive();
            Skill selectedSkill = this.player1.play();
            selectedSkill.executeSelectedSkill(this.player1, this.player2);
            selectedSkill.markAsCasted();
            verifyIfPlayerDied(this.player2, selectedSkill);
        }

        System.out.println();

        if (this.player2.isAlive()) {
            this.player2.useAllyIfAlive();
            Skill selectedSkill = this.player2.play();
            selectedSkill.executeSelectedSkill(this.player2, this.player1);
            selectedSkill.markAsCasted();
            verifyIfPlayerDied(this.player1, selectedSkill);
        }
    }

    private void verifyIfPlayerDied(Character player, Skill selectedSkill) {
        if (player.getLife() <= 0) {
            player.setAlive(false);
            this.playerIsDied(player, selectedSkill);
        }
    }

    private void playerIsDied(Character player, Skill selectedSkill) {

        System.out.println("💥 O " + selectedSkill.getName() + " atinge em cheio!");
        System.out.println("❤️ " + player.getName() + " tinha apenas " + player.getLife() + " de vida restante...");

        System.out.println("\n💀 Ele é derrubado com o impacto. Sua armadura racha e ele cai de joelhos.");
        System.out.println("🕯️ A chama da vida se apaga em seus olhos...");
        System.out.println("⚰️ " + player.getName() + " foi derrotado.");
    }

    public void welcome() {
        System.out.println("⚔️ Bem vindos ao Campo de Batalha Guerreiros! ⚔️");
        System.out.println();
        System.out.println("Sua missão é simples: DERROTE SEU INIMIGO!.\n");
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
