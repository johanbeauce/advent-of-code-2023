package be.beauce.adventofcode2023.day1.part1;

import be.beauce.adventofcode2023.day1.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class IntegerLineComputerTest {

    IntegerLineComputer integerLineComputer;

    @BeforeEach
    void setUp() {
        integerLineComputer = new IntegerLineComputer();
    }

    @Test
    void should_give_12() {
        String input = "1abc2";
        int lineInteger = integerLineComputer.compute(input);
        assertThat(lineInteger).isEqualTo(12);
    }

    @Test
    void should_give_50() {
        String input = """
                1abc2
                pqr3stu8vwx
                """;
        int lineInteger = integerLineComputer.compute(input);
        assertThat(lineInteger).isEqualTo(50);
    }

    @Test
    void should_give_142() {
        String input = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """;
        int lineInteger = integerLineComputer.compute(input);
        assertThat(lineInteger).isEqualTo(142);
    }

    @Test
    void should_give_challenge_result() {
        int lineInteger = integerLineComputer.compute(Input.TEXT);
        assertThat(lineInteger).isEqualTo(55712);
    }
}
