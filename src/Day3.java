import java.util.HashMap;
import java.util.List;

public class Day3 {
    public static void solution() {
        var input = Main.getInput("resources/day3_input.txt");
        var engineLayoutArray = transformInputTo2dArray(input);
        var partNumbersWithCoordinates = new HashMap<String, Integer>();
        int sum = 0;

        for (int r = 0; r < engineLayoutArray.length; r++) {
            for (int c = 0; c < engineLayoutArray[0].length; c++) {
                if (isEnginePart(engineLayoutArray[r][c])) {
                    addAdjacentPartsToMap(r, c, engineLayoutArray, partNumbersWithCoordinates);
                }
            }
        }

        for (Integer partNumber : partNumbersWithCoordinates.values()) {
            sum += partNumber;
        }
        System.out.println("Day 3 part 1 solution: " + sum);
    }

    private static void addAdjacentPartsToMap(int r, int c, char[][] layout, HashMap<String, Integer> parts) {
        var partDigits = new StringBuilder();
        int lastColumn = layout[0].length - 1;
        //scanning up
        if (r > 0) {
            if (Character.isDigit(layout[r-1][c])) {
                int col = c < 2 ? 0 : c - 2;
                int colStop = Math.min(col + 1, lastColumn);
                while (col < colStop) {
                    if (Character.isDigit(layout[r-1][col])) {
                        partDigits.append(layout[r-1][col]);
                    }
                    col++;
                }

            }
        }
    }

    private static boolean isEnginePart(char c) {
        //any character that is not alphanumerical,
        //is not a dot and is not a special character (all below ascii index < 33)
        return c != '.' &&
                (c > 32 && c < 48) ||
                (c > 57 && c < 65) ||
                (c > 90 && c < 97) ||
                (c > 122 && c < 127);
    }

    private static char[][] transformInputTo2dArray(List<String> input) {
        int rows = input.size();
        int columns = input.get(0).length();
        char[][] layout = new char[rows][columns];

        for (int r = 0; r < layout.length; r++) {
            for (int c = 0; c < columns; c++) {
                layout[r][c] = input.get(r).charAt(c);
            }
        }
        return layout;
    }
}
