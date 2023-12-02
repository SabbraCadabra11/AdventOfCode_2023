import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {
    //Game 1: 2 blue, 4 green; 7 blue, 1 red, 14 green; 5 blue, 13 green, 1 red; 1 red, 7 blue, 11 green
    public static void solution() {
        var input = Main.getInput("resources/day2_input.txt");
        part1(input);
    }

    private static void part1(List<String> input) {
        var sum = new AtomicInteger();
        input.forEach(line -> {
            String[] parts = line.split(":");
            if (isValidGame(line)) {
                int gameId = Integer.parseInt(parts[0].replaceAll("Game ", ""));
                sum.getAndAdd(gameId);
            }
        });
        System.out.println("\nDay 2 part 1 solution: " + sum.get());
    }

//    private static boolean isValidGame(String line) {
//        String[] sanitizedDraws = getLabeledGameDraws(line); //should return e.g. {"2blue,4green", "7blue,1red,14green"}
//        Map<String, Integer> drawSums = new HashMap<>();
//        drawSums.put("red", 0);
//        drawSums.put("green", 0);
//        drawSums.put("blue", 0);
//        Arrays.stream(sanitizedDraws).forEach(draw -> //"2blue,4green"
//            Arrays.stream(draw.split(",")).forEach(color -> { //2blue
//                if (color.contains("red")) {
//                    drawSums.put("red", drawSums.get("red") + Integer.parseInt(color.replaceAll("red", "")));
//                } else if (color.contains("green")) {
//                    drawSums.put("green", drawSums.get("green") + Integer.parseInt(color.replaceAll("green", "")));
//                } else {
//                    drawSums.put("blue", drawSums.get("blue") + Integer.parseInt(color.replaceAll("blue", "")));
//                }
//            })
//        );
//        drawSums.forEach((color, sum) -> {
//            System.out.printf("%s: %d%n", color, sum);
//        });
//        return drawSums.get("red") <= 12 &&
//                drawSums.get("green") <= 13 &&
//                drawSums.get("blue") <= 14;
//    }

    private static boolean isValidGame(String game) {
        String[] sanitizedDraws = getLabeledGameDraws(game); //should return e.g. {"2blue,4green", "7blue,1red,14green"}
        for (String draw : sanitizedDraws) {
            String[] colors = draw.split(",");
            for (String color : colors) {
                if (color.contains("red")) {
                    if (Integer.parseInt(color.replaceAll("red", "")) > 12) {
                        return false;
                    }
                } else if (color.contains("green")) {
                    if (Integer.parseInt(color.replaceAll("green", "")) > 13) {
                        return false;
                    }
                } else {
                    if (Integer.parseInt(color.replaceAll("blue", "")) > 14) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static String[] getLabeledGameDraws(String line) {
        String[] labeledDraws = line.replaceAll(" ", "").split(":");
        return labeledDraws[1].split(";");
    }
}







