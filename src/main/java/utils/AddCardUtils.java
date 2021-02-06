package utils;

import models.Card;

import java.util.HashSet;
import java.util.Set;

public class AddCardUtils {

    private static final Set<Card> usedCards = new HashSet<>();

    private AddCardUtils() {
    }

    public static Card getCards(boolean playerCard) {
        Card card = new Card(playerCard);
        while(usedCards.contains(card)) {
            card = new Card(playerCard);
        }
        usedCards.add(card);
        return card;
    }

    public static Set<Card> getUsedCards() {
        return usedCards;
    }
}
