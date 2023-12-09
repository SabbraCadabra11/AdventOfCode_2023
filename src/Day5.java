import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 {
    public static void solution() {
        var almanac = Main.getInput("resources/day5_input.txt");
        var allSeeds = getAllSeeds(almanac.get(0));
        var seedToSoilMap = getSourceToDestinationMap(almanac, "soil-to-fertilizer");
    }

    private static Map<Long, Long> getSourceToDestinationMap(List<String> almanac, String sectionTitle) {
        Map<Long, Long> mapping = new HashMap<>();
        int sectionStart = findSectionStartIndex(almanac, sectionTitle);
        for (int i = sectionStart; i < almanac.size(); i++) {
            if (almanac.get(i).isEmpty()) {
                break;
            }
            var line = almanac.get(i).split(" ");
            long source = Long.parseLong(line[1]);
        }

        return mapping;
    }

    private static int findSectionStartIndex(List<String> almanac, String sectionTitle) {
        for (int i = 0; i < almanac.size() - 1; i++) {
            if (almanac.get(i).equals(sectionTitle))
                return i + 1;
        }

        return -1;
    }

    private static List<Long> getAllSeeds(String seedsLine) {
        var seeds = new ArrayList<Long>();
        for (String seed : seedsLine.replaceAll("seeds: ", "").split(" ")) {
            seeds.add(Long.parseLong(seed));
        }
        return seeds;
    }
}
