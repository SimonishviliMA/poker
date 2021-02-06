package models;

import action.Combination;
import models.cardEnum.CombinationEnum;
import utils.AddCardUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Player implements Comparable<Player>{

    private final String name;
    private final List<Card> cards = new ArrayList<>();
    private int chips = 500;
    private CombinationEnum combination;

    public Player(String name) {
        cards.add(AddCardUtils.getCards(true));
        cards.add(AddCardUtils.getCards(true));
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
        List<Card> cards = new ArrayList<>(tableCard);
        cards.addAll(getCards());
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

    @Override
    public int compareTo(Player p) {

        int res = combination.getValue() - p.getCombination().getValue();
        if (res != 0) {
            return res;
        } else {
            List<Card> thisPlayerCards = getPlayerCards(combination.getCardsFromCombination());
            List<Card> playerCards = getPlayerCards(p.combination.getCardsFromCombination());
            for (int i = thisPlayerCards.size() - 1; i >= 0; i--) {
                res = thisPlayerCards.get(i).getValueCard().getValue() -
                        playerCards.get(i).getValueCard().getValue();
                if (res != 0) {
                    return res;
                }
            }
        }
        return combination.getKicker().getValue() - p.getCombination().getKicker().getValue();
    }

    private List<Card> getPlayerCards(List<Card> cards) {
        return cards.parallelStream()
                .filter(Card::isPlayerCard)
                .sorted(Comparator.comparingInt(card -> card.getValueCard().getValue()))
                .collect(Collectors.toList());
    }
}
