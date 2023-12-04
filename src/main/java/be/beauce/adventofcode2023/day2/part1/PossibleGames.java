package be.beauce.adventofcode2023.day2.part1;

import java.util.HashMap;
import java.util.Map;

public class PossibleGames {

    private final Map<Integer, Map<BallColor, Integer>> ballSumPerGame;
    private final Map<Integer, Map<BallColor, Integer>> maxBallsPerGame;

    public PossibleGames(String input) {
        ballSumPerGame = new HashMap<>();
        maxBallsPerGame = new HashMap<>();
        var lines = input.split("\n");
        for (String line : lines) {
            var gameNumber =  Integer.parseInt(line.split(":")[0].substring(5));
            var sumOfBalls = ballSumPerGame.getOrDefault(gameNumber, new HashMap<>());
            var maxBalls = maxBallsPerGame.getOrDefault(gameNumber, new HashMap<>());
            var sets = line.split(":")[1].trim().split("; ");
            for (String set : sets) {
                var balls = set.split(", ");
                for (String ball : balls) {
                    var ballNumber = Integer.parseInt(ball.split(" ")[0]);
                    var ballColor = ball.split(" ")[1];

                    var ballNumberTotal = sumOfBalls.getOrDefault(BallColor.valueOf(ballColor.toUpperCase()), 0);
                    ballNumberTotal += ballNumber;
                    sumOfBalls.put(BallColor.valueOf(ballColor.toUpperCase()), ballNumberTotal);

                    var maxBallNumber = maxBalls.getOrDefault(BallColor.valueOf(ballColor.toUpperCase()), 0);
                    if (ballNumber > maxBallNumber) {
                        maxBalls.put(BallColor.valueOf(ballColor.toUpperCase()), ballNumber);
                    }
                }
            }
            ballSumPerGame.put(gameNumber, sumOfBalls);
            maxBallsPerGame.put(gameNumber, maxBalls);
        }
    }

    public int getNumberOfBalls(int gameNumber, BallColor ball) {
        return ballSumPerGame.get(gameNumber).get(ball);
    }

    public Integer getSumOfValidGames(Map<BallColor, Integer> maxBallNumbers) {
        int validGamesTotal = 0;
        for (Map.Entry<Integer, Map<BallColor, Integer>> gameIndexEntry : ballSumPerGame.entrySet()) {
            var gameBallNumbers = gameIndexEntry.getValue();
            System.out.println("game " + gameIndexEntry.getKey() + " " + gameBallNumbers);
            if (noTooMuchRedBalls(maxBallNumbers, gameBallNumbers)
                    && noTooMuchGreen(maxBallNumbers, gameBallNumbers)
                    && noTooMuchBlue(maxBallNumbers, gameBallNumbers)) {
                System.out.println("Game " + gameIndexEntry.getKey() + " is valid");
                validGamesTotal += gameIndexEntry.getKey();
            }
        }
        return validGamesTotal;
    }

    public Integer getSumOfValidGamesByMax(Map<BallColor, Integer> maxBallNumbers) {
        int validGamesTotal = 0;
        for (Map.Entry<Integer, Map<BallColor, Integer>> gameIndexEntry : maxBallsPerGame.entrySet()) {
            var maximumBallPerColor = gameIndexEntry.getValue();
            System.out.println("game " + gameIndexEntry.getKey() + " " + maximumBallPerColor);
            if (noTooMuchRedBalls(maxBallNumbers, maximumBallPerColor)
                    && noTooMuchGreen(maxBallNumbers, maximumBallPerColor)
                    && noTooMuchBlue(maxBallNumbers, maximumBallPerColor)) {
                System.out.println("Game " + gameIndexEntry.getKey() + " is valid");
                validGamesTotal += gameIndexEntry.getKey();
            }
        }
        return validGamesTotal;
    }

    private boolean noTooMuchRedBalls(Map<BallColor, Integer> maxBallNumbers, Map<BallColor, Integer> maximumBallPerColor) {
        var isRedOk = maximumBallPerColor.get(BallColor.RED) <= maxBallNumbers.get(BallColor.RED);
        if (!isRedOk) {
            System.out.println("\tToo much red balls");
        }
        return isRedOk;
    }

    private boolean noTooMuchGreen(Map<BallColor, Integer> maxBallNumbers, Map<BallColor, Integer> maximumBallPerColor) {
        var isGreenOK = maximumBallPerColor.get(BallColor.GREEN) <= maxBallNumbers.get(BallColor.GREEN);
        if (!isGreenOK) {
            System.out.println("\tToo much green balls");
        }
        return isGreenOK;
    }

    private boolean noTooMuchBlue(Map<BallColor, Integer> maxBallNumbers, Map<BallColor, Integer> maximumBallPerColor) {
        var isBlueOk = maximumBallPerColor.get(BallColor.BLUE) <= maxBallNumbers.get(BallColor.BLUE);
        if (!isBlueOk) {
            System.out.println("\tToo much blue balls");
        }
        return isBlueOk;
    }

    class BallNumber {
        private final BallColor ball;
        private final int number;

        public BallNumber(BallColor ball) {
            this.ball = ball;
            this.number = 0;
        }

        public BallColor getBall() {
            return ball;
        }

        public int getNumber() {
            return number;
        }
    }
}
