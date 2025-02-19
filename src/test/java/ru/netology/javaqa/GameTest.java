import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.Game;
import ru.netology.javaqa.NotRegisteredException;
import ru.netology.javaqa.Player;

public class GameTest {

    Game playersRegistered = new Game();

    Player player1 = new Player(1, "Игрок1", 100);
    Player player2 = new Player(2, "Игрок2", 230);
    Player player3 = new Player(3, "Игрок3", 150);
    Player player4 = new Player(4, "Игрок4", 80);
    Player player5 = new Player(5, "Игрок5", 100);
    Player player6 = new Player(6, "игрок6", 180);

    @Test
    public void compareStrengthUnregisteredBothPlayers() {

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            playersRegistered.round(player1.getName(), player2.getName());
        });
    }

    @Test
    public void compareStrengthUnregisteredSinglePlayer() {
        playersRegistered.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            playersRegistered.round(player2.getName(), player1.getName());
        });
    }

    @Test
    public void comparePlayersStrengthFirstMore() {
        playersRegistered.register(player2);
        playersRegistered.register(player3);

        Assertions.assertEquals(1, playersRegistered.round(player2.getName(), player3.getName()));
    }

    @Test
    public void comparePlayersStrengthFirstLess() {
        playersRegistered.register(player4);
        playersRegistered.register(player6);

        Assertions.assertEquals(2, playersRegistered.round(player4.getName(), player6.getName()));
    }

    @Test
    public void comparePlayersStrengthEqual() {
        playersRegistered.register(player1);
        playersRegistered.register(player5);

        Assertions.assertEquals(0, playersRegistered.round(player1.getName(), player5.getName()));
    }
}