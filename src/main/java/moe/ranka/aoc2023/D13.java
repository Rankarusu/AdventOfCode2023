package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D13 extends Day {

    @Override
    public void part1() {
        var patterns = this.readFile("13.txt").split("\n\n");
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        for (String pattern : patterns) {
            var lines = pattern.split("\n");

            int horizontal = search(lines);
            int vertical = -1;
            if (horizontal == -1) {
                var transposed = transpose(lines);
                vertical = search(transposed);
                if (vertical != -1) {
                    cols.add(vertical);
                }
            } else if (horizontal != -1) {
                rows.add(horizontal);
            }
            System.out.println(horizontal);
            System.out.println(vertical);

        }

        System.out.println(cols.stream().mapToInt(x -> x).sum() + (100 * rows.stream().mapToInt(x -> x).sum()));
    }

    private int search(String[] input) {
        List<Integer> mirrorIndices = findMirrorIndices(input);

        for (Integer mirrorIndex : mirrorIndices) {

            int j = mirrorIndex + 1;

            if (mirrorIndex == 1) {
                return 1;
            }

            if (mirrorIndex == input.length - 1) {
                return input.length - 1;
            }

            for (int i = mirrorIndex - 2; i >= 0; i--) {

                if (j >= input.length || i < 0) {
                    break;
                }

                if ((i == 0 || j == input.length - 1) && input[i].equals(input[j])) {
                    return mirrorIndex;
                }
                if (!input[i].equals(input[j])) {
                    break;
                }
                j++;
            }
        }
        return -1;
    }

    private List<Integer> findMirrorIndices(String[] lines) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            if (lines[i].equals(lines[i - 1])) {
                result.add(i);
            }
        }
        return result;
    }

    private String[] transpose(String[] input) {
        int m = input.length;
        int n = input[0].length();
        char[][] transposed = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed[j][i] = input[i].charAt(j);
            }
        }

        return Arrays.stream(transposed).map(String::new).toArray(String[]::new);
    }

    @Override
    public void part2() {
        // TODO Auto-generated method stub

    }

}
