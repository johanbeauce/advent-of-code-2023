package be.beauce.adventofcode2023.day1.part2;

import be.beauce.adventofcode2023.day1.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntegerNamedLineComputerTest {
    IntegerNamedLineComputer integerNamedLineComputer;

    @BeforeEach
    void setUp() {
        integerNamedLineComputer = new IntegerNamedLineComputer();
    }

    @Test
    void found_first_and_last_digits() {
        var digits = integerNamedLineComputer.getFirstAndLastDigit("oneasldfkxcutwo");
        assertThat(digits.getNumber()).isEqualTo(12);
    }

    @Test
    void sum_of_all_inputs_is_77() {
        var sum = integerNamedLineComputer.getSum("""
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """);
        assertThat(sum).isEqualTo(142);
    }

    @Test
    void sum_of_part1() {
        var sum = integerNamedLineComputer.getSum(Input.TEXT);
        assertThat(sum).isEqualTo(55413);
    }
}
