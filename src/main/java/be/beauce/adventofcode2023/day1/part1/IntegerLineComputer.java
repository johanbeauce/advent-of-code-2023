package be.beauce.adventofcode2023.day1.part1;

public class IntegerLineComputer {
    public int compute(String input) {
        var lines = input.split("\n");
        int result = 0;
        for (String line : lines) {
            String onlyNumber = line.replaceAll("[^0-9]", "");
            var firstNumber = onlyNumber.charAt(0);
            var lastNumber = onlyNumber.charAt(onlyNumber.length()-1);
            result += Integer.parseInt(firstNumber + String.valueOf(lastNumber));
        }
        return result;
    }
}
