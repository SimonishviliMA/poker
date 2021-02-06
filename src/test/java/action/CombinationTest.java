package action;

import models.Card;
import models.cardEnum.CombinationEnum;
import models.cardEnum.SuitCardEnum;
import models.cardEnum.ValueCardEnum;
import org.junit.Test;

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
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.THREE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.QUEEN);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.HIGH_CARD, result);
        //assertEquals(ValueCardEnum.ACE, result.getMaxValueCardCombination());
    }

    @Test
    public void getCombinationOnePair() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FOUR);
        Card card4 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.JACK);
        Card card5 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SIX);
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.THREE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.QUEEN);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.ONE_PAIR, result);
        //assertEquals(ValueCardEnum.TWO, result.getMaxValueCardCombination());
    }

    @Test
    public void getCombinationTwoPairs() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FOUR);
        Card card4 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.FOUR);
        Card card5 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SIX);
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.THREE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.QUEEN);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        List<Card> cardsFromCombination = result.getCardsFromCombination();

        assertEquals(CombinationEnum.TWO_PAIRS, result);
        //assertEquals(ValueCardEnum.FOUR, result.getMaxValueCardCombination());
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
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.THREE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.QUEEN);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.THREE_OF_A_KIND, result);
        //assertEquals(ValueCardEnum.TWO, result.getMaxValueCardCombination());
    }

    @Test
    public void getCombinationStraight() {

        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.FOUR);
        Card card4 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.FIVE);
        Card card5 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SIX);
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.THREE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.QUEEN);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        List<Card> cardsFromCombination = result.getCardsFromCombination();
        assertEquals(CombinationEnum.STRAIGHT, result);
        //assertEquals(ValueCardEnum.SIX, result.getMaxValueCardCombination());
        System.out.println(cardsFromCombination.toString());
        assertTrue(cardsFromCombination.contains(card1));
        assertTrue(cardsFromCombination.contains(card6));
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
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.THREE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.QUEEN);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.FLUSH, result);
        //assertEquals(ValueCardEnum.SIX, result.getMaxValueCardCombination());
    }

    @Test
    public void getCombinationFullHouse() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card4 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        Card card5 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.FIVE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.QUEEN);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.FULL_HOUSE, result);
        //assertEquals(ValueCardEnum.THREE, result.getMaxValueCardCombination());
    }

    @Test
    public void getCombinationFourOfAKind() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card4 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TWO);
        Card card5 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.THREE);
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.THREE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.QUEEN);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.FOUR_OF_A_KIND, result);
        //assertEquals(ValueCardEnum.TWO, result.getMaxValueCardCombination());
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
        List<Card> cards1 = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards1);
        assertEquals(CombinationEnum.STRAIGHT_FLUSH, result);
        //assertEquals(ValueCardEnum.EIGHT, result.getMaxValueCardCombination());

        Card card8 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.SEVEN);
        Card card9 = new Card(SuitCardEnum.CLUBS, ValueCardEnum.EIGHT);
        List<Card> cards2 = Arrays.asList(card3, card4, card5, card8, card9);
        CombinationEnum wrongResult = combination.getCombination(cards2);
        assertNotEquals(CombinationEnum.STRAIGHT_FLUSH, wrongResult);
    }

    @Test
    public void getCombinationRoyalFlush() {
        Card card1 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.TEN);
        Card card2 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.JACK);
        Card card3 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.QUEEN);
        Card card4 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.KING);
        Card card5 = new Card(SuitCardEnum.DIAMONDS, ValueCardEnum.ACE);
        Card card6 = new Card(SuitCardEnum.SPADES, ValueCardEnum.FIVE);
        Card card7 = new Card(SuitCardEnum.SPADES, ValueCardEnum.SIX);
        List<Card> cards = Arrays.asList(card1, card4, card3, card5, card2, card6, card7);
        Combination combination = new Combination();
        CombinationEnum result = combination.getCombination(cards);
        assertEquals(CombinationEnum.ROYAL_FLUSH, result);
        //assertEquals(ValueCardEnum.ACE, result.getMaxValueCardCombination());
    }
}