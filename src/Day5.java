import java.util.ArrayList;
import java.util.List;

public class Day5 {
    public static void solution() {
        final var almanac = Main.getInput("resources/day5_input.txt");
        //part1(almanac);
        part2(almanac);
    }

    private static void part1(List<String> almanac) {
        final var allSeeds = getAllSeeds(almanac.get(0));
        long closestLocation = Long.MAX_VALUE;
        for (Long seed : allSeeds) {
            final long seedLocation = findSeedLocation(seed, almanac);
            if (seedLocation < closestLocation) {
                closestLocation = seedLocation;
            }
        }
        System.out.println("Day 5 part 1 solution: " + closestLocation);
    }

    private static void part2(List<String> almanac) {
        final var allSeeds = getAllSeeds(almanac.get(0));
        long closestLocation = Long.MAX_VALUE;
        for (int i = 0; i < allSeeds.size(); i += 2) {
            final long seedRangeStart = allSeeds.get(i);
            final long seedRangeEnd = seedRangeStart + allSeeds.get(i + 1);
            for (long seed = seedRangeStart; seed < seedRangeEnd; seed++) {
                final long seedLocation = findSeedLocation(seed, almanac);
                if (seedLocation < closestLocation) {
                    closestLocation = seedLocation;
                }
            }
        }
        System.out.println("Day 5 part 2 solution: " + closestLocation);
    }

    private static long findSeedLocation(long seed, List<String> almanac) {
        final long soilForSeed = findDestination(seed, almanac, "seed-to-soil");
        final long fertilizerForSoil = findDestination(soilForSeed, almanac, "soil-to-fertilizer");
        final long waterForFertilizer = findDestination(fertilizerForSoil, almanac, "fertilizer-to-water");
        final long lightForWater = findDestination(waterForFertilizer, almanac, "water-to-light");
        final long tempForLight = findDestination(lightForWater, almanac, "light-to-temperature");
        final long humidityForTemp = findDestination(tempForLight, almanac, "temperature-to-humidity");
        final long locationForHumidity = findDestination(humidityForTemp, almanac, "humidity-to-location");

        return locationForHumidity;
    }

    private static long findDestination(long source, List<String> almanac, String sectionTitle) {
        int i = findSectionStartIndex(almanac, sectionTitle);
        while (i < almanac.size() && !almanac.get(i).isEmpty()) {
            final var line = almanac.get(i);
            final long sourceRangeBegin = parseSourceRangeBegin(line);
            final long rangeLength = parseRangeLength(line);
            if (isSourceInRange(source, sourceRangeBegin, rangeLength)) {
                final long destRangeBegin = parseDestinationSourceBegin(line);
                return destRangeBegin + (source - sourceRangeBegin);
            }
            i++;
        }
        // destination = source if it's not in any range in the map
        return source;
    }

    private static boolean isSourceInRange(long source, long sourceRangeBegin, long rangeLength) {
        // sourceRangeBegin = 50, rangeLength = 3, true for source 50, 51, 52
        return source >= sourceRangeBegin && source < sourceRangeBegin + rangeLength;
    }

    private static long parseDestinationSourceBegin(String line) {
        return Long.parseLong(line.substring(0, line.indexOf(" ")).trim());
    }

    private static long parseSourceRangeBegin(String line) {
        return Long.parseLong(line.substring(line.indexOf(" "), line.lastIndexOf(" ")).trim());
    }

    private static long parseRangeLength(String line) {
        return Long.parseLong(line.substring(line.lastIndexOf(" ")).trim());
    }

    private static int findSectionStartIndex(List<String> almanac, String sectionTitle) {
        for (int i = 0; i < almanac.size() - 1; i++) {
            if (almanac.get(i).contains(sectionTitle))
                return i + 1;
        }
        return -1;
    }

    private static List<Long> getAllSeeds(String seedsLine) {
        final var seeds = new ArrayList<Long>();
        for (String seed : seedsLine.replaceAll("seeds: ", "").split(" ")) {
            seeds.add(Long.parseLong(seed));
        }
        return seeds;
    }
}
