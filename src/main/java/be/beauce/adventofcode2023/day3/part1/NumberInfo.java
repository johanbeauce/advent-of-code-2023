package be.beauce.adventofcode2023.day3.part1;

public record NumberInfo(
        int number,
        int lineIndex,
        int startIndex
) {
    public boolean isAdjacent(int lineIndex, int symbolIndex) {
//        System.out.println("is number %s on line %s: start of number between %s and %s in line %s for symbol %s"
//                .formatted(number, lineIndex, symbolIndex + 1, symbolIndex - 1, lineIndex, symbolIndex));
        int endNumberIndex = startIndex + String.valueOf(number).length() - 1;
        return this.lineIndex == lineIndex
                && (isBetween(startIndex, symbolIndex - 1, symbolIndex + 1)
                    || isBetween(endNumberIndex, symbolIndex - 1, symbolIndex + 1)
        );
    }

    private boolean isBetween(int startNumberIndex, int start, int end) {
        return startNumberIndex >= start && startNumberIndex <= end;
    }
}
