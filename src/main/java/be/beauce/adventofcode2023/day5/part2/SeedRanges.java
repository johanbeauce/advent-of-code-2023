package be.beauce.adventofcode2023.day5.part2;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeedRanges {
    private final Set<SeedRange> setOfseedRanges;

    public SeedRanges(Collection<SeedRange> seedRanges) {
        this.setOfseedRanges = new HashSet<>(seedRanges);
    }

    public Collection<SeedRange> convertTo(List<SeedRangeConvertor> targetSeedRanges) {
        var convertedSeedRanges = new HashSet<SeedRange>();
        for (SeedRange seedRangeSource : setOfseedRanges) {
            if (seedRangeSource.isOutsideAll(targetSeedRanges)) {
                convertedSeedRanges.add(seedRangeSource);
            }
            for(SeedRangeConvertor convertedSeedRange : targetSeedRanges) {
                if (seedRangeSource.isOutside(convertedSeedRange)) {
                    continue;
                }
                if (seedRangeSource.startsInside(convertedSeedRange)) {
                    if (seedRangeSource.endsOutside(convertedSeedRange)) {
                        convertedSeedRanges.add(new SeedRangeConvertor(seedRangeSource.start(), convertedSeedRange.end(), convertedSeedRange.operand()).applyOperand());
                        convertedSeedRanges.addAll(addRest(targetSeedRanges, new SeedRange(convertedSeedRange.end() + 1, seedRangeSource.end())));
                    }
                    else { // seed range ends inside
                        convertedSeedRanges.add(new SeedRangeConvertor(seedRangeSource.start(), seedRangeSource.end(), convertedSeedRange.operand()).applyOperand());
                    }
                }
                else { // seed range starts before
                    if (seedRangeSource.endsInside(convertedSeedRange)) {
                        convertedSeedRanges.addAll(addRest(targetSeedRanges, new SeedRange(seedRangeSource.start(), convertedSeedRange.start() - 1)));
                        convertedSeedRanges.add(new SeedRangeConvertor(convertedSeedRange.start(), seedRangeSource.end(), convertedSeedRange.operand()).applyOperand());
                    }
                    else { // seed rang ends outside
                        convertedSeedRanges.addAll(addRest(targetSeedRanges, new SeedRange(seedRangeSource.start(), convertedSeedRange.start() - 1)));
                        convertedSeedRanges.add(new SeedRangeConvertor(convertedSeedRange.start(), convertedSeedRange.end(), convertedSeedRange.operand()).applyOperand());
                        convertedSeedRanges.addAll(addRest(targetSeedRanges, new SeedRange(convertedSeedRange.end() + 1, seedRangeSource.end())));
                    }
                }
            }
        }
        return convertedSeedRanges;
    }

    private Collection<SeedRange> addRest(List<SeedRangeConvertor> targetSeedRanges,
                                          SeedRange seedRangeRest) {
        return new SeedRanges(List.of(seedRangeRest)).convertTo(targetSeedRanges);
    }

    public Set<SeedRange> asSet() {
        return setOfseedRanges;
    }
}
