import builders.CharacterBuilder;
import entities.BattleGround;
import entities.character.Player;
import enums.Specialization;

public class Main {
    public static void main(String[] args) {
        Player player1 = new CharacterBuilder()
                .withSpecialization(Specialization.Hunter)
                .build();
        Player player2 = new CharacterBuilder()
                .build();

        BattleGround battle = new BattleGround(player1, player2);
        battle.welcome();
        player1.registerObserver(battle);
        player2.registerObserver(battle);

        while (!battle.isGameOver()) {
            battle.onTurnStart();
            battle.nextTurn();
        }

        battle.victory();
    }
}