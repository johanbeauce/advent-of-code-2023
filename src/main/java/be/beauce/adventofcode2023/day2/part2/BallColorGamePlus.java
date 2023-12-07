package be.beauce.adventofcode2023.day2.part2;

import be.beauce.adventofcode2023.day2.Input;

import java.util.ArrayList;
import java.util.List;

public class BallColorGamePlus {

    private final List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public BallColorGamePlus(String input) {
        games = new ArrayList<>();
        var lines = input.split("\n");
        for (String line : lines) {
            games.add(new Game(line));
        }
    }

    public int getPowerSum() {
        return games.stream()
                .mapToInt(Game::getPower)
                .sum();
    }

    public static void main(String[] args) {
        BallColorGamePlus ballColorGamePlus = new BallColorGamePlus(Input.GAMES);
        System.out.println(ballColorGamePlus.getPowerSum());
    }
}
