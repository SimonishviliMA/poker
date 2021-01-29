package models.cardEnum;

import java.util.List;
import java.util.Random;

public enum SuitCardEnum {

    DIAMONDS(0, "Diamond"),
    HEARS(1, "Heart"),
    SPADES(2, "Spade"),
    CLUBS(3, "Club");


    private final int value;
    private final String name;
    private static final List<SuitCardEnum> VALUES =
            List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    SuitCardEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static SuitCardEnum getRandom() {

        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}