package be.beauce.adventofcode2023.day7.part1;

import java.util.List;
import java.util.function.Predicate;

public enum CardType {
    FIVE(1, CardType::havFiveCardInCommon),
    FOUR(2, CardType::hasFourCardsInCommon),
    FULL(3, CardType::hasFull),
    THREE(4, CardType::hasThree),
    TWO(5, CardType::hasTwo),
    ONE(6, CardType::hasPair),
    HIGH(6, CardType::isAllDifferent);

    private static boolean havFiveCardInCommon(List<String> cards) {
        return cards.stream().distinct().count() == 1;
    }

    private static boolean hasFourCardsInCommon(List<String> cards) {
        return cards.stream().distinct().count() == 2
            && cards.stream().anyMatch(card -> cards.stream().filter(card::equals).count() == 4);
    }

    private static boolean hasFull(List<String> cards) {
        return cards.stream().distinct().count() == 2
                && cards.stream().anyMatch(card -> cards.stream().filter(card::equals).count() == 3)
                && cards.stream().anyMatch(card -> cards.stream().filter(card::equals).count() == 2);
    }

    private static boolean hasThree(List<String> cards) {
        return cards.stream().distinct().count() == 3
                && cards.stream().anyMatch(card -> cards.stream().filter(card::equals).count() == 3);
    }

    private static boolean hasTwo(List<String> cards) {
        return cards.stream().distinct().count() == 3
                && cards.stream().anyMatch(card -> cards.stream().filter(card::equals).count() == 2);
    }

    private static boolean hasPair(List<String> cards) {
        return cards.stream().distinct().count() == 4
                && cards.stream().anyMatch(card -> cards.stream().filter(card::equals).count() == 2);
    }

    private static boolean isAllDifferent(List<String> cards) {
        return cards.stream().distinct().count() == 5;
    }

    private final int level;
    private final Predicate<List<String>> predicate;

    CardType(int level, Predicate<List<String>> predicate) {
        this.level = level;
        this.predicate = predicate;
    }

    public static CardType getCardType(List<String> cards) {
        for (CardType cardType : CardType.values()) {
            if (cardType.predicate.test(cards)) {
                return cardType;
            }
        }
        throw new IllegalArgumentException("No card type found for " + cards);
    }
}
