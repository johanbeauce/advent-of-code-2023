package be.beauce.adventofcode2023.day3.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EngineSchematicTest {

    private EngineSchematic engineSchematic;

    @Nested
    class Given_an_engine_schematic_with_1_number_on_top {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                1..
                %..
                """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            int sum = engineSchematic.getNumbersSum();
            assertThat(sum).isEqualTo(1);
        }
    }

    @Nested
    class Given_an_engine_schematic_with_2_number_on_top {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                1.2.
                .%..
                """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            int sum = engineSchematic.getNumbersSum();
            assertThat(sum).isEqualTo(3);
        }
    }

    @Nested
    class Given_an_engine_schematic_with_3_number_on_top_and_same_line {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                1.2...
                20%..
                """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            int sum = engineSchematic.getNumbersSum();
            assertThat(sum).isEqualTo(23);
        }
    }


    @Nested
    class Given_an_engine_schematic_with_3_number_on_top_and_two_on_same_line {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                .1.10..
                20*5..
                """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            int sum = engineSchematic.getNumbersSum();
            assertThat(sum).isEqualTo(36);
        }
    }

    @Nested
    class Given_an_engine_schematic_with_3_number_on_top_and_two_on_same_line_and_2_on_next_line {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                1..10..
                20*.5..
                50..300
                """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            int sum = engineSchematic.getNumbersSum();
            assertThat(sum).isEqualTo(80);
        }
    }

    @Nested
    class Given_a_large_input {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic(Input.text);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            int sum = engineSchematic.getNumbersSum();
            assertThat(sum).isEqualTo(525946);
        }
    }

}
