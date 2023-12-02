import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    public static void solution() {
        List<String> inputStrings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/day1_input.txt"))) {
            reader.lines().forEach(inputStrings::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        part1(inputStrings);
        part2(inputStrings);
    }
    /*
    --- Part Two ---
    Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters:
    one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".

    Equipped with this new information, you now need to find the real first and last digit on each line. For example:

    two1nine
    eightwothree
    abcone2threexyz
    xtwone3four
    4nineeightseven2
    zoneight234
    7pqrstsixteen
    In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.

    What is the sum of all of the calibration values?
    */
    private static void part2(List<String> inputStrings) {

        AtomicInteger sum = new AtomicInteger();
        inputStrings.forEach(line -> {
            sum.getAndAdd(findDigitFromLeft(line) * 10);
            sum.getAndAdd(findDigitFromRight(line));
        });
        System.out.println("Solution for day 1 part 2: " + sum.get());
    }

    private static int findDigitFromLeft(String input) {
        Map<String, Integer> digitDictionary = new HashMap<>();
        digitDictionary.put("zero", 0);
        digitDictionary.put("one", 1);
        digitDictionary.put("two", 2);
        digitDictionary.put("three", 3);
        digitDictionary.put("four", 4);
        digitDictionary.put("five", 5);
        digitDictionary.put("six", 6);
        digitDictionary.put("seven", 7);
        digitDictionary.put("eight", 8);
        digitDictionary.put("nine", 9);

        String patternString = String.join("|", digitDictionary.keySet()) + "|\\d";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group();
            if (digitDictionary.containsKey(match)) {
                return digitDictionary.get(match);
            } else if (match.matches("\\d")) {
                return Integer.parseInt(match);
            }
        }
        return -1;
    }

    private static int findDigitFromRight(String input) {
        int i = input.length() - 1;
        while (i >= 0) {
            if (Character.isDigit(input.charAt(i))) {
                return Integer.parseInt(String.valueOf(input.charAt(i)));
            }
            for (int x = 0; x < 10; x++) {
                if (input.substring(i).contains(digitToWord(x))) {
                    return x;
                }
            }
            i--;
        }
        return -1;
    }

    private static String digitToWord(int digit) {
        return switch (digit) {
            case 0 -> "zero";
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            case 9 -> "nine";
            default -> "";
        };
    }

    /*
    --- Part One ---
    As they're making the final adjustments, they discover that their calibration document (your puzzle input)
    has been amended by a very young Elf who was apparently just excited to show off her art skills.
    Consequently, the Elves are having trouble reading the values on the document.

    The newly-improved calibration document consists of lines of text;
    each line originally contained a specific calibration value that the Elves now need to recover.
    On each line, the calibration value can be found by combining the first digit and the last digit (in that order)
    to form a single two-digit number.

    In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.

    Consider your entire calibration document. What is the sum of all of the calibration values?
    */
    private static void part1(List<String> inputStrings) {
        AtomicInteger sum = new AtomicInteger();
        inputStrings.forEach(line -> {
            sum.addAndGet(getLeftmostDigit(line) * 10);
            sum.getAndAdd(getRightmostDigit(line));
        });
        System.out.println("Solution for day 1: " + sum.get());
    }

    private static int getLeftmostDigit(String line) {
        byte i = 0;
        while (i < line.length()) {
            if (Character.isDigit(line.charAt(i))) {
                return Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            i++;
        }
        return -1;
    }

    private static int getRightmostDigit(String line) {
        byte i = (byte) (line.length() - 1);
        while (i >= 0) {
            if (Character.isDigit(line.charAt(i))) {
                return Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            i--;
        }
        return -1;
    }
}













