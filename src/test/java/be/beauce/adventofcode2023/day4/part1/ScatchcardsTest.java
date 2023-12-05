package be.beauce.adventofcode2023.day4.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScatchcardsTest {
    Scratchcards scratchcards;

    @Nested
    class Given_one_line {
        @BeforeEach
        void setUp() {
            scratchcards = new Scratchcards("""
                    Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                    """);
        }

        @Test
        void card_number_is_1() {
            assertThat(scratchcards.getCards().get(0).index()).isZero();
            assertThat(scratchcards.getCards().get(0).number()).isEqualTo(1);
        }

        @Test
        void card_winning_numbers() {
            assertThat(scratchcards.getCards().get(0).winningNumbers())
                    .containsExactly(41, 48, 83, 86, 17);
        }

        @Test
        void card_numbers() {
            assertThat(scratchcards.getCards().get(0).numbers())
                    .containsExactly(83, 86, 6, 31, 17, 9, 48, 53);
        }
    }

    @Nested
    class Given_one_line_with_one_winning_and_two_numbers_and_one_match {
        @BeforeEach
        void setUp() {
            scratchcards = new Scratchcards("""
                    Card 1: 1 | 1 2
                    """);
        }

        @Test
        void total_is_one() {
            assertThat(scratchcards.getCards().get(0).total()).isEqualTo(1);
        }
    }

    @Nested
    class Given_one_line_with_two_winning_and_two_numbers_and_two_match {
        @BeforeEach
        void setUp() {
            scratchcards = new Scratchcards("""
                    Card 1: 1 2 | 1 2
                    """);
        }

        @Test
        void total_is_two() {
            assertThat(scratchcards.getCards().get(0).total()).isEqualTo(2);
        }
    }

    @Nested
    class Given_one_line_with_winnings_and_numbers_and_three_match {
        @BeforeEach
        void setUp() {
            scratchcards = new Scratchcards("""
                    Card 1: 1 2 3 | 1 2 12 3 42
                    """);
        }

        @Test
        void total_is_4() {
            assertThat(scratchcards.getCards().get(0).total()).isEqualTo(4);
        }
    }

    @Nested
    class Given_one_line_with_winnings_and_numbers_and_four_match {
        @BeforeEach
        void setUp() {
            scratchcards = new Scratchcards("""
                    Card 1: 1 2 3 54 | 1 2 12 3 42 54 
                    """);
        }

        @Test
        void total_is_8() {
            assertThat(scratchcards.getCards().get(0).total()).isEqualTo(8);
        }
    }

    @Nested
    class Given_several_lines {
        @BeforeEach
        void setUp() {
            scratchcards = new Scratchcards("""
                    Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                    Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                    Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                    Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                    Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                    """);
        }

        @Test
        void total_is_13() {
            assertThat(scratchcards.getTotal()).isEqualTo(13);
        }
    }
}
