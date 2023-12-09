package be.beauce.adventofcode2023.day6.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaitForIt {

    List<RaceInstance> raceInstances;

    public WaitForIt() {
    }

    public List<RaceInstance> getRaceInstances() {
        return raceInstances;
    }

    public WaitForIt(String input) {
        raceInstances = new ArrayList<>();
        var lines = input.split("\n");
        var timesLine = lines[0];
        var distancesLine = lines[1];
        var times = Arrays.stream(timesLine.split(":")[1].split(" "))
                .filter(s -> !s.isEmpty())
                .toList();
        var distances = Arrays.stream(distancesLine.split(":")[1].split(" "))
                .filter(s -> !s.isEmpty())
                .toList();
        for (int i = 0; i < times.size(); i++) {
            raceInstances.add(new RaceInstance(Long.parseLong(times.get(i)), Long.parseLong(distances.get(i))));
        }
    }

    public int multiplyNumberOfWinsForEachRaces() {
        return raceInstances.stream()
                .map(this::getNumberOfPossibleWins)
                .reduce(1, (a, b) -> a * b);
    }

    public int getNumberOfPossibleWins(RaceInstance raceInstance) {
        var holdTime = 1;
        var numberOfPossibleWins = 0;
        while (holdTime < raceInstance.time()) {
            var boat = new Boat(holdTime, raceInstance);
            if (boat.canWin()) {
                numberOfPossibleWins++;
            }
            holdTime++;
        }
        return numberOfPossibleWins;
    }

    public static void main(String[] args) {
        var waitForIt = new WaitForIt("""
                Time:        44     89     96     91
                Distance:   277   1136   1890   1768
                """);
        System.out.println(waitForIt.multiplyNumberOfWinsForEachRaces());
    }


}
