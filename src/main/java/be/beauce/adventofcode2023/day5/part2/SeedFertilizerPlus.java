package be.beauce.adventofcode2023.day5.part2;

import be.beauce.adventofcode2023.day5.Input;

import java.util.ArrayList;
import java.util.List;

public class SeedFertilizerPlus {

    private final List<SeedRange> initialSeedRanges;

    public SeedFertilizerPlus(String[] initialSeedRanges) {
        this.initialSeedRanges = getInitialSeedRanges(initialSeedRanges);
    }

    public List<SeedRange> initialSeedRanges() {
        return this.initialSeedRanges;
    }

    public static void main(String[] args) {
        SeedFertilizerPlus seedFertilizerPlus = new SeedFertilizerPlus(Input.SEEDS_AS_ARRAY);
        var seedRanges = new SeedRanges(seedFertilizerPlus.initialSeedRanges());
        var seedRangeSoil = new SeedRanges(seedRanges.convertTo(seedFertilizerPlus.getSeedRanges(Input.SEED_TO_SOIL)));
        var seedRangeFertilizer = new SeedRanges(seedRangeSoil.convertTo(seedFertilizerPlus.getSeedRanges(Input.SOIL_TO_FERTILIZER)));
        var seedRangeWater = new SeedRanges(seedRangeFertilizer.convertTo(seedFertilizerPlus.getSeedRanges(Input.FERTILIZER_TO_WATER)));
        var seedRangeLight = new SeedRanges(seedRangeWater.convertTo(seedFertilizerPlus.getSeedRanges(Input.WATER_TO_LIGHT)));
        var seedRangeTemperature = new SeedRanges(seedRangeLight.convertTo(seedFertilizerPlus.getSeedRanges(Input.LIGHT_TO_TEMPERATURE)));
        var seedRangeHumidity = new SeedRanges(seedRangeTemperature.convertTo(seedFertilizerPlus.getSeedRanges(Input.TEMPERATURE_TO_HUMIDITY)));
        var seedRangeLocation = new SeedRanges(seedRangeHumidity.convertTo(seedFertilizerPlus.getSeedRanges(Input.HUMIDITY_TO_LOCATION)));
        var minimumValue = seedRangeLocation.asSet().stream()
                .mapToLong(SeedRange::start)
                .min()
                .getAsLong();
        System.out.println(minimumValue);
    }

    public List<SeedRange> getInitialSeedRanges(String[] initialSeedRanges) {
        var seedRanges = new ArrayList<SeedRange>();
        for (int i = 0; i < initialSeedRanges.length; i++) {
            long seedStart = Long.parseLong(initialSeedRanges[i]);
            long seedEnd = seedStart + Long.parseLong(initialSeedRanges[++i]);
            seedRanges.add(new SeedRange(seedStart, seedEnd));
        }
        return seedRanges;
    }

    public List<SeedRangeConvertor> getSeedRanges(String input) {
        String[] lines = input.split("\n");
        List<SeedRangeConvertor> seedRanges = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(" ");
            long destination = Long.parseLong(split[0]);
            long source = Long.parseLong(split[1]);
            long range = Long.parseLong(split[2]);
            seedRanges.add(new SeedRangeConvertor(source, source + range, destination-source));
        }
        return seedRanges;
    }
}
