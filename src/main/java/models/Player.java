package models;

import models.utils.AddCardUtils;

import java.util.HashSet;
import java.util.Set;

public class Player {

    private final String name;
    private final Set<Card> cards = new HashSet<>();
    private int chips = 500;


    public Player(String name) {
        cards.add(AddCardUtils.getCards());
        cards.add(AddCardUtils.getCards());
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void resultGame(int chips) {
        this.chips += chips;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }
}
