package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.List;

public class D15 extends Day {

    @Override
    public void part1() {
        var line = this.readFile("15.txt");
        var instructions = line.split(",");
        List<Integer> result = new ArrayList<>();
        // Determine the ASCII code for the current character of the string.
        // Increase the current value by the ASCII code you just determined.
        // Set the current value to itself multiplied by 17.
        // Set the current value to the remainder of dividing itself by 256.
        for (String instruction : instructions) {
            int instructionValue = 0;
            for (int i = 0; i < instruction.length(); i++) {
                var current = (int) instruction.charAt(i);
                instructionValue += current;
                instructionValue *= 17;
                instructionValue %= 256;
            }
            result.add(instructionValue);
        }

        System.out.println(result.stream().mapToInt(Integer::valueOf).sum());
    }

    @Override
    public void part2() {
        // TODO Auto-generated method stub

    }

}
