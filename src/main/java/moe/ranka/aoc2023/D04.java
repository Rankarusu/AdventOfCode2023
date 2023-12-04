package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

            s1.retainAll(s2);

            points += getPoints(s1.size());

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
        // TODO Auto-generated method stub
    }

}
