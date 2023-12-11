package be.beauce.adventofcode2023.day7.part2;

import be.beauce.adventofcode2023.day7.CardType;
import be.beauce.adventofcode2023.day7.JokerHand;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCardsPlusTest {

    CamelCardsPlus camelCardsPlus;

    @BeforeEach
    void setUp() {
        camelCardsPlus = new CamelCardsPlus("""
                32T3K 765
                T55J5 684
                KK677 28
                KTJJT 220
                QQQJA 483
                """);
    }

    /*
    KTJJT
    QQQJA
    T55J5
    KK677
    32T3K
    * */

    @Test
    void get_sorted_hands() {
        assertThat(camelCardsPlus.getSortedHands())
                .hasSize(5)
                .extracting("cards", "bid")
                .containsExactly(
                        Tuple.tuple(List.of("K", "T", "J", "J", "T"), 220L),
                        Tuple.tuple(List.of("Q", "Q", "Q", "J", "A"), 483L),
                        Tuple.tuple(List.of("T", "5", "5", "J", "5"), 684L),
                        Tuple.tuple(List.of("K", "K", "6", "7", "7"), 28L),
                        Tuple.tuple(List.of("3", "2", "T", "3", "K"), 765L)
                );
    }

    @Nested
    class Given_previous_test_always_works {
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


    @Test
    void hands_compare() {
        var compare = camelCardsPlus.getHands().get(3).compareTo(camelCardsPlus.getHands().get(4));
        assertThat(compare).isEqualTo(-1);
    }

    @Test
    void get_total_winnings() {
        assertThat(camelCardsPlus.getTotalWinnings()).isEqualTo(5905);
    }

    @Test
    void card_with_5_jokers() {
        JokerHand jokerHand = new JokerHand(List.of("J", "J", "J", "J", "J"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FIVE);
    }

    @Test
    void hand_with_four_with_joker_can_be_transformed_to_five() {
        JokerHand jokerHand = new JokerHand(List.of("A", "A", "A", "A", "J"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FIVE);
    }

    @Test
    void hand_with_four_J_can_not_be_transformed_to_five() {
        JokerHand jokerHand = new JokerHand(List.of("J", "J", "J", "A", "J"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FIVE);
    }

    @Test
    void hand_with_full_and_2_jokers_can_be_transformed_to_five() {
        JokerHand jokerHand = new JokerHand(List.of("A", "A", "J", "A", "J"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FIVE);
    }

    @Test
    void hand_with_full_and_1_joker_can_be_transformed_to_FOUR() {
        JokerHand jokerHand = new JokerHand(List.of("A", "A", "K", "A", "J"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FOUR);
    }

    @Test
    void hand_with_full_and_3_jokers_can_be_transformed() {
        JokerHand jokerHand = new JokerHand(List.of("A", "J", "J", "A", "J"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FIVE);
    }

    @Test
    void hand_with_full_and_2_jokers_can_not_be_transformed() {
        JokerHand jokerHand = new JokerHand(List.of("A", "A", "J", "A", "J"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FIVE);
    }

    @Test
    void hand_with_three_and_1_joker_can_be_transformed_to_four() {
        JokerHand jokerHand = new JokerHand(List.of("A", "A", "J", "A", "K"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FOUR);
    }

    @Test
    void hand_with_three_jokerS_can_not_be_transformed_to_four() {
        JokerHand jokerHand = new JokerHand(List.of("J", "J", "J", "A", "K"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FOUR);
    }

    @Test
    void hand_with_two_and_1_joker_can_be_transformed_to_full() {
        JokerHand jokerHand = new JokerHand(List.of("A", "A", "J", "K", "K"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FULL);
    }

    @Test
    void hand_with_two_and_2_joker_can_not_be_transformed_to_full() {
        JokerHand jokerHand = new JokerHand(List.of("A", "J", "J", "K", "K"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.FOUR);
    }

    @Test
    void hand_with_one_and_1_joker_can_be_transformed_to_three() {
        JokerHand jokerHand = new JokerHand(List.of("A", "3", "J", "K", "K"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.THREE);
    }

    @Test
    void hand_with_one_and_2_jokers_can_not_be_transformed() {
        JokerHand jokerHand = new JokerHand(List.of("A", "3", "J", "J", "K"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.THREE);
    }

    @Test
    void hand_with_high_and_1_joker_can_be_transform_to_two() {
        JokerHand jokerHand = new JokerHand(List.of("A", "3", "J", "K", "T"), 100);
        assertThat(jokerHand.getCardType()).isEqualTo(CardType.ONE);
    }
}
