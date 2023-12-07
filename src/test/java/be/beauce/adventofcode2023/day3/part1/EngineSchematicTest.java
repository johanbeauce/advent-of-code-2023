package be.beauce.adventofcode2023.day3.part1;

import be.beauce.adventofcode2023.day3.Input;
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
            assertThat(engineSchematic.getNumbersSum()).isEqualTo(1);
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
            assertThat(engineSchematic.getNumbersSum()).isEqualTo(3);
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
            assertThat(engineSchematic.getNumbersSum()).isEqualTo(23);
        }
    }


    @Nested
    class Given_an_engine_schematic_with_3_number_on_top_and_two_on_same_line {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                .1..10..
                20*5..
                """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            assertThat(engineSchematic.getNumbersSum()).isEqualTo(26);
        }
    }

    @Nested
    class Given_an_engine_schematic_with_3_number_on_top_and_two_on_same_line_and_2_on_next_line {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                1...10.
                20*.5..
                50..300
                """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            assertThat(engineSchematic.getNumbersSum()).isEqualTo(70);
        }
    }

    @Test
    void case_with_4_numbers() {
        engineSchematic = new EngineSchematic("""
                1.10..
                5*55..
                5000..
                """);
        var sum = engineSchematic.getNumbersSum();
        assertThat(sum).isEqualTo(5071);
    }

    @Nested
    class Given_a_large_input {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                    467..114..
                    ...*......
                    .%35..633.
                    ......#...
                    617*......
                    .....+.58.
                    ..592.....
                    ......755.
                    ...$.*....
                    .664.598..
                    """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            assertThat(engineSchematic.getNumbersSum()).isEqualTo(4361);
        }
    }

    @Nested
    class Given_another_large_input {

        @BeforeEach
        void setUp() {
            engineSchematic = new EngineSchematic("""
                    .....766.......................*......68.....#..477...$...428....209*.......*...265.......191......*........268...=....81...618#........=...
                    ........*847.....661.474......624.........................................223...#....*...........74...................*.....................
                    .................*......%.574.....................976.475*618.@.....=..27.........975.280.....*......=...............13.....757-.......112..
                    .....721.........748.........*......392&.........../..........636.911......................359.......857.656....72-.....................*...
                    """);
        }

        @Test
        void get_sum_of_adjacent_numbers() {
            assertThat(engineSchematic.getNumbersSum()).isEqualTo(9334);
        }
    }

}
