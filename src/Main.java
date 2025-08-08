import builders.CharacterBuilder;
import entities.BattleGround;
import entities.character.Character;
import enums.Specialization;

public class Main {
    public static void main(String[] args) {
        Character player1 = new CharacterBuilder()
                .withSpecialization(Specialization.Hunter)
                .build();
        Character player2 = new CharacterBuilder()
                .build();

        BattleGround battle = new BattleGround(player1, player2);
        battle.welcome();
        battle.onAddObserver(player1);
        battle.onAddObserver(player2);

        while (!battle.isGameOver()) {
            battle.nextTurn();
        }

        battle.victory();
    }
}