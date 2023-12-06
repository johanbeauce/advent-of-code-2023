package be.beauce.adventofcode2023.day5.part1;

import be.beauce.adventofcode2023.day5.EnumConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.beauce.adventofcode2023.day5.part1.SeedFertilizer.aSeedFertilizer;
import static org.assertj.core.api.Assertions.assertThat;

class SeedFertilizerTest {
    private SeedFertilizer seedFertilizer;

    @BeforeEach
    void setUp() {
        seedFertilizer = aSeedFertilizer()
                .withSeeds(List.of("79", "14", "55", "13"))
                .build();
        seedFertilizer
                .addConverter(EnumConverter.SOIL, """
                        50 98 2
                        52 50 48
                        """)
                .addConverter(EnumConverter.FERTILIZER,"""
                        0 15 37
                        37 52 2
                        39 0 15
                        """)
                .addConverter(EnumConverter.WATER, """
                        49 53 8
                        0 11 42
                        42 0 7
                        57 7 4
                        """)
                .addConverter(EnumConverter.LIGHT, """
                        88 18 7
                        18 25 70
                        """)
                .addConverter(EnumConverter.TEMPERATURE, """
                        45 77 23
                        81 45 19
                        68 64 13
                        """)
                .addConverter(EnumConverter.HUMIDITY, """
                        0 69 1
                        1 0 69
                        """)
                .addConverter(EnumConverter.LOCATION, """
                        60 56 37
                        56 93 4
                        """);
    }

    @Test
    void seeds() {
        assertThat(seedFertilizer.getSeeds())
                .hasSize(4)
                .containsExactly(79l, 14l, 55l, 13l);
    }

    @Test
    void seedToSoil() {
        assertThat(seedFertilizer.convert(79, EnumConverter.SOIL))
                .isEqualTo(81);
        assertThat(seedFertilizer.convert(14, EnumConverter.SOIL))
                .isEqualTo(14);
        assertThat(seedFertilizer.convert(55, EnumConverter.SOIL))
                .isEqualTo(57);
        assertThat(seedFertilizer.convert(13, EnumConverter.SOIL))
                .isEqualTo(13);
    }

    @Test
    void toFertilizer() {
        assertThat(seedFertilizer.convert(79, EnumConverter.SOIL, EnumConverter.FERTILIZER))
                .isEqualTo(81);
        assertThat(seedFertilizer.convert(14, EnumConverter.SOIL, EnumConverter.FERTILIZER))
                .isEqualTo(53);
        assertThat(seedFertilizer.convert(55, EnumConverter.SOIL, EnumConverter.FERTILIZER))
                .isEqualTo(57);
        assertThat(seedFertilizer.convert(13, EnumConverter.SOIL, EnumConverter.FERTILIZER))
                .isEqualTo(52);
    }

    @Test
    void toWater() {
        assertThat(seedFertilizer.convert(79, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER))
                .isEqualTo(81);
        assertThat(seedFertilizer.convert(14, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER))
                .isEqualTo(49);
        assertThat(seedFertilizer.convert(55, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER))
                .isEqualTo(53);
        assertThat(seedFertilizer.convert(13, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER))
                .isEqualTo(41);
    }

    @Test
    void toLight() {
        assertThat(seedFertilizer.convert(79, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT))
                .isEqualTo(74);
        assertThat(seedFertilizer.convert(14, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT))
                .isEqualTo(42);
        assertThat(seedFertilizer.convert(55, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT))
                .isEqualTo(46);
        assertThat(seedFertilizer.convert(13, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT))
                .isEqualTo(34);
    }

    @Test
    void toLocation() {
        assertThat(seedFertilizer.convert(79, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT, EnumConverter.TEMPERATURE, EnumConverter.HUMIDITY, EnumConverter.LOCATION))
                .isEqualTo(82);
        assertThat(seedFertilizer.convert(14, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT, EnumConverter.TEMPERATURE, EnumConverter.HUMIDITY, EnumConverter.LOCATION))
                .isEqualTo(43);
        assertThat(seedFertilizer.convert(55, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT, EnumConverter.TEMPERATURE, EnumConverter.HUMIDITY, EnumConverter.LOCATION))
                .isEqualTo(86);
        assertThat(seedFertilizer.convert(13, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT, EnumConverter.TEMPERATURE, EnumConverter.HUMIDITY, EnumConverter.LOCATION))
                .isEqualTo(35);
    }

    @Test
    void minimumlocation() {
        assertThat(seedFertilizer.minimumLocation())
                .isEqualTo(35);
    }
}
