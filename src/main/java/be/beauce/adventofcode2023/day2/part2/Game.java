package be.beauce.adventofcode2023.day2.part2;

import be.beauce.adventofcode2023.day2.part1.BallColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Game(
        List<GameSet> gamesSets
) {
    public Game(String line) {
        this(initSets(line));
    }

    private static List<GameSet> initSets(String line) {
        var gameSets = new ArrayList<GameSet>();
        var lineSets = line.split(":")[1].trim().split("; ");
        for (String lineSet : lineSets) {
            var balls = new ArrayList<Balls>();
            var lineBalls = lineSet.trim().split(", ");
            for (String lineBall : lineBalls) {
                var ballNumber = Integer.parseInt(removeLeftAndRightSpace(lineBall).split(" ")[0]);
                var ballColor = removeLeftAndRightSpace(lineBall).split(" ")[1];
                balls.add(new Balls(ballNumber, BallColor.valueOf(ballColor.toUpperCase())));
            }
            gameSets.add(new GameSet(balls));
        }
        return gameSets;
    }

    private static String removeLeftAndRightSpace(String input) {
        return input.replaceAll("\\s*$", "").replaceAll("^\\s*", "");
    }

    public int getMaxBallNumber(BallColor ballColor) {
        return gamesSets.stream()
                .map(gameSet -> gameSet.balls().stream()
                        .filter(balls -> balls.ballColor().equals(ballColor))
                        .mapToInt(Balls::ballNumber)
                        .max()
                        .orElse(0))
                .max(Integer::compareTo)
                .orElse(0);
    }

    public int getPower() {
        return Arrays.stream(BallColor.values())
                .map(this::getMaxBallNumber)
                .reduce(1, (a, b) -> a * b);
    }
}
