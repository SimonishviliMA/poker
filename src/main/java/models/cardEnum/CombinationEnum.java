package models.cardEnum;

public enum CombinationEnum {

    HIGH_CARD(0, "High card"),
    ONE_PAIR(1, "One pair"),
    TWO_PAIRS(2, "Two pairs"),
    THREE_OF_A_KIND(3, "Three of a kind"),
    STRAIGHT(4, "Straight"),
    FLUSH(5, "Flush"),
    FULL_HOUSE(6, "Full house"),
    FOUR_OF_A_KIND(7, "Four of a kind"),
    STRAIGHT_FLUSH(8, "Straight flush"),
    ROYAL_FLUSH(9, "Royal flush");

    private final int value;
    private final String name;

    CombinationEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
