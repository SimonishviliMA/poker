package action;

import models.Card;
import models.Player;
import models.cardEnum.CombinationEnum;
import models.cardEnum.SuitCardEnum;
import models.cardEnum.ValueCardEnum;

import java.util.*;
import java.util.stream.Collectors;

public class Combination {

    public CombinationEnum getCombination(List<Card> cards) {

        CombinationEnum combination = getRoyalFlush(cards);
        if (combination != null) {
            return combination;
        }

        combination = getStraightFlush(cards);
        if (combination != null) {
            return combination;
        }

        combination = getFourOfAKind(cards);
        if (combination != null) {
            return combination;
        }

        combination = getFullHouse(cards);
        if (combination != null) {
            return combination;
        }

        combination = getFlush(cards);
        if (combination != null) {
            return combination;
        }

        combination = getStraight(cards);
        if (combination != null) {
            return combination;
        }

        combination = getThreeOfAKind(cards);
        if (combination != null) {
            return combination;
        }

        combination = getTwoPairs(cards);
        if (combination != null) {
            return combination;
        }

        combination = getOnePairs(cards);
        if (combination != null) {
            return combination;
        }

        Card highCard = getHighCard(cards);
        combination = CombinationEnum.HIGH_CARD;
        combination.setCardsFromCombination(new ArrayList<>(List.of(highCard)));
        combination.setKicker(highCard.getValueCard());
        return combination;

    }

    private CombinationEnum getRoyalFlush(List<Card> cards) {

        CombinationEnum betweenCombination = getStraightFlush(cards);
        if (betweenCombination != null) {
            List<Card> combinationCards = betweenCombination.getCardsFromCombination();
            if (combinationCards.get(combinationCards.size() - 1).getValueCard().equals(ValueCardEnum.ACE)) {
                CombinationEnum combination = CombinationEnum.ROYAL_FLUSH;
                combination.setCardsFromCombination(combinationCards);
                combination.setKicker(cards, combinationCards);
                return combination;
            }
        }
        return null;
    }

    private CombinationEnum getStraightFlush(List<Card> cards) {

        CombinationEnum betweenCombination = getFlush(cards);
        if (betweenCombination != null) {
            List<Card> combinationCards = betweenCombination.getCardsFromCombination();
            if (!combinationCards.isEmpty()) {
                betweenCombination = getStraight(combinationCards);
                if (betweenCombination != null) {
                    CombinationEnum combination = CombinationEnum.STRAIGHT_FLUSH;
                    combination.setCardsFromCombination(combinationCards);
                    combination.setKicker(cards, combinationCards);
                    return combination;
                }
            }
        }
        return null;
    }

    private CombinationEnum getFourOfAKind(List<Card> cards) {

        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        List<Card> combinationCards = getCombinationCards(cards, quantity, 4);
        if (!combinationCards.isEmpty()) {
            CombinationEnum combination = CombinationEnum.FOUR_OF_A_KIND;
            combination.setCardsFromCombination(combinationCards);
            combination.setKicker(cards, combinationCards);
            return combination;
        }
        return null;
    }

    private CombinationEnum getFullHouse(List<Card> cards) {

        CombinationEnum betweenCombination = getThreeOfAKind(cards);
        List<Card> combinationCards = new ArrayList<>();
        if (betweenCombination != null) {
            combinationCards = betweenCombination.getCardsFromCombination();
            betweenCombination = getOnePairs(cards);
            if (betweenCombination != null) {
                combinationCards.addAll(betweenCombination.getCardsFromCombination());
            }
        }

        if (combinationCards.size() == 5) {
            CombinationEnum combination = CombinationEnum.FULL_HOUSE;
            combination.setCardsFromCombination(combinationCards);
            combination.setKicker(cards, combinationCards);
            return combination;
        }
        return null;
    }

    private CombinationEnum getFlush(List<Card> cards) {

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
            cards.sort(Comparator.comparingInt(card -> card.getValueCard().getValue()));
            List<Card> cardsCombination = new ArrayList<>();
            for (int i = cards.size() - 1; i >= 0; i--) {
                if (cardsCombination.size() != 5 && cards.get(i).getSuitCard().equals(suitCardEnum)) {
                    cardsCombination.add(cards.get(i));
                }
            }
            CombinationEnum combination = CombinationEnum.FLUSH;
            combination.setCardsFromCombination(cardsCombination);
            combination.setKicker(cards, cardsCombination);
            return combination;
        }
        return null;
    }

    private CombinationEnum getStraight(List<Card> cards) {

        cards.sort(Comparator.comparingInt(x -> x.getValueCard().getValue()));
        int prevCard = cards.get(cards.size() - 1).getValueCard().getValue();
        Card card;
        List<Card> cardsCombination = new ArrayList<>(Collections.singletonList(cards.get(cards.size() - 1)));
        for (int i = cards.size() - 2; i >= 0; i--) {
            card = cards.get(i);
            if (prevCard - card.getValueCard().getValue() != 0) {
                if (prevCard - card.getValueCard().getValue() != 1) {
                    cardsCombination.clear();
                }
                cardsCombination.add(card);
                if (cardsCombination.size() == 5) {
                    break;
                }
                prevCard = card.getValueCard().getValue();
            }
        }

        if (cardsCombination.size() == 5) {
            CombinationEnum combination = CombinationEnum.STRAIGHT;
            combination.setCardsFromCombination(cardsCombination);
            combination.setKicker(cards, cardsCombination);
            return combination;
        }

        return null;
    }

    private CombinationEnum getThreeOfAKind(List<Card> cards) {

        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        List<Card> combinationCards = getCombinationCards(cards, quantity, 3);
        if (!combinationCards.isEmpty()) {
            CombinationEnum result = CombinationEnum.THREE_OF_A_KIND;
            result.setCardsFromCombination(combinationCards);
            result.setKicker(cards, combinationCards);
            return result;
        }
        return null;
    }

    private CombinationEnum getTwoPairs(List<Card> cards) {

        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        List<Card> combinationCards = getCombinationCards(cards, quantity, 2);
        if (combinationCards.size() == 4) {
            CombinationEnum result = CombinationEnum.TWO_PAIRS;
            result.setCardsFromCombination(combinationCards);
            result.setKicker(cards, combinationCards);
            return result;
        }
        return null;
    }

    private CombinationEnum getOnePairs(List<Card> cards) {

        Map<ValueCardEnum, Integer> quantity = getQuantity(cards);
        List<Card> combinationCards = getCombinationCards(cards, quantity, 2);
        if (!combinationCards.isEmpty()) {
            CombinationEnum onePair = CombinationEnum.ONE_PAIR;
            onePair.setCardsFromCombination(combinationCards);
            onePair.setKicker(cards, combinationCards);
            return onePair;
        }
        return null;
    }

    private Card getHighCard(List<Card> cards) {

        cards.sort(Comparator.comparingInt(card -> card.getValueCard().getValue()));
        return cards.get(cards.size() - 1);
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
                        cards.parallelStream()
                                .filter(card -> card.getValueCard().equals(cardMap.getKey()))
                                .collect(Collectors.toList())
                );
            }
        }
        return result;
    }
}
