import java.util.ArrayList;
import java.util.List;

public class Day6 {
    public static void solution() {
        var input = Main.getInput("resources/day6_input.txt");
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        var times = getNumbersFromInput(input.get(0));
        var distances = getNumbersFromInput(input.get(1));
        int answer = 1;

        for (int i = 0; i < times.size(); i++) {
            int combinations = 0;
            int raceTime = times.get(i);
            int bestRaceDistance = distances.get(i);
            for (int time = 1; time < raceTime; time++) {
                if (calculateDistance(time, raceTime) > bestRaceDistance) {
                    combinations++;
                }
            }
            answer *= combinations;
        }

        System.out.println("Day 6 part 1 solution: " + answer);
    }

    private static void part2(List<String> input) {
        long raceTime = Long.parseLong(input.get(0).replaceAll("[^0-9]", ""));
        long raceDistance = Long.parseLong(input.get(1).replaceAll("[^0-9]", ""));

        int answer = 0;
        for (long time = 1; time < raceTime; time++) {
            if (calculateDistance(time, raceTime) > raceDistance) {
                answer++;
            }
        }

        System.out.println("Day 6 part 2 solution: " + answer);
    }

    private static int calculateDistance(int timeButtonPressed, int totalTime) {
        return (totalTime - timeButtonPressed) * timeButtonPressed;
    }

    private static long calculateDistance(long timeButtonPressed, long totalTime) {
        return (totalTime - timeButtonPressed) * timeButtonPressed;
    }

    private static List<Integer> getNumbersFromInput(String line) {
        var numbers = new ArrayList<Integer>();
        for (String numStr : line.replaceAll("[A-Za-z0-9]+:\\s", "").split(" ")) {
            if (numStr.isEmpty()) {
                continue;
            }
            numbers.add(Integer.parseInt(numStr));
        }
        return numbers;
    }
}
