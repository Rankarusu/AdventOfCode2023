package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.List;

public class D01 extends Day {

    @Override
    public void part1() {
        var input = this.readFile("01.txt");
        List<Integer> nums = new ArrayList<>();
        var lines = input.split("\n");
        for (String line : lines) {

            var charArray = line.toCharArray();
            char first = '0';
            char last = '0';

            for (int i = 0; i < charArray.length; i++) {
                if (Character.isDigit(line.charAt(i))) {
                    first = line.charAt(i);
                    break;
                }
            }

            for (int j = charArray.length - 1; j >= 0; j--) {
                if (Character.isDigit(line.charAt(j))) {
                    last = line.charAt(j);
                    break;
                }
            }

            var combinedNumber = Integer.parseInt(Character.toString(first) + Character.toString(last));
            nums.add(combinedNumber);
        }
        var result = nums.stream().mapToInt(Integer::intValue).sum();
        System.out.println(result);
    }

    @Override
    public void part2() {
        var input = this.readFile("01.txt");
        List<Integer> nums = new ArrayList<>();
        var lines = input.split("\n");
        for (String line : lines) {

            line = line.replaceAll("one", "o1e").replaceAll("two", "t2o").replaceAll("three", "t3e")
                    .replaceAll("four", "f4r").replaceAll("five", "f5e").replaceAll("six", "s6x")
                    .replaceAll("seven", "s7n").replaceAll("eight", "e8t").replaceAll("nine", "n9e");

            var charArray = line.toCharArray();
            char first = '0';
            char last = '0';

            for (int i = 0; i < charArray.length; i++) {
                if (Character.isDigit(line.charAt(i))) {
                    first = line.charAt(i);
                    break;
                }
            }

            for (int j = charArray.length - 1; j >= 0; j--) {
                if (Character.isDigit(line.charAt(j))) {
                    last = line.charAt(j);
                    break;
                }
            }

            var combinedNumber = Integer.parseInt(Character.toString(first) + Character.toString(last));
            nums.add(combinedNumber);
        }
        var result = nums.stream().mapToInt(Integer::intValue).sum();
        System.out.println(result);
    }

}
