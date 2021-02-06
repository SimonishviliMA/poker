import action.Result;
import models.Player;
import models.Table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));
        players.add(new Player("Player3"));
        Table table = new Table();
        table.addCard();
        table.addCard();

        players = players.stream()
                .peek(player -> player.setCombination(table.getCards()))
                .sorted(Comparator.comparingInt(player -> player.getCombination().getValue()))
                .collect(Collectors.toList());
        System.out.println(table.getCards().toString());
        System.out.println("\n\n");
        players.forEach(player -> {
            System.out.println(player.getName());
            System.out.println(player.getCards().toString());
            System.out.println(player.getCombination().getName());
            System.out.println("\n\n");
        });

        List<Player> winners = Result.getWinner(players);
        winners.forEach(winner -> System.out.println(winner.getName() + " win"));
    }
}
