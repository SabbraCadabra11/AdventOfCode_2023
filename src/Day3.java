import java.util.List;

public class Day3 {
    public static void solution() {
        final var input = Main.getInput("resources/day3_input.txt");
        final var engineLayoutArray = transformInputTo2dArray(input);
        part1(engineLayoutArray);
    }

    private static void part1(char[][] engineLayoutArray) {
        int sum = 0;

        for (int r = 0; r < engineLayoutArray.length; r++) {
            for (int c = 0; c < engineLayoutArray[r].length; c++) {
                if (Character.isDigit(engineLayoutArray[r][c])) {
                    int num = parseEnginePartNumber(engineLayoutArray, r, c);
                    if (isPartNumber(engineLayoutArray, r, c, num)) {
                        c += digitsCount(num);
                        sum += num;
                    }
                }
            }
        }

        System.out.println("Day 3 part 1 solution: " + sum);
    }

    private static boolean isPartNumber(char[][] layout, int r, int c, int num) {
        //bounds are inclusive
        final int leftBound = c == 0 ? c : c - 1;
        final int rightBound = Math.min(layout[0].length - 1, leftBound + digitsCount(num) + 1);
        //check left
        if (c > 0) {
            if (isEnginePartSymbol(layout[r][leftBound])) {
                return true;
            }
        }
        //check right
        //...510
        if (!Character.isDigit(layout[r][rightBound])) {
            if (isEnginePartSymbol(layout[r][rightBound])) {
                return true;
            }
        }
        //check up
        if (r > 0) {
           for (int x = leftBound; x <= rightBound; x++) {
               if (isEnginePartSymbol(layout[r-1][x])) {
                   return true;
               }
           }
        }
        //check down
        if (r < layout.length - 1) {
            for (int x = leftBound; x <= rightBound; x++) {
                if (isEnginePartSymbol(layout[r+1][x])) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int digitsCount(int num) {
        return String.valueOf(num).length();
    }


    private static int parseEnginePartNumber(char[][] layout, int r, int c) {
        final var digits = new StringBuilder();
        int col = c;
        while (Character.isDigit(layout[r][col])) {
            digits.append(layout[r][col]);
            col++;
            if (col == layout[r].length) {
                break;
            }
        }
        return Integer.parseInt(digits.toString());
    }

    private static boolean isEnginePartSymbol(char c) {
        //any character that is not alphanumerical,
        //is not a dot and is not a special character (all below ascii index < 33)
        return c != '.' &&
                (c > 32 && c < 48) ||
                (c > 57 && c < 65) ||
                (c > 90 && c < 97) ||
                (c > 122 && c < 127);
    }

    private static char[][] transformInputTo2dArray(List<String> input) {
        final int rows = input.size();
        final int columns = input.get(0).length();
        char[][] layout = new char[rows][columns];

        for (int r = 0; r < layout.length; r++) {
            for (int c = 0; c < columns; c++) {
                layout[r][c] = input.get(r).charAt(c);
            }
        }
        return layout;
    }
}
