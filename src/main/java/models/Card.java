package models;

import models.cardEnum.SuitCardEnum;
import models.cardEnum.ValueCardEnum;

import java.util.Objects;

public class Card {

    private final SuitCardEnum suitCard;
    private final ValueCardEnum valueCard;

    public Card() {
        this.suitCard = SuitCardEnum.getRandom();
        this.valueCard = ValueCardEnum.getRandom();
    }

    public SuitCardEnum getSuitCard() {
        return suitCard;
    }

    public ValueCardEnum getValueCard() {
        return valueCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suitCard == card.suitCard && valueCard == card.valueCard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suitCard, valueCard);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suitCard=" + suitCard.getName() +
                ", valueCard=" + valueCard.getName() +
                '}';
    }
}
