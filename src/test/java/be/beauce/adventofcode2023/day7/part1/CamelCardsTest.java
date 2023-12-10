package be.beauce.adventofcode2023.day7.part1;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CamelCardsTest {

    CamelCards camelCards;

    @BeforeEach
    void setUp() {
        camelCards = new CamelCards("""
                32T3K 765
                T55J5 684
                KK677 28
                KTJJT 220
                QQQJA 483
                """);
    }

    @Test
    void hands() {
        assertThat(camelCards.getHands())
                .hasSize(5)
                .extracting("cards", "bid")
                .containsExactlyInAnyOrder(
                        Tuple.tuple(List.of("3", "2", "T", "3", "K"), 765L),
                        Tuple.tuple(List.of("T", "5", "5", "J", "5"), 684L),
                        Tuple.tuple(List.of("K", "K", "6", "7", "7"), 28L),
                        Tuple.tuple(List.of("K", "T", "J", "J", "T"), 220L),
                        Tuple.tuple(List.of("Q", "Q", "Q", "J", "A"), 483L)
                );
    }

    @Test
    void verify_sort() {
        var list = camelCards.getHands().stream()
                .sorted(Hand::compareTo)
                .toList();

        assertThat(list)
                .extracting("cards", "bid")
                .containsExactly(
                        Tuple.tuple(List.of("Q", "Q", "Q", "J", "A"), 483L),
                        Tuple.tuple(List.of("T", "5", "5", "J", "5"), 684L),
                        Tuple.tuple(List.of("K", "K", "6", "7", "7"), 28L),
                        Tuple.tuple(List.of("K", "T", "J", "J", "T"), 220L),
                        Tuple.tuple(List.of("3", "2", "T", "3", "K"), 765L)
                        );
    }

    @Test
    void hand_compare() {
        var compare = camelCards.getHands().get(1).compareTo(camelCards.getHands().get(4));
        assertThat(compare).isEqualTo(-1);
    }

    @Test
    void compare_cards() {
        assertThat(camelCards.getTotalWinnings()).isEqualTo(6440);
    }

    @Test
    void verify_five() {
        assertThat(CardType.getCardType(List.of("5", "5", "5", "5", "5"))).isEqualTo(CardType.FIVE);
    }

    @Test
    void verify_four() {
        assertThat(CardType.getCardType(List.of("5", "5", "5", "5", "4"))).isEqualTo(CardType.FOUR);
    }

    @Test
    void verify_high() {
        assertThat(CardType.getCardType(List.of("T", "T", "T", "9", "8"))).isEqualTo(CardType.THREE);
    }

    @Test
    void verify_three() {
        assertThat(CardType.getCardType(List.of("2", "3", "4", "2", "5", "6"))).isEqualTo(CardType.HIGH);
    }

    @Test
    void verify_two_pair() {
        assertThat(CardType.getCardType(List.of("2", "3", "4", "3", "2"))).isEqualTo(CardType.TWO);
    }

    @Test
    void verify_one() {
        assertThat(CardType.getCardType(List.of("A", "2", "2", "3", "A", "4"))).isEqualTo(CardType.ONE);
    }

    @Test
    void verify_full() {
        assertThat(CardType.getCardType(List.of("2", "3", "3", "3", "2"))).isEqualTo(CardType.FULL);
    }
}
