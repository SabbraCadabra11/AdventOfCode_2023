import java.util.*;

public class Day2 {
    //Game 1: 2 blue, 4 green; 7 blue, 1 red, 14 green; 5 blue, 13 green, 1 red; 1 red, 7 blue, 11 green
    public static void solution() {
        var input = Main.getInput("resources/day2_input.txt");
        part1(input);
        part2(input);
    }

    //----------------- part 1 ---------------------------

    private static void part1(List<String> input) {
        int sum = 0;
        for (String line : input) {
            String[] parts = line.split(":");
            if (isValidGame(line)) {
                int gameId = Integer.parseInt(parts[0].replaceAll("Game ", ""));
                sum += gameId;
            }
        }
        System.out.println("Day 2 part 1 solution: " + sum);
    }

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

    //-------------- part 2 ------------------------

    private static void part2(List<String> input) {
        int sum = 0;
        for (String line : input) {
            sum += calculateGamePower(line);
        }

        System.out.println("Day 2 part 2 solution: " + sum);
    }

    private static int calculateGamePower(String game) {
        var cubesInGame = new HashMap<String, Integer>();
        cubesInGame.put("red", 0);
        cubesInGame.put("green", 0);
        cubesInGame.put("blue", 0);

        String[] sanitizedGameDraws = getLabeledGameDraws(game);
        for (String draw : sanitizedGameDraws) {
            updateCubeMap(draw, cubesInGame);
        }

        int power = 1;
        for (Integer amount : cubesInGame.values()) {
            if (amount > 0) {
                power *= amount;
            }
        }
        return power;
    }

    private static void updateCubeMap(String draw, HashMap<String, Integer> cubesInGame) {
        String[] colors = draw.split(",");
        for (String color : colors) {
            int amountOfCubes;
            if (color.contains("red")) {
                amountOfCubes = Integer.parseInt(color.replaceAll("red", ""));
                if (amountOfCubes > cubesInGame.get("red")) {
                    cubesInGame.put("red", amountOfCubes);
                }
            } else if (color.contains("green")) {
                amountOfCubes = Integer.parseInt(color.replaceAll("green", ""));
                if (amountOfCubes > cubesInGame.get("green")) {
                    cubesInGame.put("green", amountOfCubes);
                }
            } else {
                amountOfCubes = Integer.parseInt(color.replaceAll("blue", ""));
                if (amountOfCubes > cubesInGame.get("blue")) {
                    cubesInGame.put("blue", amountOfCubes);
                }
            }
        }
    }

    private static String[] getLabeledGameDraws(String game) {
        String[] labeledDraws = game.replaceAll(" ", "").split(":");
        return labeledDraws[1].split(";");
    }
}







