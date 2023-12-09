package be.beauce.adventofcode2023.day6.part2;

import be.beauce.adventofcode2023.day6.part1.WaitForIt;

public class WaitForItPlus {
    public static void main(String[] args) {
        var waitForIt = new WaitForIt("""
                Time:       44899691
                Distance:   277113618901768
                """);
        System.out.println(waitForIt.multiplyNumberOfWinsForEachRaces());
    }
}
