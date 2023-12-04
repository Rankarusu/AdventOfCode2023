package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D03 extends Day {

    @Override
    public void part1() {
        var lines = this.readFile("03.txt").split("\n");
        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);

        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            StringBuilder currentNumberBuffer = new StringBuilder();
            for (int j = 0; j < map[0].length; j++) {
                char symbol = map[i][j];

                if (Character.isDigit(symbol)) {
                    currentNumberBuffer.append(symbol);
                } else if (currentNumberBuffer.length() > 0) {
                    if (isAdjacentToSymbol(map, currentNumberBuffer.length(), i, j)) {
                        nums.add(Integer.parseInt(currentNumberBuffer.toString()));
                    }
                    currentNumberBuffer.setLength(0);
                }

                if (j == map[0].length -1 && currentNumberBuffer.length() > 0
                        && isAdjacentToSymbol(map, currentNumberBuffer.length(), i, j)) {
                    nums.add(Integer.parseInt(currentNumberBuffer.toString()));
                }
            }
        }
        System.out.println(nums.stream().mapToInt(Integer::intValue).sum());
    }

    private boolean isAdjacentToSymbol(char[][] map, int currentBufferLength, int i, int j) {
        for (int k = Math.max(i - 1, 0); k <= Math.min(i + 1, map.length - 1); k++) {
            for (int l = Math.max(0, j - currentBufferLength - 1); l <= Math.min(j, map[0].length - 1); l++) {
                char current = map[k][l];
                if (current != '.' && !Character.isDigit(current)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void part2() {
        // TODO Auto-generated method stub
    }

}
