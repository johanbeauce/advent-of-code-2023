package be.beauce.adventofcode2023.day3.part2;

import be.beauce.adventofcode2023.day3.Input;
import be.beauce.adventofcode2023.day3.part1.EngineSchematic;

public class EngineSchematicPlus {
    public static void main(String[] args) {
        var engineSchematic = new EngineSchematic(Input.TEXT);
        System.out.println(engineSchematic.getGearsSum());
    }
}
