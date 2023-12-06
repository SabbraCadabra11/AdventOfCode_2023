import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4 {

    //Card   1: 24 12 26 39 19 98 74 16 82 77 | 80 11 51  1 74 60 77 68 42 35 39 78 21 12 29 19 25 98 65 91 33 17 59 24 31
    //Card  14:  8 59 91 73 10 61 65 34 29 81 |  9 32 87 78 26 16 90 49 74 61 56 11 57 93 77 62 75 46 36 59 85  3 19 34 28
    public static void solution() {
        var cards = Main.getInput("resources/day4_input.txt");
        int sum = 0;
        for (String card : cards) {
            var partsWithLabel = card.split(":");
            sum += calculateCardWorth(partsWithLabel[1]);
        }
        System.out.println("Day 4 part 1 solution: " + sum);
    }

    private static int calculateCardWorth(String cardNumbers) {
        var parts = cardNumbers.split("\\|");
        Set<Integer> winningNumbers = getNumberSet(parts[0].trim());
        List<Integer> myNumbers = getNumberList(parts[1].trim());

        int matches = 0;
        for (Integer num : myNumbers) {
            if (winningNumbers.contains(num)) {
                matches++;
            }
        }

        return matches == 0 ? 0 :
                (int) Math.pow(2, (matches - 1));
    }


    private static List<Integer> getNumberList(String numbers) {
        var numberList = new ArrayList<Integer>();
        for (String numberStr : numbers.trim().split(" ")) {
            if (numberStr.equals("")) {
                continue;
            }
            int num = Integer.parseInt(numberStr.trim());
            numberList.add(num);
        }
        return numberList;
    }

    private static Set<Integer> getNumberSet(String numbers) {
        var numberSet = new HashSet<Integer>();
        for (String numberStr : numbers.trim().split(" ")) {
            if (numberStr.equals("")) {
                continue;
            }
            int num = Integer.parseInt(numberStr.trim());
            numberSet.add(num);
        }
        return numberSet;
    }

}
