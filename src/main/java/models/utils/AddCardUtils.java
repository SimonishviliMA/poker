package models.utils;

import models.Card;

import java.util.HashSet;
import java.util.Set;

public class AddCardUtils {

    private static AddCardUtils instance;
    private static final Set<Card> usedCards = new HashSet<>();

    private AddCardUtils() {
    }

    public static void getInstance(){
        if (instance == null) {

            instance = new AddCardUtils();
        }

    }

    public static Card getCards() {
        Card card = new Card();
        while(usedCards.contains(card)) {
            card = new Card();
        }
        usedCards.add(card);
        return card;
    }

    public static Set<Card> getUsedCards() {
        return usedCards;
    }
}
