package be.beauce.adventofcode2023.day6.part1;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WaitForItTest {

    WaitForIt waitForIt;
    RaceInstance raceInstance;

    @BeforeEach
    void setUp() {
        waitForIt = new WaitForIt();
    }

    @Nested
    class Given_race_1 {
        @BeforeEach
        void setUp() {
            raceInstance = new RaceInstance(7, 9);
        }

        @Test
        void hold_time_1() {
            var boat = new Boat(1, raceInstance);
            assertThat(boat.getTotalTime()).isEqualTo(10);
            assertThat(boat.canWin()).isFalse();
        }

        @Test
        void hold_time_2() {
            var boat = new Boat(2, raceInstance);
            assertThat(boat.getTotalTime()).isEqualTo(7);
            assertThat(boat.canWin()).isTrue();
        }

        @Test
        void hold_time_3() {
            var boat = new Boat(3, raceInstance);
            assertThat(boat.getTimeToRunTheRaceDistance()).isEqualTo(3);
            assertThat(boat.getTotalTime()).isEqualTo(6);
            assertThat(boat.canWin()).isTrue();
        }

        @Test
        void hold_time_4() {
            var boat = new Boat(4, raceInstance);
            assertThat(boat.getTotalTime()).isEqualTo(7);
            assertThat(boat.canWin()).isTrue();
        }

        @Test
        void hold_time_5() {
            var boat = new Boat(5, raceInstance);
            assertThat(boat.getTotalTime()).isEqualTo(7);
            assertThat(boat.canWin()).isTrue();
        }

        @Test
        void hold_time_6() {
            var boat = new Boat(6, raceInstance);
            assertThat(boat.getTotalTime()).isEqualTo(8);
            assertThat(boat.canWin()).isFalse();
        }

        @Test
        void hold_time_7() {
            var boat = new Boat(7, raceInstance);
            assertThat(boat.getTotalTime()).isEqualTo(9);
            assertThat(boat.canWin()).isFalse();
        }

        @Test
        void get_number_of_possible_wins() {
            waitForIt.getNumberOfPossibleWins(raceInstance);
            assertThat(waitForIt.getNumberOfPossibleWins(raceInstance))
                    .isEqualTo(4);
        }
    }

    @Nested
    class Given_race_2 {
        @BeforeEach
        void setUp() {
            raceInstance = new RaceInstance(15, 40);
        }

        @ParameterizedTest
        @MethodSource("holdTimeProvider")
        void verify_matching_race(int holdTime, boolean canWin) {
            var boat = new Boat(holdTime, raceInstance);
            assertThat(boat.canWin()).isEqualTo(canWin);
        }

        private static Stream<Arguments> holdTimeProvider() {
            return Stream.of(
                    Arguments.of(1, false),
                    Arguments.of(2, false),
                    Arguments.of(3, false),
                    Arguments.of(4, true),
                    Arguments.of(5, true),
                    Arguments.of(6, true),
                    Arguments.of(7, true),
                    Arguments.of(8, true),
                    Arguments.of(9, true),
                    Arguments.of(10, true),
                    Arguments.of(11, true),
                    Arguments.of(12, false));
        }

        @Test
        void get_number_of_possible_wins() {
            waitForIt.getNumberOfPossibleWins(raceInstance);
            assertThat(waitForIt.getNumberOfPossibleWins(raceInstance))
                    .isEqualTo(8);
        }
    }


    @Nested
    class Given_race_3 {
        @BeforeEach
        void setUp() {
            raceInstance = new RaceInstance(30, 200);
        }

        @ParameterizedTest
        @MethodSource("holdTimeProvider")
        void verify_matching_race(int holdTime, boolean canWin) {
            var boat = new Boat(holdTime, raceInstance);
            assertThat(boat.canWin()).isEqualTo(canWin);
        }

        private static Stream<Arguments> holdTimeProvider() {
            return Stream.of(
                    Arguments.of(10, false),
                    Arguments.of(11, true),
                    Arguments.of(12, true),
                    Arguments.of(13, true),
                    Arguments.of(14, true),
                    Arguments.of(15, true),
                    Arguments.of(16, true),
                    Arguments.of(17, true),
                    Arguments.of(18, true),
                    Arguments.of(19, true),
                    Arguments.of(20, false));
        }

        @Test
        void get_number_of_possible_wins() {
            waitForIt.getNumberOfPossibleWins(raceInstance);
            assertThat(waitForIt.getNumberOfPossibleWins(raceInstance))
                    .isEqualTo(9);
        }
    }

    @Test
    void test_wait_for_it_input() {
        waitForIt = new WaitForIt("""
                Time:      7  15   30
                Distance:  9  40  200
                """);
        assertThat(waitForIt.getRaceInstances())
                .hasSize(3)
                .extracting("time", "distance")
                .containsExactly(
                        Tuple.tuple(7L, 9L),
                        Tuple.tuple(15L, 40L),
                        Tuple.tuple(30L, 200L)
                );
    }

    @Test
    void verify_mutiply_of_number_of_wins() {
        waitForIt = new WaitForIt("""
                Time:      7  15   30
                Distance:  9  40  200
                """);
        assertThat(waitForIt.multiplyNumberOfWinsForEachRaces())
                .isEqualTo(288);
    }

    @Test
    void verify_mutiply_of_number_of_wins_of_plus() {
        waitForIt = new WaitForIt("""
                Time:      71530
                Distance:  940200
                """);
        assertThat(waitForIt.multiplyNumberOfWinsForEachRaces())
                .isEqualTo(71503);
    }
}
