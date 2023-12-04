package be.beauce.adventofcode2023.day3.part1;

public record Line(
        int index,
        String line,
        Numbers numbers,
        Symbols symbols
) {
    public Line(int lineIndex, String line) {
        this(lineIndex, line, new Numbers(lineIndex, line), new Symbols(lineIndex, line));
    }
}
