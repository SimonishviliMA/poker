package action;

import models.Card;
import models.cardEnum.CombinationEnum;
import models.cardEnum.SuitCardEnum;
import models.cardEnum.ValueCardEnum;

import java.util.*;
import java.util.stream.Collectors;

public class Combination {

    public CombinationEnum getCombination(List<Card> cards) {

        List<Card> cardsCombination = getRoyalFlush(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum royalFlush = CombinationEnum.ROYAL_FLUSH;
            royalFlush.setCardsFromCombination(cardsCombination);
            return royalFlush;
        }

        cardsCombination = getStraightFlush(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum straightFlush = CombinationEnum.STRAIGHT_FLUSH;
            straightFlush.setCardsFromCombination(cardsCombination);
            return straightFlush;
        }

        cardsCombination = getFourOfAKind(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum fourOfAKind = CombinationEnum.FOUR_OF_A_KIND;
            fourOfAKind.setCardsFromCombination(cardsCombination);
            return fourOfAKind;
        }

        cardsCombination = getFullHouse(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum fullHouse = CombinationEnum.FULL_HOUSE;
            fullHouse.setCardsFromCombination(cardsCombination);
            return fullHouse;
        }

        cardsCombination = getFlush(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum flush = CombinationEnum.FLUSH;
            flush.setCardsFromCombination(cardsCombination);
            return flush;
        }

        cardsCombination = getStraight(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum straight = CombinationEnum.STRAIGHT;
            straight.setCardsFromCombination(cardsCombination);
            return straight;
        }

        cardsCombination = getThreeOfAKind(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum threeOfAKind = CombinationEnum.THREE_OF_A_KIND;
            threeOfAKind.setCardsFromCombination(cardsCombination);
            return threeOfAKind;
        }

        cardsCombination = getTwoPairs(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum twoPairs = CombinationEnum.TWO_PAIRS;
            twoPairs.setCardsFromCombination(cardsCombination);
            return twoPairs;
        }

        cardsCombination = getOnePairs(cards);
        if (!cardsCombination.isEmpty()) {
            CombinationEnum onePair = CombinationEnum.ONE_PAIR;
            onePair.setCardsFromCombination(cardsCombination);
            return onePair;
        }

        CombinationEnum highCard = CombinationEnum.HIGH_CARD;
        highCard.setMaxValueCard(getHighCard(cards).getValueCard());
        return highCard;

    }

    private List<Card> getRoyalFlush(List<Card> cards) {
        List<Card> straightFlush = getStraightFlush(cards);
        return !straightFlush.isEmpty() &&
                straightFlush.get(straightFlush.size() - 1).getValueCard().equals(ValueCardEnum.ACE) ?
                straightFlush : new ArrayList<>();
    }

    private List<Card> getStraightFlush(List<Card> cards) {
        List<Card> flush = getFlush(cards);

        return !flush.isEmpty() ? getStraight(flush) : new ArrayList<>();
    }

    private List<Card> getFourOfAKind(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        return getCombinationCards(cards, quantity, 4);
    }

    private List<Card> getFullHouse(List<Card> cards) {
        List<Card> result = getThreeOfAKind(cards);
        result.addAll(getOnePairs(cards));
        return result.size() == 5 ? result : new ArrayList<>();
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

            if (x.getValue() >= 5) {
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
        return new ArrayList<>();
    }

    private List<Card> getStraight(List<Card> cards) {
        cards.sort(Comparator.comparingInt(x -> x.getValueCard().getValue()));
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
            if (count >= 5) {
                return result;
            }
            prevCard = card.getValueCard().getValue();
        }
        return new ArrayList<>();
    }

    private List<Card> getThreeOfAKind(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        return getCombinationCards(cards, quantity, 3);
    }

    private List<Card> getTwoPairs(List<Card> cards) {
        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        List<Card> result = getCombinationCards(cards, quantity, 2);
        return result.size() == 4 ? result : new ArrayList<>();
    }

    private List<Card> getOnePairs(List<Card> cards) {

        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        return getCombinationCards(cards, quantity, 2);
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

    private List<Card> getCombinationCards(List<Card> cards, Map<ValueCardEnum, Integer> quantity, Integer needQuantity) {
        List<Card> result = new ArrayList<>();
        for (Map.Entry<ValueCardEnum, Integer> cardMap : quantity.entrySet()) {
            if (cardMap.getValue().equals(needQuantity)) {
                result.addAll(
                        cards.stream()
                                .filter(card -> card.getValueCard().equals(cardMap.getKey()))
                                .collect(Collectors.toList())
                );
            }
        }
        return result;
    }

}
