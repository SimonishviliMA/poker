package action;

import models.Card;
import models.cardEnum.CombinationEnum;
import models.cardEnum.SuitCardEnum;
import models.cardEnum.ValueCardEnum;

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

        ValueCardEnum highCardByCombination = getRoyalFlush(cards);
        if (highCardByCombination != null) {
            CombinationEnum royalFlush = CombinationEnum.ROYAL_FLUSH;
            royalFlush.setValueCard(highCardByCombination);
            return royalFlush;
        }

        highCardByCombination = getStraightFlush(cards);
        if (highCardByCombination != null) {
            CombinationEnum straightFlush = CombinationEnum.STRAIGHT_FLUSH;
            straightFlush.setValueCard(highCardByCombination);
            return straightFlush;
        }

        highCardByCombination = getFourOfAKind(cards);
        if (highCardByCombination != null) {
            CombinationEnum fourOfAKind = CombinationEnum.FOUR_OF_A_KIND;
            fourOfAKind.setValueCard(highCardByCombination);
            return fourOfAKind;
        }

        highCardByCombination = getFullHouse(cards);
        if (highCardByCombination != null) {
            CombinationEnum fullHouse = CombinationEnum.FULL_HOUSE;
            fullHouse.setValueCard(highCardByCombination);
            return fullHouse;
        }

        highCardByCombination = getFlush(cards);
        if (highCardByCombination != null) {
            CombinationEnum flush = CombinationEnum.FLUSH;
            flush.setValueCard(highCardByCombination);
            return flush;
        }

        highCardByCombination = getStraight(cards);
        if (highCardByCombination != null) {
            CombinationEnum straight = CombinationEnum.STRAIGHT;
            straight.setValueCard(highCardByCombination);
            return straight;
        }

        highCardByCombination = getThreeOfAKind(cards);
        if (highCardByCombination != null) {
            CombinationEnum threeOfAKind = CombinationEnum.THREE_OF_A_KIND;
            threeOfAKind.setValueCard(highCardByCombination);
            return threeOfAKind;
        }

        highCardByCombination = getTwoPairs(cards);
        if (highCardByCombination != null) {
            CombinationEnum twoPairs = CombinationEnum.TWO_PAIRS;
            twoPairs.setValueCard(highCardByCombination);
            return twoPairs;
        }

        highCardByCombination = getOnePairs(cards);
        if (highCardByCombination != null) {
            CombinationEnum onePair = CombinationEnum.ONE_PAIR;
            onePair.setValueCard(highCardByCombination);
            return onePair;
        }

        highCardByCombination = getHighCard(cards);
        CombinationEnum highCard = CombinationEnum.HIGH_CARD;
        highCard.setValueCard(highCardByCombination);
        return highCard;

    }

    private ValueCardEnum getRoyalFlush(List<Card> cards) {
        //TODO добавить проверку на старшую карту
        return getStraightFlush(cards);
    }

    private ValueCardEnum getStraightFlush(List<Card> cards) {
        ValueCardEnum straight = getStraight(cards);
        ValueCardEnum flush = getFlush(cards);
        if (straight != null && flush != null) {
            return straight;
        }
        return null;
    }

    private ValueCardEnum getFourOfAKind(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        for (Map.Entry<ValueCardEnum, Integer> x : quantity.entrySet()) {
            if (x.getValue() == 4) {
                return x.getKey();
            }
        }
        return null;
    }

    private ValueCardEnum getFullHouse(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        ValueCardEnum threeOfAKind = null;
        ValueCardEnum twoPairs = null;
        for (Map.Entry<ValueCardEnum, Integer> x : quantity.entrySet()) {

            if (x.getValue() == 3) {
                threeOfAKind = x.getKey();

            }
            if (x.getValue() == 2) {
                twoPairs = x.getKey();
            }
        }

        if (threeOfAKind != null && twoPairs != null) {
            return max(threeOfAKind, twoPairs);
        }

        return null;
    }

    private ValueCardEnum getFlush(List<Card> cards) {
        Map<SuitCardEnum, Integer> quantity = new HashMap<>();
        for (Card card : cards) {
            SuitCardEnum value = card.getSuitCard();
            if (quantity.containsKey(value)) {
                quantity.put(value, quantity.get(value) + 1);
            } else {
                quantity.put(value, 1);
            }
        }

        SuitCardEnum suitCardEnum = null;
        for (Map.Entry<SuitCardEnum, Integer> x : quantity.entrySet()) {

            if (x.getValue().equals(5)) {
                suitCardEnum = x.getKey();
                break;
            }
        }

        if (suitCardEnum != null) {
            ValueCardEnum maxValue = ValueCardEnum.TWO;
            for (Card card : cards) {
                if (card.getSuitCard().getValue() == suitCardEnum.getValue() &&
                        maxValue.getValue() < card.getValueCard().getValue()) {

                    maxValue = card.getValueCard();
                }
            }
            return maxValue;
        }
        return null;
    }

    private ValueCardEnum getStraight(List<Card> cards) {
        return null;
    }

    private ValueCardEnum getThreeOfAKind(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        for (Map.Entry<ValueCardEnum, Integer> x : quantity.entrySet()) {
            if (x.getValue() == 3) {
                return x.getKey();
            }
        }
        return null;
    }

    private ValueCardEnum getTwoPairs(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        int count = 0;
        ValueCardEnum firstPair = null;
        ValueCardEnum secondPair = null;
        for (Map.Entry<ValueCardEnum, Integer> x : quantity.entrySet()) {
            if (x.getValue() == 2) {
                count++;
                if (count == 1) {
                    firstPair = x.getKey();
                }
                if (count == 2) {
                    secondPair = x.getKey();
                }
            }
        }
        if (firstPair != null && secondPair != null) {
            return max(firstPair, secondPair);
        }
        return null;
    }

    private ValueCardEnum getOnePairs(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        for (Map.Entry<ValueCardEnum, Integer> x : quantity.entrySet()) {
            if (x.getValue() == 2) {
                return x.getKey();
            }
        }
        return null;
    }

    private ValueCardEnum getHighCard(List<Card> cards) {
        ValueCardEnum maxValue = ValueCardEnum.TWO;
        for (Card card : cards) {
            if (card.getValueCard().getValue() > maxValue.getValue()) {
                maxValue = card.getValueCard();
            }
        }
        return maxValue;
    }

    private Map<ValueCardEnum, Integer> getQuantity(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = new HashMap<>();
        ValueCardEnum value;
        for (Card card : cards) {
            value = card.getValueCard();
            if (quantity.containsKey(value)) {
                quantity.put(value, quantity.get(value) + 1);
            } else {
                quantity.put(value, 1);
            }
        }
        return quantity;
    }

    private ValueCardEnum max(ValueCardEnum x, ValueCardEnum y) {
        return x.getValue() > y.getValue() ? x : y;
    }

}
