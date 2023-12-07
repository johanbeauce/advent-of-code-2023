package be.beauce.adventofcode2023.day1.part2;

public record Digits(
        String first,
        String last
) {
    private int convertToInteger(String numberAsString) {
        return switch (numberAsString) {
            case "zero" -> 0;
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> Integer.parseInt(numberAsString);
        };
    }

    public int getNumber() {
        return Integer.parseInt("%s%s".formatted(convertToInteger(first), convertToInteger(last)));
    }
}
