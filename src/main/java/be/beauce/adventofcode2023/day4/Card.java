package be.beauce.adventofcode2023.day4;

import java.util.Arrays;
import java.util.List;

public record Card(
        int index,
        int number,
        List<Integer> winningNumbers,
        List<Integer> numbers
) {
    public Card(int index,int number, String winningNumbers, String numbers) {
        this(index, number, convertToNumbers(winningNumbers), convertToNumbers(numbers));
    }

    private static List<Integer> convertToNumbers(String numbers) {
        return Arrays.stream(numbers.split(" "))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();
    }

    public int match() {
        int match = 0;
        for (Integer number : numbers) {
            for (Integer winningNumber : winningNumbers) {
                if (number.equals(winningNumber)) {
                    match++;
                }
            }
        }
        return match;
    }

    public int total() {
        int total = 0;
        for (Integer number : numbers) {
            for (Integer winningNumber : winningNumbers) {
                if (number.equals(winningNumber)) {
                    if (total == 0) {
                        total = 1;
                    } else {
                        total *= 2;
                    }
                }
            }
        }
        return total;
    }
}
