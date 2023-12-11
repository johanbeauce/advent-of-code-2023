package be.beauce.adventofcode2023.day7;

import java.util.List;

import static be.beauce.adventofcode2023.day7.CardType.FIVE;
import static be.beauce.adventofcode2023.day7.CardType.FOUR;
import static be.beauce.adventofcode2023.day7.CardType.FULL;
import static be.beauce.adventofcode2023.day7.CardType.ONE;
import static be.beauce.adventofcode2023.day7.CardType.THREE;
import static be.beauce.adventofcode2023.day7.CardType.TWO;

public class JokerHand {
    public static final List<String> CARDS_POWER = List.of("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J");

    private final List<String> cards;
    private final long bid;
//    private boolean usedJoker;

    public JokerHand(List<String> cards, long bid) {
        this.cards = cards;
        this.bid = bid;
    }

    public List<String> getCards() {
        return cards;
    }

    public long getBid() {
        return bid;
    }

    public CardType getCardType() {
        return cards.contains("J") ?
                getCardTypeWithJoker(cards) :
                CardType.getCardType(cards);
    }

    public static CardType getCardTypeWithJoker(List<String> cards) {
        var cardType = CardType.getCardType(cards);
        return switch (cardType) {
            case FIVE, FOUR, FULL -> FIVE;
            case THREE -> tryingTransformThreeToFour(cards);
            case TWO -> tryingTransformTwoToFull(cards);
            case ONE -> THREE;
            case HIGH -> ONE;
        };
    }

    private static CardType tryingTransformThreeToFour(List<String> cards) {
        if (cards.stream().filter("J"::equals).count() == 3 || cards.stream().filter("J"::equals).count() == 1) {
            return FOUR;
        }
        return THREE;
    }

    private static CardType tryingTransformTwoToFull(List<String> cards) {
        if (cards.stream().filter("J"::equals).count() == 2) {
            return FOUR;
        }
        if (cards.stream().filter("J"::equals).count() == 1) {
            return FULL;
        }
        return TWO;
    }

    public int compareTo(JokerHand hand) {
        var compare = Integer.compare(getCardType().getLevel(), hand.getCardType().getLevel());
        if (compare != 0) {
            return compare;
        }
        for (int i = 0; i < cards.size(); i++) {
            compare = Integer.compare(CARDS_POWER.indexOf(cards.get(i)), CARDS_POWER.indexOf(hand.getCards().get(i)));
            if (compare != 0) {
                return compare;
            }
        }
        throw new IllegalArgumentException("Should not be here");
    }
}
