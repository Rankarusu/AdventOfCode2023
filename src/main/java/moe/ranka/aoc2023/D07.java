package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D07 extends Day {

    enum HandType {
        FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
    }

    class Hand implements Comparable<Hand> {
        private static List<Character> cardValues = Arrays.asList('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4',
                '3', '2');
        private Map<Character, Integer> cards = new HashMap<>();

        private final HandType type;

        private String input;
        public int bid;

        public HandType getType() {
            return type;
        }

        public Hand(String input, int bid) {
            this.input = input;
            this.bid = bid;
            for (char card : input.toCharArray()) {
                System.out.println(card);
                Integer currentValue = cards.computeIfAbsent(card, s -> 0);
         
                cards.put(card, currentValue + 1);
            }
            this.type = calculateType();
        }

        private HandType calculateType() {
            List<Integer> list = new ArrayList<>(cards.values()); 
            list.sort(Collections.reverseOrder());

            if (list.size() == 1) {
                return HandType.FIVE_OF_A_KIND;
            } else if (list.get(0) == 4) {
                return HandType.FOUR_OF_A_KIND;
            } else if (list.get(0) == 3 && list.get(1) == 2) {
                return HandType.FULL_HOUSE;
            } else if (list.get(0) == 3 && list.get(1) == 1) {
                return HandType.THREE_OF_A_KIND;
            } else if (list.get(0) == 2 && list.get(1) == 2) {
                return HandType.TWO_PAIR;
            } else if (list.get(0) == 2 && list.get(1) == 1) {
                return HandType.ONE_PAIR;
            } else {
                return HandType.HIGH_CARD;
            }

        }

        @Override
        public int compareTo(Hand hand) {
            if (hand.getType() != this.getType()) {
                return hand.getType().ordinal() - this.getType().ordinal();
            }
            for (int i = 0; i < input.toCharArray().length; i++) {
                int handRank = Hand.cardValues.indexOf(hand.input.charAt(i));
                int ownRank = Hand.cardValues.indexOf(input.charAt(i));
                if (handRank != ownRank) {
                    return handRank - ownRank;
                }
            }

            return 0;
        }
    }

    @Override
    public void part1() {
        var lines = this.readFile("07.txt").split("\n");
        int result = 0;
        List<Hand> hands = new ArrayList<>();
        for (String line : lines) {
            String hand = line.split(" ")[0];
            int bid = Integer.parseInt(line.split(" ")[1]);

            hands.add(new Hand(hand, bid));

        }
        hands.sort(Comparable::compareTo);


        for (int i = 0; i < hands.size(); i++) {
           result += (i+1) * hands.get(i).bid;
        }
        System.out.println(result   );

    }

    @Override
    public void part2() {
        // TODO Auto-generated method stub

    }

}
