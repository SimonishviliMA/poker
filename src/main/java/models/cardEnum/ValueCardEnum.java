package models.cardEnum;

import java.util.List;
import java.util.Random;

public enum ValueCardEnum {

    TWO(0, "Two"),
    THREE(1, "Three"),
    FOUR(2, "Four"),
    FIVE(3, "Five"),
    SIX(4, "Six"),
    SEVEN(5, "Seven"),
    EIGHT(6, "Eight"),
    NINE(7, "Nine"),
    TEN(8, "Ten"),
    JACK(9, "Jack"),
    QUEEN(10, "Queen"),
    KING(11, "King"),
    ACE(12, "Ace");

    private final int value;
    private final String name;
    private static final List<ValueCardEnum> VALUES =
            List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    ValueCardEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ValueCardEnum getRandom() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
