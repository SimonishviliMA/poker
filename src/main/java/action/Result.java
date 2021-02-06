package action;

import models.Player;

import java.util.ArrayList;
import java.util.List;

public class Result {

    public static List<Player> getWinner(List<Player> players) {
        players.sort(Player::compareTo);

        int i = players.size() - 1;
        return new ArrayList<>(List.of(players.get(i)));
    }

}
