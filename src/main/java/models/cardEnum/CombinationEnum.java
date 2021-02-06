package models.cardEnum;

import models.Card;

import java.util.ArrayList;
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
    private ValueCardEnum kicker;

    CombinationEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public List<Card> getCardsFromCombination() {
        return cardsFromCombination;
    }

    public void setCardsFromCombination(List<Card> cardsFromCombination) {
        cardsFromCombination.sort(Comparator.comparingInt(x -> x.getValueCard().getValue()));
        this.cardsFromCombination = cardsFromCombination;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public ValueCardEnum getKicker() {
        return kicker;
    }

    public void setKicker(List<Card> cards, List<Card> cardsFromCombination) {
        List<Card> otherCard = new ArrayList<>();
        cards.forEach(x -> {
            boolean have = false;
            for (Card card : cardsFromCombination) {
                if (card.equals(x)) {
                    have = true;
                    break;
                }
            }
            if (!have) {
                otherCard.add(x);
            }
        });
        if (!otherCard.isEmpty()) {
            otherCard.sort(Comparator.comparingInt(card -> card.getValueCard().getValue()));
            this.kicker = otherCard.get(otherCard.size() - 1).getValueCard();
        }
    }

    public void setKicker(ValueCardEnum kicker) {
        this.kicker = kicker;
    }

    public boolean equals(CombinationEnum o){
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        return o.getValue() == this.getValue() &&
                o.getCardsFromCombination().equals(this.getCardsFromCombination());
    }
}
