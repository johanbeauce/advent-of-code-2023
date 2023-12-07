package be.beauce.adventofcode2023.day3.part2;

import be.beauce.adventofcode2023.day3.Input;
import be.beauce.adventofcode2023.day3.part1.EngineSchematic;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EngineSchematicPlusTest {
    @Test
    void small_input() {
        var engineSchematic = new EngineSchematic("""
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598.
                """);
        assertThat(engineSchematic.getGearsSum()).isEqualTo(467835);
    }
}
