package models;

import action.Combination;
import models.cardEnum.CombinationEnum;
import utils.AddCardUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {

    private final String name;
    private final List<Card> cards = new ArrayList<>();
    private int chips = 500;
    private CombinationEnum combination;


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

    public List<Card> getCards() {
        return cards;
    }

    public void resultGame(int chips) {
        this.chips += chips;
    }

    public void setCombination(List<Card> tableCard) {
        List<Card> cards = new ArrayList<>(getCards());
        cards.addAll(tableCard);
        Combination combination = new Combination();
        this.combination = combination.getCombination(cards);
    }

    public CombinationEnum getCombination() {
        return combination;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }
}
