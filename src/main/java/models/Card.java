package models;

import models.cardEnum.SuitCardEnum;
import models.cardEnum.ValueCardEnum;

import java.util.Objects;

public class Card {

    private final SuitCardEnum suitCard;
    private final ValueCardEnum valueCard;
    private boolean playerCard;

    public Card(boolean playerCard) {
        this.playerCard = playerCard;
        this.suitCard = SuitCardEnum.getRandom();
        this.valueCard = ValueCardEnum.getRandom();
    }

    public Card(SuitCardEnum suitCard, ValueCardEnum valueCard) {
        this.suitCard = suitCard;
        this.valueCard = valueCard;
    }

    public SuitCardEnum getSuitCard() {
        return suitCard;
    }

    public ValueCardEnum getValueCard() {
        return valueCard;
    }

    public boolean isPlayerCard() {
        return playerCard;
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
