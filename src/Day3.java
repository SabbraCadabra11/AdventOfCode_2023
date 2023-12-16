
public class Day3 {
    public static void solution() {
        var engineLayoutArray = Main.transformInputTo2dArray(Main.getInput("resources/day10_input.txt"));
        part1(engineLayoutArray);
        //part2(engineLayoutArray);
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

//    private static void part2(char[][] engineLayoutArray) {
//        int sum = 0;
//        for (int r = 0; r < engineLayoutArray.length; r++) {
//            for (int c = 0; c < engineLayoutArray[r].length; c++) {
//                if (engineLayoutArray[r][c] == '*') {
//                    sum += calculateGearRatio(engineLayoutArray, r, c);
//                }
//            }
//        }
//
//    }

    private static int calculateGearRatio(char[][] layout, int r, int c) {
        int adjacentNumbers = 0;
        int ratio = 1;
        final var digits = new StringBuilder();
        //check to the left
        if (c > 0 && Character.isDigit(layout[r][c-1])) {
            int col = c;
            while (col >= 0 && Character.isDigit(layout[r][col])) {
                digits.append(layout[r][col]);
                col--;
            }
            ratio *= Integer.parseInt(digits.reverse().toString());
            adjacentNumbers++;
        }
        //check to the right
        digits.setLength(0);
        if (c < layout[r].length - 1 && Character.isDigit(layout[r][c+1])) {
            int col = c;
            while (col < layout[r].length - 1 && Character.isDigit(layout[r][col])) {
                digits.append(layout[r][col]);
                col++;
            }
            ratio *= Integer.parseInt(digits.toString());
            adjacentNumbers++;
            if (adjacentNumbers == 2) {
                return ratio;
            }
        }
        //check up
        digits.setLength(0);
        if (r > 0) {
            if (c == 0) {
                if (Character.isDigit(layout[r-1][c]) || Character.isDigit(layout[r-1][c+1])) {
                    for (int col = c; col <= 3; col++) {
                        digits.append(layout[r][col]);
                    }
                    ratio *= Integer.parseInt(digits.toString().replaceAll(".", ""));
                    adjacentNumbers++;
                    if (adjacentNumbers == 2) {
                        return ratio;
                    }
                }
            }
        }



        return adjacentNumbers > 1 ? ratio : 0;
    }


    private static boolean isPartNumber(char[][] layout, int r, int c, int num) {
        //bounds are inclusive
        final int leftBound = c == 0 ? c : c - 1;
        final int rightBound = Math.min(layout[0].length - 1, leftBound + digitsCount(num) + 1);
        //check left
        if (c > 0 && isEnginePartSymbol(layout[r][leftBound])) {
            return true;
        }
        //check right
        if (!Character.isDigit(layout[r][rightBound]) && isEnginePartSymbol(layout[r][rightBound])) {
            return true;
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
}