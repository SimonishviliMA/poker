package models;

import models.utils.AddCardUtils;

import java.util.HashSet;
import java.util.Set;

public class Table {

    private final Set<Card> cards = new HashSet<>();

    public Table() {
        cards.add(AddCardUtils.getCards());
        cards.add(AddCardUtils.getCards());
        cards.add(AddCardUtils.getCards());
    }

    public void addCard(){
        cards.add(AddCardUtils.getCards());
    }

    public Set<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Table{" +
                "cards=" + cards +
                '}';
    }
}
