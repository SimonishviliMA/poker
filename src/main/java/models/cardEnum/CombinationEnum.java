package models.cardEnum;

import models.Card;

import java.util.Comparator;
import java.util.List;

public enum CombinationEnum {

    HIGH_CARD(0, "High card"),
    ONE_PAIR(1, "One pair"),
    TWO_PAIRS(2, "Two pairs"),
    THREE_OF_A_KIND(3, "Three of a kind"),
    STRAIGHT(4, "Straight"),
    FLUSH(5, "Flush"),
    FULL_HOUSE(6, "Full house"),
    FOUR_OF_A_KIND(7, "Four of a kind"),
    STRAIGHT_FLUSH(8, "Straight flush"),
    ROYAL_FLUSH(9, "Royal flush");

    private final int value;
    private final String name;
    private List<Card> cardsFromCombination;
    private ValueCardEnum maxValueCard;

    CombinationEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public void setMaxValueCard(ValueCardEnum maxValueCard) {
        this.maxValueCard = maxValueCard;
    }

    public List<Card> getCardsFromCombination() {
        return cardsFromCombination;
    }

    public void setCardsFromCombination(List<Card> cardsFromCombination) {
        cardsFromCombination.sort(Comparator.comparingInt(x -> x.getValueCard().getValue()));
        this.maxValueCard = cardsFromCombination.get(cardsFromCombination.size() - 1).getValueCard();
        this.cardsFromCombination = cardsFromCombination;
    }

    public int getValue() {
        return value;
    }

    public ValueCardEnum getMaxValueCard() {
        return maxValueCard;
    }

    public String getName() {
        return name;
    }
}
