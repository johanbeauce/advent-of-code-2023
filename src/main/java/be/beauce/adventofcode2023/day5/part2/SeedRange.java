package be.beauce.adventofcode2023.day5.part2;

import java.util.List;

public record SeedRange(
        long start,
        long end
) {

    public boolean isOutside(SeedRangeConvertor seedRange) {
        return start() > seedRange.end() || end() < seedRange.start();
    }

    public boolean startsInside(SeedRangeConvertor seedRange) {
        return start() >= seedRange.start();
    }

    public boolean endsOutside(SeedRangeConvertor seedRange) {
        return end() > seedRange.end();
    }

    public boolean endsInside(SeedRangeConvertor seedRange) {
        return end() <= seedRange.end();
    }

    public boolean isOutsideAll(List<SeedRangeConvertor> seedRanges) {
        return seedRanges.stream()
                .allMatch(this::isOutside);
    }
}
