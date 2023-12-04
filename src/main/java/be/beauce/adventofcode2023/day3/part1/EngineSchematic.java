package be.beauce.adventofcode2023.day3.part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EngineSchematic {
    private final List<Line> lines;

    public EngineSchematic(String input) {
        this.lines = new ArrayList<>();
        String[] split = input.split("\n");
        for (int lineIndex = 0, splitLength = split.length; lineIndex < splitLength; lineIndex++) {
            String line = split[lineIndex];
            this.lines.add(new Line(lineIndex, line));
        }
    }

    public List<Line> lines() {
        return lines;
    }

    public Numbers getNumbers(int lineIndex) {
        return lines.get(lineIndex).numbers();
    }

    public Symbols getSymbols(int lineIndex) {
        return lines.get(lineIndex).symbols();
    }

    public Set<NumberInfo> getAdjacentNumbers(int lineIndex, SymbolInfo firstSymbol) {
        int previousLineIndex = lineIndex - 1;
        int nextLineIndex = lineIndex + 1;
        Set<NumberInfo> adjacentNumbers = new HashSet<>();
        if (isValidLineIndex(previousLineIndex)) {
            adjacentNumbers.addAll(getAdjacentNumbersFromLineIndex(previousLineIndex, firstSymbol));
        }
        if (isValidLineIndex(lineIndex)) {
            adjacentNumbers.addAll(getAdjacentNumbersFromLineIndex(lineIndex, firstSymbol));
        }
        if (isValidLineIndex(nextLineIndex)) {
            adjacentNumbers.addAll(getAdjacentNumbersFromLineIndex(nextLineIndex, firstSymbol));
        }
        return adjacentNumbers;
    }

    private Set<NumberInfo> getAdjacentNumbersFromLineIndex(int lineIndex, SymbolInfo symbolInfo) {
        Set<NumberInfo> adjacentNumbers = new HashSet<>();
        Numbers numbers = getNumbers(lineIndex);
        for (NumberInfo numberInfo : numbers.asList()) {
            if (numberInfo.indexBetween(lineIndex, symbolInfo.startIndex())) {
                adjacentNumbers.add(numberInfo);
            }
        }
        return adjacentNumbers;
    }

    private boolean isValidLineIndex(int lineIndex) {
        return lineIndex >= 0 && lineIndex < lines.size();
    }

    public int getNumbersSum() {
        Set<NumberInfo> adjacentNumbers = new HashSet<>();
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            Symbols symbols = getSymbols(lineIndex);
            System.out.println("line %s: %s".formatted(lineIndex, symbols.asList()));
            for (SymbolInfo symbolInfo : symbols.asList()) {
                adjacentNumbers.addAll(getAdjacentNumbers(lineIndex, symbolInfo));
            }
        }
        adjacentNumbers.stream()
                .filter(numberInfo -> numberInfo.lineIndex() == 0)
                .sorted(Comparator.comparingInt(NumberInfo::startIndex))
                .forEach(System.out::println);
        return adjacentNumbers
                .stream()
                .mapToInt(NumberInfo::number)
                .sum();
    }
}
