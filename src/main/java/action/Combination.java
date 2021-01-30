package action;

import models.Card;
import models.cardEnum.CombinationEnum;

import java.util.*;

public class Combination {

    private boolean straightFlush;
    private boolean fourOfAKind;
    private boolean fullHouse;
    private boolean flush;
    private boolean straight;
    private boolean threeOfAKind;
    private boolean twoPairs;
    private boolean onePairs;

    public CombinationEnum getCombination(List<Card> cards) {
        if (getRoyalFlush(cards)) return CombinationEnum.ROYAL_FLUSH;
        if (getStraightFlush(cards)) return CombinationEnum.STRAIGHT_FLUSH;
        if (getFourOfAKind(cards)) return CombinationEnum.FOUR_OF_A_KIND;
        if (getFullHouse(cards)) return CombinationEnum.FULL_HOUSE;
        if (getFlush(cards)) return CombinationEnum.FLUSH;
        if (getStraight(cards)) return CombinationEnum.STRAIGHT;
        if (getThreeOfAKind(cards)) return CombinationEnum.THREE_OF_A_KIND;
        if (getTwoPairs(cards)) return CombinationEnum.TWO_PAIRS;
        if (getOnePairs(cards)) return CombinationEnum.ONE_PAIR;
        return getHighCard(cards);
    }

    private boolean getRoyalFlush(List<Card> cards) {
        //TODO добавить проверку на старшую карту
        return getStraightFlush(cards);
    }

    private boolean getStraightFlush(List<Card> cards) {
        if (straightFlush) return true;
        if (getStraight(cards) && getFlush(cards)) {
            straightFlush = true;
        }
        return straightFlush;
    }

    private boolean getFourOfAKind(List<Card> cards) {
        if (fourOfAKind) return true;
        Map<Integer, Integer> quantity = getQuantity(cards);
        for (int x : quantity.keySet()) {
            if (quantity.get(x) == 4) {
                fourOfAKind = true;
            }
        }
        return fourOfAKind;
    }

    private boolean getFullHouse(List<Card> cards) {
        if (fullHouse) return true;
        Map<Integer, Integer> quantity = getQuantity(cards);
        boolean flagThree = false;
        boolean flagTwo = false;
        for (int x : quantity.keySet()) {

            if (quantity.get(x) == 3) {
                flagThree = true;
            }
            if (quantity.get(x) == 2) {
                flagTwo = true;
            }
        }

        if (flagThree && flagTwo) {
            fullHouse = true;
        }

        return fullHouse;
    }

    private boolean getFlush(List<Card> cards) {
        if (flush) return true;
        Map<Integer, Integer> quantity = new HashMap<>();
        for (Card card : cards) {
            int value = card.getSuitCard().getValue();
            if (quantity.containsKey(value)) {
                quantity.put(value, quantity.get(value) + 1);
            } else {
                quantity.put(value, 1);
            }
        }
        for (int x : quantity.keySet()) {

            if (quantity.get(x) == 5) {
                flush = true;
            }
        }
        return flush;
    }

    private boolean getStraight(List<Card> cards) {
        return false;
    }

    private boolean getThreeOfAKind(List<Card> cards) {
        if (threeOfAKind) return true;
        Map<Integer, Integer> quantity = getQuantity(cards);
        for (int x : quantity.keySet()) {
            if (quantity.get(x) == 3) {
                threeOfAKind = true;
            }
        }
        return threeOfAKind;
    }

    private boolean getTwoPairs(List<Card> cards) {
        if (twoPairs) return true;
        Map<Integer, Integer> quantity = getQuantity(cards);
        int count = 0;
        for (int x : quantity.keySet()) {
            if (quantity.get(x) == 2) {
                count++;
            }
        }
        if (count == 2) {
            twoPairs = true;
        }
        return twoPairs;
    }

    private boolean getOnePairs(List<Card> cards) {
        if (onePairs) return true;
        Map<Integer, Integer> quantity = getQuantity(cards);
        for (int x : quantity.keySet()) {
            if (quantity.get(x) == 2) {
                onePairs = true;
            }
        }
        return onePairs;
    }

    private CombinationEnum getHighCard(List<Card> cards) {
        return CombinationEnum.HIGH_CARD;
    }

    private Map<Integer, Integer> getQuantity(List<Card> cards) {
        Map<Integer, Integer> quantity = new HashMap<>();
        for (Card card : cards) {
            int value = card.getValueCard().getValue();
            if (quantity.containsKey(value)) {
                quantity.put(value, quantity.get(value) + 1);
            } else {
                quantity.put(value, 1);
            }
        }
        return quantity;
    }
}
