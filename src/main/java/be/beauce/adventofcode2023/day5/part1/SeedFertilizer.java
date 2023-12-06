package be.beauce.adventofcode2023.day5.part1;

import be.beauce.adventofcode2023.day5.ConversionRule;
import be.beauce.adventofcode2023.day5.EnumConverter;
import be.beauce.adventofcode2023.day5.Input;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class SeedFertilizer {

    private final List<Long> seeds;
    private final EnumMap<EnumConverter, List<ConversionRule>> converterMap;

    public SeedFertilizer(List<Long> seeds) {
        this.seeds = seeds;
        this.converterMap = new EnumMap<>(EnumConverter.class);
    }

    public List<Long> getSeeds() {
        return seeds;
    }

    public static SeedFertilizerBuilder aSeedFertilizer() {
        return new SeedFertilizerBuilder();
    }

    public SeedFertilizer addConverter(EnumConverter enumConverter, String input) {
        converterMap.put(enumConverter, toIncreaseNumbers(input));
        return this;
    }

    private List<ConversionRule> toIncreaseNumbers(String input) {
        List<ConversionRule> conversionRules = new ArrayList<>();
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] split = line.split(" ");
            long destination = Long.parseLong(split[0]);
            long source = Long.parseLong(split[1]);
            long elementNumber = Long.parseLong(split[2]);
            conversionRules.add(new ConversionRule(source, destination, elementNumber));
        }
        return conversionRules;
    }

    public long minimumLocation() {
        return seeds.stream()
                .map(seed -> convert(seed, EnumConverter.SOIL, EnumConverter.FERTILIZER, EnumConverter.WATER, EnumConverter.LIGHT, EnumConverter.TEMPERATURE, EnumConverter.HUMIDITY, EnumConverter.LOCATION))
                .min(Long::compareTo)
                .orElseThrow(() -> new RuntimeException("No minimum location found"));
    }

    public long convert(long seed, EnumConverter... enumConverter) {
        long result = seed;
        for (EnumConverter converter : enumConverter) {
            result = convertByConversionRule(result, converter);
        }
        return result;
    }

    private long convertByConversionRule(final long source,
                                         EnumConverter converter) {
        return converterMap.get(converter).stream()
                .filter(conversionRule -> conversionRule.source() <= source && source <= conversionRule.source() + conversionRule.operand())
                .findFirst()
                .map(conversionRule -> conversionRule.destination() + source - conversionRule.source())
                .orElse(source);
    }

    public static class SeedFertilizerBuilder {

        private List<Long> seeds;


        public SeedFertilizerBuilder withLongSeeds(List<Long> seeds) {
            this.seeds = seeds;
            return this;
        }

        public SeedFertilizerBuilder withSeeds(List<String> seeds) {
            this.seeds = seeds.stream()
                    .map(Long::parseLong)
                    .toList();
            return this;
        }

        public SeedFertilizer build() {
            return new SeedFertilizer(seeds)
                    .addConverter(EnumConverter.SOIL, Input.SEED_TO_SOIL)
                    .addConverter(EnumConverter.FERTILIZER,Input.SOIL_TO_FERTILIZER)
                    .addConverter(EnumConverter.WATER, Input.FERTILIZER_TO_WATER)
                    .addConverter(EnumConverter.LIGHT, Input.WATER_TO_LIGHT)
                    .addConverter(EnumConverter.TEMPERATURE, Input.LIGHT_TO_TEMPERATURE)
                    .addConverter(EnumConverter.HUMIDITY, Input.TEMPERATURE_TO_HUMIDITY)
                    .addConverter(EnumConverter.LOCATION, Input.HUMIDITY_TO_LOCATION);
        }
    }

    public static void main(String[] args) {
        SeedFertilizer seedFertilizer = aSeedFertilizer()
                .withSeeds(Input.SEEDS)
                .build();

        System.out.println("minimum = " + seedFertilizer.minimumLocation());
    }

}
