package action;

import models.Card;
import models.cardEnum.CombinationEnum;
import models.cardEnum.SuitCardEnum;
import models.cardEnum.ValueCardEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CombinationTest {

    @Test
    public void getCombinationHighCard() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.ACE);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FOUR);
        Card card4 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.JACK);
        Card card5 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SIX);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.HIGH_CARD, result);
        assertEquals(ValueCardEnum.ACE, result.getMaxValueCard());
    }

    @Test
    public void getCombinationOnePair() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FOUR);
        Card card4 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.JACK);
        Card card5 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SIX);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.ONE_PAIR, result);
        assertEquals(ValueCardEnum.TWO, result.getMaxValueCard());
    }

    @Test
    public void getCombinationTwoPairs() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FOUR);
        Card card4 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.FOUR);
        Card card5 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SIX);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        List<Card> cardsFromCombination = result.getCardsFromCombination();

        assertEquals(CombinationEnum.TWO_PAIRS, result);
        assertEquals(ValueCardEnum.FOUR, result.getMaxValueCard());
        assertTrue(cardsFromCombination.contains(card1));
        assertTrue(cardsFromCombination.contains(card2));
        assertTrue(cardsFromCombination.contains(card3));
        assertTrue(cardsFromCombination.contains(card4));
    }

    @Test
    public void getCombinationThreeOfAKind() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card4 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.FOUR);
        Card card5 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SIX);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.THREE_OF_A_KIND, result);
        assertEquals(ValueCardEnum.TWO, result.getMaxValueCard());
    }

    @Test
    public void getCombinationStraight() {

        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FOUR);
        Card card4 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.FIVE);
        Card card5 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SIX);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        List<Card> cardsFromCombination = result.getCardsFromCombination();
        assertEquals(CombinationEnum.STRAIGHT, result);
        assertEquals(ValueCardEnum.SIX, result.getMaxValueCard());
        System.out.println(cardsFromCombination.toString());
        assertTrue(cardsFromCombination.contains(card1));
        assertTrue(cardsFromCombination.contains(card2));
        assertTrue(cardsFromCombination.contains(card3));
        assertTrue(cardsFromCombination.contains(card4));
        assertTrue(cardsFromCombination.contains(card5));
    }

    @Test
    public void getCombinationFlush() {

        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FIVE);
        Card card4 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FIVE);
        Card card5 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.SIX);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.FLUSH, result);
        assertEquals(ValueCardEnum.SIX, result.getMaxValueCard());
    }

    @Test
    public void getCombinationFullHouse() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card4 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        Card card5 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.FULL_HOUSE, result);
        assertEquals(ValueCardEnum.THREE, result.getMaxValueCard());
    }

    @Test
    public void getCombinationFourOfAKind() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card4 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card5 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.FOUR_OF_A_KIND, result);
        assertEquals(ValueCardEnum.TWO, result.getMaxValueCard());
    }

    @Test
    public void getCombinationStraightFlush() {
        Card card1 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.ACE);
        Card card2 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.JACK);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FOUR);
        Card card4 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FIVE);
        Card card5 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.SIX);
        Card card6 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.SEVEN);
        Card card7 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.EIGHT);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.STRAIGHT_FLUSH, result);
        assertEquals(ValueCardEnum.EIGHT, result.getMaxValueCard());
    }
}