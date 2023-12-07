package be.beauce.adventofcode2023.day1.part2;

import java.util.Arrays;
import java.util.List;

public class IntegerNamedLineComputer {
    private static final List<String> numbers = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    public Digits getFirstAndLastDigit(String input) {
        var firstNumberIndexFound = getFirstNumberIndexFound(input);
        var lastNumberIndexFound = getLastNumberIndexFound(input);

        return new Digits(
                numbers.get(firstNumberIndexFound),
                numbers.get(lastNumberIndexFound));
    }

    private int getFirstNumberIndexFound(String input) {
        var firstDigitIndex = -1;
        var firstNumberIndexFound = -1;
        for (int numberIndex = 0; numberIndex < numbers.size(); numberIndex++) {
            var number = numbers.get(numberIndex);
            var index = input.indexOf(number);
            if (firstDigitIndex == -1 || (index >= 0 && index < firstDigitIndex)) {
                firstDigitIndex = index;
                firstNumberIndexFound = numberIndex;
            }
        }
        return firstNumberIndexFound;
    }

    private int getLastNumberIndexFound(String input) {
        var lastDigitIndex = -1;
        var lastNumberIndexFound = -1;
        for (int numberIndex = 0; numberIndex < numbers.size(); numberIndex++) {
            var number = numbers.get(numberIndex);
            var index = input.lastIndexOf(number);
            if (lastDigitIndex == -1 || (index >= 0 && index > lastDigitIndex)) {
                lastDigitIndex = index;
                lastNumberIndexFound = numberIndex;
            }
        }
        return lastNumberIndexFound;
    }

    public int getSum(String input) {
        var lines = input.split("\n");
        return Arrays.stream(lines)
                .map(this::getFirstAndLastDigit)
                .mapToInt(Digits::getNumber)
                .sum();
    }
}
