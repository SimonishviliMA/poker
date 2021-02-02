package action;

import models.Card;
import models.cardEnum.CombinationEnum;
import models.cardEnum.SuitCardEnum;
import models.cardEnum.ValueCardEnum;

import java.util.*;

public class Combination {

    public CombinationEnum getCombination(List<Card> cards) {

        cards.sort(Comparator.comparingInt(x -> x.getValueCard().getValue()));

        List<Card> cardsCombination = getRoyalFlush(cards);
        if (cardsCombination != null) {
            CombinationEnum royalFlush = CombinationEnum.ROYAL_FLUSH;
            royalFlush.setCardsFromCombination(cardsCombination);
            return royalFlush;
        }

        cardsCombination = getStraightFlush(cards);
        if (cardsCombination != null) {
            CombinationEnum straightFlush = CombinationEnum.STRAIGHT_FLUSH;
            straightFlush.setCardsFromCombination(cardsCombination);
            return straightFlush;
        }

        cardsCombination = getFourOfAKind(cards);
        if (cardsCombination != null) {
            CombinationEnum fourOfAKind = CombinationEnum.FOUR_OF_A_KIND;
            fourOfAKind.setCardsFromCombination(cardsCombination);
            return fourOfAKind;
        }

        cardsCombination = getFullHouse(cards);
        if (cardsCombination != null) {
            CombinationEnum fullHouse = CombinationEnum.FULL_HOUSE;
            fullHouse.setCardsFromCombination(cardsCombination);
            return fullHouse;
        }

        cardsCombination = getFlush(cards);
        if (cardsCombination != null) {
            CombinationEnum flush = CombinationEnum.FLUSH;
            flush.setCardsFromCombination(cardsCombination);
            return flush;
        }

        cardsCombination = getStraight(cards);
        if (cardsCombination != null) {
            CombinationEnum straight = CombinationEnum.STRAIGHT;
            straight.setCardsFromCombination(cardsCombination);
            return straight;
        }

        cardsCombination = getThreeOfAKind(cards);
        if (cardsCombination != null) {
            CombinationEnum threeOfAKind = CombinationEnum.THREE_OF_A_KIND;
            threeOfAKind.setCardsFromCombination(cardsCombination);
            return threeOfAKind;
        }

        cardsCombination = getTwoPairs(cards);
        if (cardsCombination != null) {
            CombinationEnum twoPairs = CombinationEnum.TWO_PAIRS;
            twoPairs.setCardsFromCombination(cardsCombination);
            return twoPairs;
        }

        cardsCombination = getOnePairs(cards);
        if (cardsCombination != null) {
            CombinationEnum onePair = CombinationEnum.ONE_PAIR;
            onePair.setCardsFromCombination(cardsCombination);
            return onePair;
        }

        CombinationEnum highCard = CombinationEnum.HIGH_CARD;
        highCard.setMaxValueCard(getHighCard(cards).getValueCard());
        return highCard;

    }

    private List<Card> getRoyalFlush(List<Card> cards) {
        //TODO добавить проверку
        return null;
    }

    private List<Card> getStraightFlush(List<Card> cards) {
        List<Card> flush = getFlush(cards);
        return flush != null ? getStraight(flush) : null;
    }

    private List<Card> getFourOfAKind(List<Card> cards) {
        Map<Card, Integer> quantity = getQuantity(cards);
        for (Map.Entry<Card, Integer> x : quantity.entrySet()) {
            if (x.getValue() == 4) {
                return getCardCombination(x.getKey(), cards);
            }
        }
        return null;
    }

    private List<Card> getFullHouse(List<Card> cards) {
        Map<Card, Integer> quantity = getQuantity(cards);
        Card threeOfAKind = null;
        Card twoPairs = null;
        for (Map.Entry<Card, Integer> x : quantity.entrySet()) {

            if (x.getValue() == 3) {
                threeOfAKind = x.getKey();

            }
            if (x.getValue() == 2) {
                twoPairs = x.getKey();
            }
        }

        return addSomeCardAndSort(threeOfAKind, twoPairs, cards);
    }

    private List<Card> getFlush(List<Card> cards) {
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
            List<Card> flushCards = new ArrayList<>();
            for (Card card : cards) {
                if (card.getSuitCard().equals(suitCardEnum)) {
                    flushCards.add(card);
                }
            }
            return flushCards;
        }
        return null;
    }

    private List<Card> getStraight(List<Card> cards) {
        int count = 1;
        int prevCard = cards.get(0).getValueCard().getValue();
        Card card;
        List<Card> result = new ArrayList<>(Collections.singletonList(cards.get(0)));
        for (int i = 1; i < cards.size(); i++) {
            card = cards.get(i);
            if (card.getValueCard().getValue() - prevCard == 1) {
                count++;
            } else {
                count = 1;
                result.clear();
            }
            result.add(card);
            if (count == 5) {
                return result;
            }
            prevCard = card.getValueCard().getValue();
        }
        return null;
    }

    private List<Card> getThreeOfAKind(List<Card> cards) {
        Map<Card, Integer> quantity = getQuantity(cards);
        for (Map.Entry<Card, Integer> x : quantity.entrySet()) {
            if (x.getValue() == 3) {
                return getCardCombination(x.getKey(), cards);
            }
        }
        return null;
    }

    private List<Card> getTwoPairs(List<Card> cards) {
        Map<Card, Integer> quantity = getQuantity(cards);
        int count = 0;
        Card firstPair = null;
        Card secondPair = null;
        for (Map.Entry<Card, Integer> x : quantity.entrySet()) {
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
        return addSomeCardAndSort(firstPair, secondPair, cards);
    }

    private List<Card> getOnePairs(List<Card> cards) {

        Map<Card, Integer> quantity = getQuantity(cards);
        for (Map.Entry<Card, Integer> x : quantity.entrySet()) {
            if (x.getValue() == 2) {
                return getCardCombination(x.getKey(), cards);
            }
        }
        return null;
    }

    private Card getHighCard(List<Card> cards) {
        Card maxValue = cards.get(0);
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getValueCard().getValue() > maxValue.getValueCard().getValue()) {
                maxValue = cards.get(i);
            }
        }
        return maxValue;
    }

    private List<Card> addSomeCardAndSort(Card x1, Card x2, List<Card> cards) {
        if (x1 != null && x2 != null) {
            List<Card> result = new ArrayList<>(getCardCombination(x1, cards));
            result.addAll(getCardCombination(x2, cards));
            result.sort(Comparator.comparingInt(lam -> lam.getValueCard().getValue()));
            return result;
        }
        return null;
    }

    private List<Card> getCardCombination(Card x, List<Card> cards) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getValueCard().equals(x.getValueCard())) {
                result.add(card);
            }
        }
        return result;
    }

    private Map<Card, Integer> getQuantity(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = new HashMap<>();
        Map<Card, Integer> result = new HashMap<>();
        ValueCardEnum value;
        for (Card card : cards) {
            value = card.getValueCard();
            if (quantity.containsKey(value)) {
                quantity.put(value, quantity.get(value) + 1);
            } else {
                quantity.put(value, 1);
            }
        }
        for (Card card : cards) {
            if (quantity.containsKey(card.getValueCard()) && !result.containsKey(card)) {
                result.put(card, quantity.get(card.getValueCard()));
            }
        }
        return result;
    }

}
