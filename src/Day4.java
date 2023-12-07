import java.util.*;

public class Day4 {

    public static void solution() {
        var lines = Main.getInput("resources/day4_input.txt");
        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            var card = new Card(line);
            sum += card.calculateCardWorth();
        }
        System.out.println("Day 4 part 1 solution: " + sum);
    }

    private static void part2(List<String> lines) {
        var allCards = new LinkedHashMap<Integer, List<Card>>();
        int amountOfAllCards = 0, lastCardId = 0;

        for (String line : lines) {
            var card = new Card(line);
            var listOfCards = new ArrayList<Card>();
            listOfCards.add(card);
            allCards.put(card.id, listOfCards);
            lastCardId = card.id;
            amountOfAllCards++;
        }

        for (Map.Entry<Integer, List<Card>> entry : allCards.entrySet()) {
            var cards = entry.getValue();
            for (Card card : cards) {
                if (card.id == lastCardId) {
                    break;
                }
                int matches = card.getAmountOfMatchingNumbers();
                int endId = Math.min(card.id + matches, lastCardId);
                for (int nextCardId = card.id + 1; nextCardId <= endId; nextCardId++) {
                    var listOfCards = allCards.get(nextCardId);
                    listOfCards.add(listOfCards.get(0).clone());
                    amountOfAllCards++;
                }
            }
        }
        System.out.println("Day 4 part 1 solution: " + amountOfAllCards);
    }

    private static List<Integer> getNumberList(String numbers) {
        var numberList = new ArrayList<Integer>();
        for (String numberStr : numbers.trim().split(" ")) {
            if (numberStr.isEmpty()) {
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
            if (numberStr.isEmpty()) {
                continue;
            }
            int num = Integer.parseInt(numberStr.trim());
            numberSet.add(num);
        }
        return numberSet;
    }

    private static class Card implements Cloneable {
        //Card   1: 24 12 26 39 19 98 74 16 82 77 | 80 11 51  1 74 60 77 68 42 35 39 78 21 12 29 19 25 98 65 91 33 17 59 24 31
        //Card  14:  8 59 91 73 10 61 65 34 29 81 |  9 32 87 78 26 16 90 49 74 61 56 11 57 93 77 62 75 46 36 59 85  3 19 34 28
        final int id;
        final Set<Integer> winningNumbers;
        final List<Integer> myNumbers;

        Card(String line) {
            var sanitizedLine = line.replaceAll("Card ", "").trim();
            var parts = sanitizedLine.split(" \\| ");
            id = Integer.parseInt(parts[0].substring(0, parts[0].indexOf(':')));
            winningNumbers = getNumberSet(parts[0].substring(parts[0].indexOf(':') + 1).trim());
            myNumbers = getNumberList(parts[1].trim());
        }

        int calculateCardWorth() {
            int matches = 0;
            for (Integer num : myNumbers) {
                if (winningNumbers.contains(num)) {
                    matches++;
                }
            }
            return matches == 0 ? 0 : (int) Math.pow(2, (matches - 1));
        }

        int getAmountOfMatchingNumbers() {
            int matches = 0;
            for (Integer num : myNumbers) {
                if (winningNumbers.contains(num)) {
                    matches++;
                }
            }
            return matches;
        }

        @Override
        public Card clone() {
            try {
                return (Card) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
