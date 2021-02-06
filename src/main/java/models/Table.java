package models;

import utils.AddCardUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table {

    private final List<Card> cards = new ArrayList<>();

    public Table() {
        cards.add(AddCardUtils.getCards(false));
        cards.add(AddCardUtils.getCards(false));
        cards.add(AddCardUtils.getCards(false));
    }

    public void addCard(){
        cards.add(AddCardUtils.getCards(false));
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Table{" +
                "cards=" + cards +
                '}';
    }
}
