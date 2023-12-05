package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class D04 extends Day {

    @Override
    public void part1() {
        var lines = this.readFile("04.txt");
        Pattern p = Pattern.compile("\\d+");
        int points = 0;

        for (String line : lines.split("\n")) {
            line = line.replaceAll("^Card\\s+\\d+:", "");
            var splitLine = line.split("\\|");
            var winningNumbersStr = splitLine[0];
            List<Integer> winningNumbers = new ArrayList<>();
            List<Integer> ownNumbers = new ArrayList<>();
            Matcher m = p.matcher(winningNumbersStr);
            while (m.find()) {
                winningNumbers.add(Integer.parseInt(m.group()));
            }
            var ownNumbersStr = splitLine[1];

            m = p.matcher(ownNumbersStr);
            while (m.find()) {
                ownNumbers.add(Integer.parseInt(m.group()));
            }

            Set<Integer> s1 = new HashSet<>(winningNumbers);
            Set<Integer> s2 = new HashSet<>(ownNumbers);

            s2.retainAll(s1);

            points += getPoints(s2.size());

        }
        System.out.println(points);
    }

    private int getPoints(int size) {
        if (size == 0) {
            return 0;
        } else if (size == 1) {
            return 1;
        } else {
            return (int) Math.pow(2, size - 1);
        }
    }

    @Override
    public void part2() {
        var lines = this.readFile("04.txt");
        Pattern p = Pattern.compile("\\d+");
        Map<Integer, Integer> scratchCards = new HashMap<>();
        for (int i = 1; i <= lines.split("\n").length; i++) {
            scratchCards.put(i, 1);
        }

        int currentCard = 1;
        for (String line : lines.split("\n")) {
            line = line.replaceAll("^Card\\s+\\d+:", "");
            var splitLine = line.split("\\|");
            var winningNumbersStr = splitLine[0];
            List<Integer> winningNumbers = new ArrayList<>();
            List<Integer> ownNumbers = new ArrayList<>();
            var m = p.matcher(winningNumbersStr);
            while (m.find()) {
                winningNumbers.add(Integer.parseInt(m.group()));
            }
            var ownNumbersStr = splitLine[1];

            m = p.matcher(ownNumbersStr);
            while (m.find()) {
                ownNumbers.add(Integer.parseInt(m.group()));
            }

            Set<Integer> s1 = new HashSet<>(winningNumbers);
            Set<Integer> s2 = new HashSet<>(ownNumbers);

            s2.retainAll(s1);

            int currentCards = scratchCards.get(currentCard);
            for (int i = currentCard + 1; i < currentCard + 1 + s2.size(); i++) {
                scratchCards.put(i, scratchCards.get(i) + currentCards);
            }

            currentCard++;
        }
        System.out.println(scratchCards.values().stream().mapToInt(x -> x).sum());
    }

}
