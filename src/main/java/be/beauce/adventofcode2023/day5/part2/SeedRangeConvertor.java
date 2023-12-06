package be.beauce.adventofcode2023.day5.part2;

public record SeedRangeConvertor(
    long start,
    long end,
    long operand
) {
    public SeedRange applyOperand() {
        return new SeedRange(start() + operand(), end() + operand());
    }
}
