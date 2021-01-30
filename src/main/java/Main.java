import models.Player;
import models.Table;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Table table = new Table();
        table.addCard();
        table.addCard();
        player1.setCombination(table.getCards());
        player2.setCombination(table.getCards());
        System.out.println(table.getCards().toString());
        System.out.println("\n\n");
        System.out.println(player1.getCards().toString());
        System.out.println(player1.getCombination().getName());
        System.out.println("\n\n");
        System.out.println(player2.getCards().toString());
        System.out.println(player2.getCombination().getName());
    }


}
