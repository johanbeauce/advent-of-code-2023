package be.beauce.adventofcode2023.day7.part1;

import java.util.List;

public final class Hand {
    private static final List<String> CARDS_POWER = List.of("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2");

    private final List<String> cards;
    private final long bid;

    public Hand(List<String> cards,
                long bid) {
        this.cards = cards;
        this.bid = bid;
    }

    public List<String> cards() {
        return cards;
    }

    public long bid() {
        return bid;
    }

    public int compareTo(Hand hand) {
        var compare = CardType.getCardType(cards).compareTo(CardType.getCardType(hand.cards()));
        if (compare != 0) {
            return compare;
        }
        for (int i = 0; i < cards.size(); i++) {
            compare = Integer.compare(CARDS_POWER.indexOf(cards.get(i)), CARDS_POWER.indexOf(hand.cards().get(i)));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
}
