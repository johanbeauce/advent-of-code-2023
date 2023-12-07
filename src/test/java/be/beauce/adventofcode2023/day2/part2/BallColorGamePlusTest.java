package be.beauce.adventofcode2023.day2.part2;

import be.beauce.adventofcode2023.day2.part1.BallColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BallColorGamePlusTest {
    BallColorGamePlus ballColorGamePlus;

    @BeforeEach
    void setUp() {
        ballColorGamePlus = new BallColorGamePlus("""
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                """);
    }

    @Test
    void number_of_game_should_be_5() {
        assertThat(ballColorGamePlus.getGames()).hasSize(5);
    }

    @Test
    void number_of_sets() {
        var games = ballColorGamePlus.getGames();
        assertThat(games.get(0).gamesSets()).hasSize(3);
        assertThat(games.get(1).gamesSets()).hasSize(3);
        assertThat(games.get(2).gamesSets()).hasSize(3);
        assertThat(games.get(3).gamesSets()).hasSize(3);
        assertThat(games.get(4).gamesSets()).hasSize(2);
    }

    @Test
    void color_of_game_set() {
        var games = ballColorGamePlus.getGames();

        var firstGame = games.get(0);
        assertThat(firstGame.getMaxBallNumber(BallColor.RED)).isEqualTo(4);
        assertThat(firstGame.getMaxBallNumber(BallColor.GREEN)).isEqualTo(2);
        assertThat(firstGame.getMaxBallNumber(BallColor.BLUE)).isEqualTo(6);

        var secondGame = games.get(1);
        assertThat(secondGame.getMaxBallNumber(BallColor.RED)).isEqualTo(1);
        assertThat(secondGame.getMaxBallNumber(BallColor.GREEN)).isEqualTo(3);
        assertThat(secondGame.getMaxBallNumber(BallColor.BLUE)).isEqualTo(4);

        var thirdGame = games.get(2);
        assertThat(thirdGame.getMaxBallNumber(BallColor.RED)).isEqualTo(20);
        assertThat(thirdGame.getMaxBallNumber(BallColor.GREEN)).isEqualTo(13);
        assertThat(thirdGame.getMaxBallNumber(BallColor.BLUE)).isEqualTo(6);

        var fourthGame = games.get(3);
        assertThat(fourthGame.getMaxBallNumber(BallColor.RED)).isEqualTo(14);
        assertThat(fourthGame.getMaxBallNumber(BallColor.GREEN)).isEqualTo(3);
        assertThat(fourthGame.getMaxBallNumber(BallColor.BLUE)).isEqualTo(15);

        var fifthGame = games.get(4);
        assertThat(fifthGame.getMaxBallNumber(BallColor.RED)).isEqualTo(6);
        assertThat(fifthGame.getMaxBallNumber(BallColor.GREEN)).isEqualTo(3);
        assertThat(fifthGame.getMaxBallNumber(BallColor.BLUE)).isEqualTo(2);
    }

    @Test
    void power_of_game_set() {
        var games = ballColorGamePlus.getGames();

        var firstGame = games.get(0);
        assertThat(firstGame.getPower()).isEqualTo(48);

        var secondGame = games.get(1);
        assertThat(secondGame.getPower()).isEqualTo(12);

        var thirdGame = games.get(2);
        assertThat(thirdGame.getPower()).isEqualTo(1560);

        var fourthGame = games.get(3);
        assertThat(fourthGame.getPower()).isEqualTo(630);

        var fifthGame = games.get(4);
        assertThat(fifthGame.getPower()).isEqualTo(36);
    }

    @Test
    void get_sum_of_power() {
        assertThat(ballColorGamePlus.getPowerSum()).isEqualTo(2286);
    }
}
