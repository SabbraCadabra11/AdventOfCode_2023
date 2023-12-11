import java.util.*;

public class Day7 {
    public static void solution() {
        var input = Main.getInput("resources/day7_input.txt");
        part1(input);
    }

    private static void part1(List<String> input) {
        var allHands = new ArrayList<Hand>();
        for (String line : input) {
            var parts = line.split(" ");
            allHands.add(new Hand(parts[0], Integer.parseInt(parts[1])));
        }
        Collections.sort(allHands);
        long totalWinnings = 0;
        for (int i = 0; i < allHands.size(); i++) {
            totalWinnings += (long) allHands.get(i).bid * (i + 1);
        }
        System.out.println("Day 7 part 1 solution: " + totalWinnings);
    }

    private static class Hand implements Comparable<Hand> {
        final Map<Character, Integer> distinctCards;
        final int bid;
        long score;

        Hand(String cards, int bid) {
            this.bid = bid;
            distinctCards = new HashMap<>();
            score = 0;
            for (int i = 0; i < cards.length(); i++) {
                char card = cards.charAt(i);
                score += (long) (getCardValue(card) * Math.pow(10, 8 - (2*i)));
                if (distinctCards.containsKey(card)) {
                    distinctCards.put(card, distinctCards.get(card) + 1);
                } else {
                    distinctCards.put(card, 1);
                }
            }
            calculateFinalHandScore();
        }

        void calculateFinalHandScore() {
            switch (distinctCards.size()) {
                //five of a kind
                case 1 -> score += 90_000_000_000L;
                //four of a kind and full house
                case 2 -> {
                    if (distinctCards.containsValue(4)) {
                        score += 80_000_000_000L;
                    } else {
                        score += 70_000_000_000L;
                    }
                }
                //three of a kind and two pair
                case 3 -> {
                    if (distinctCards.containsValue(3)) {
                        score += 60_000_000_000L;
                    } else {
                        score += 50_000_000_000L;
                    }
                }
                //pair
                case 4 -> score += 40_000_000_000L;
            }
        }

        private int getCardValue(char card) {
            return switch (card) {
                case 'A' -> 14;
                case 'K' -> 13;
                case 'Q' -> 12;
                case 'J' -> 11;
                case 'T' -> 10;
                default -> card - 48;
            };
        }

        @Override
        public int compareTo(Hand otherHand) {
            return Long.compare(this.score, otherHand.score);
        }

        @Override
        public String toString() {
            var sb = new StringBuilder();
            distinctCards.keySet().forEach(sb::append);
            return sb + " " + score;
        }
    }
}
