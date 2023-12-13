import java.util.HashMap;
import java.util.List;

public class Day8 {
    public static void solution() {
        var input = Main.getInput("resources/day8_input.txt");
        part1(input);
    }

    private static void part1(List<String> input) {
        var turnInstructions = input.get(0).toCharArray();
        var map = new HashMap<String, Coordinates>();
        for (int i = 2; i < input.size(); i++) {
            var line = input.get(i);
            var place = line.substring(0, 3);
            var coordinatesStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
            var coordinates = new Coordinates(coordinatesStr);
            map.put(place, coordinates);
        }
        int turns = 0;
        var nextCoordinates = map.get("AAA");
        String nextPlace = "";
        while (!nextPlace.equals("ZZZ")) {
            char direction = turnInstructions[turns % turnInstructions.length];
            turns++;
            nextPlace = nextCoordinates.getPlace(direction);
            nextCoordinates = map.get(nextPlace);
        }
        System.out.println("Day 8 part 1 solution: " + turns);
    }

    private static class Coordinates {
        String left, right;
        Coordinates(String coordinates) {
            final var parts = coordinates.split(", ");
            left = parts[0];
            right = parts[1];
        }

        String getPlace(char direction) {
            return direction == 'L' ? left : right;
        }
    }
}
