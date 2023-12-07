package be.beauce.adventofcode2023.day2.part1;

import be.beauce.adventofcode2023.day2.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PossibleGamesTest {
    PossibleGames possibleGames;

    @BeforeEach
    void setUp() {
        possibleGames = new PossibleGames(Input.GAMES);
    }

    @Test
    void game_1_should_have_3_sets() {
        assertThat(possibleGames.getNumberOfBalls(1, BallColor.BLUE)).isEqualTo(9);
        assertThat(possibleGames.getNumberOfBalls(1, BallColor.RED)).isEqualTo(5);
        assertThat(possibleGames.getNumberOfBalls(1, BallColor.GREEN)).isEqualTo(4);
    }

    @Test
    void calculate_sum_of_valid_games() {
        assertThat(possibleGames.getSumOfValidGamesByMax(Map.of(BallColor.RED, 12, BallColor.GREEN, 13, BallColor.BLUE, 14)))
                .isEqualTo(99 + 65 + 29);
    }
}
