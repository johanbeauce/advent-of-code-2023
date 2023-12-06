package be.beauce.adventofcode2023.day5.part2;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeedFertilizerPlusTest {
    private SeedFertilizerPlus seedFertilizerPlus;

    @Nested
    class Given_a_specific_seed_range {
        @BeforeEach
        void setUp() {
            seedFertilizerPlus = new SeedFertilizerPlus(new String[]{"80","10", "150","20"});
        }

        @Test
        void get_ranges() {
            assertThat(seedFertilizerPlus.initialSeedRanges())
                    .hasSize(2)
                    .extracting("start", "end")
                    .containsExactly(
                            Tuple.tuple(80L, 90L),
                            Tuple.tuple(150L, 170L));
        }

        @Nested
        class Given_an_initial_seed_ranges {

            private SeedRanges seedRanges;

            @BeforeEach
            void setUp() {
                seedRanges = new SeedRanges(seedFertilizerPlus.initialSeedRanges());
            }

            @Test
            void get_seed_range_outside_before() {
                assertThat(seedRanges.convertTo(seedFertilizerPlus.getSeedRanges("""
                40 50 5
                """)))
                        .hasSize(2)
                        .extracting("start", "end")
                        .containsExactlyInAnyOrder(
                                Tuple.tuple(80L, 90L),
                                Tuple.tuple(150L, 170L));
            }

            @Test
            void get_seed_range_outside_after() {
                assertThat(seedRanges.convertTo(seedFertilizerPlus.getSeedRanges("""
                40 200 5
                """)))
                        .hasSize(2)
                        .extracting("start", "end")
                        .containsExactlyInAnyOrder(
                                Tuple.tuple(80L, 90L),
                                Tuple.tuple(150L, 170L));
            }

            @Test
            void get_seed_range_starts_inside_and_ends_inside() {
                assertThat(seedRanges.convertTo(seedFertilizerPlus.getSeedRanges("""
                40 60 25
                """)))
                        .hasSize(3)
                        .extracting("start", "end")
                        .containsExactlyInAnyOrder(
                                Tuple.tuple(60L, 65L),
                                Tuple.tuple(86L, 90L),
                                Tuple.tuple(150L, 170L));
            }

            @Test
            void get_seed_range_starts_inside_and_ends_outside() {
                assertThat(seedRanges.convertTo(seedFertilizerPlus.getSeedRanges("""
                40 60 35
                """)))
                        .hasSize(2)
                        .extracting("start", "end")
                        .containsExactlyInAnyOrder(
                                Tuple.tuple(60L, 70L),
                                Tuple.tuple(150L, 170L));
            }

            @Test
            void get_seed_range_starts_before_and_ends_inside() {
                assertThat(seedRanges.convertTo(seedFertilizerPlus.getSeedRanges("""
                40 85 10
                """)))
                        .hasSize(3)
                        .extracting("start", "end")
                        .containsExactlyInAnyOrder(
                                Tuple.tuple(80L, 84L),
                                Tuple.tuple(40L, 45L),
                                Tuple.tuple(150L, 170L));
            }

            @Test
            void get_seed_range_starts_before_and_ends_outside() {
                assertThat(seedRanges.convertTo(seedFertilizerPlus.getSeedRanges("""
                40 85 2
                """)))
                        .hasSize(4)
                        .extracting("start", "end")
                        .containsExactlyInAnyOrder(
                                Tuple.tuple(80L, 84L),
                                Tuple.tuple(40L, 42L),
                                Tuple.tuple(88L, 90L),
                                Tuple.tuple(150L, 170L));
            }
        }
    }

    @Nested
    class Given_simple_example_seed_range {
        @BeforeEach
        void setUp() {
            seedFertilizerPlus = new SeedFertilizerPlus(new String[]{"79", "14", "55", "13"});
        }

        @Test
        void apply_several_converters() {
            var seedRanges = new SeedRanges(seedFertilizerPlus.initialSeedRanges());
            var seedRangeSoil = new SeedRanges(seedRanges.convertTo(seedFertilizerPlus.getSeedRanges("""
                    50 98 2
                    52 50 48
                    """)));
            var seedRangeFertilizer = new SeedRanges(seedRangeSoil.convertTo(seedFertilizerPlus.getSeedRanges("""
                        0 15 37
                        37 52 2
                        39 0 15
                        """)));
            var seedRangeWater = new SeedRanges(seedRangeFertilizer.convertTo(seedFertilizerPlus.getSeedRanges("""
                        49 53 8
                        0 11 42
                        42 0 7
                        57 7 4
                        """)));
            var seedRangeLight = new SeedRanges(seedRangeWater.convertTo(seedFertilizerPlus.getSeedRanges("""
                        88 18 7
                        18 25 70
                        """)));
            var seedRangeTemperature = new SeedRanges(seedRangeLight.convertTo(seedFertilizerPlus.getSeedRanges("""
                        45 77 23
                        81 45 19
                        68 64 13
                        """)));
            var seedRangeHumidity = new SeedRanges(seedRangeTemperature.convertTo(seedFertilizerPlus.getSeedRanges("""
                        0 69 1
                        1 0 69
                        """)));
            var seedRangeLocation = new SeedRanges(seedRangeHumidity.convertTo(seedFertilizerPlus.getSeedRanges("""
                        60 56 37
                        56 93 4
                        """)));
            System.out.println("end");
            var minimumValue = seedRangeLocation.asSet().stream()
                    .mapToLong(SeedRange::start)
                    .min()
                    .getAsLong();
            assertThat(minimumValue).isEqualTo(46);
        }
    }
}
