package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class D11 extends Day {

    @Override
    public void part1() {
        var lines = this.readFile("11.txt").split("\n");
        List<String> expandedVertically = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            Set<Character> charsSet = lines[i].chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            if (charsSet.size() == 1 && charsSet.contains('.')) {
                expandedVertically.add(lines[i]);
            }
            expandedVertically.add(lines[i]);
        }

        List<Integer> indices = new ArrayList<>();

        List<String> expandedHorizontally = new ArrayList<>();
        for (int i = 0; i < lines[0].length(); i++) {
            int hashes = 0;
            for (int j = 0; j < lines.length; j++) {
                char currentChar = lines[j].charAt(i);
                if (currentChar == '#') {
                    hashes++;
                }
            }
            if (hashes == 0) {
                indices.add(i);
            }
        }

        indices.addFirst(0);
        for (String line : expandedVertically) {
            List<String> parts = new ArrayList<>();
            for (int i = 1; i < indices.size(); i++) {
                parts.add(line.substring(indices.get(i - 1), indices.get(i)));
                if (i == indices.size() - 1) {
                    parts.add(line.substring(indices.get(i), line.length()));
                }
            }
            expandedHorizontally.add(String.join(".", parts));
        }

        char[][] map = expandedHorizontally.stream().map(String::toCharArray).toArray(char[][]::new);

        List<int[]> galaxies = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char curent = map[i][j];
                if (curent == '#') {
                    galaxies.add(new int[] { i, j });
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < galaxies.size(); i++) {
            int[] current = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                int[] next = galaxies.get(j);
                int xdist = Math.abs(current[0] - next[0]);
                int ydist = Math.abs(current[1] - next[1]);
                result.add((xdist + ydist));
            }

        }
        System.out.println(result.stream().mapToInt(x -> x).sum());
    }

    @Override
    public void part2() {
        var lines = this.readFile("11.txt").split("\n");

        List<Integer> expandedRows = new ArrayList<>();
        List<Integer> expandedCols = new ArrayList<>();

        int multiplier = 999_999;

        for (int i = 0; i < lines.length; i++) {
            Set<Character> charsSet = lines[i].chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            if (charsSet.size() == 1 && charsSet.contains('.')) {
                expandedRows.add(i);
            }
        }

        for (int i = 0; i < lines[0].length(); i++) {
            int hashes = 0;
            for (int j = 0; j < lines.length; j++) {
                char currentChar = lines[j].charAt(i);
                if (currentChar == '#') {
                    hashes++;
                }
            }
            if (hashes == 0) {
                expandedCols.add(i);
            }
        }

        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);

        List<int[]> galaxies = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char current = map[i][j];
                if (current == '#') {
                    galaxies.add(new int[] { i, j });
                }
            }
        }

        List<Long> result = new ArrayList<>();

        for (int i = 0; i < galaxies.size(); i++) {
            int[] current = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                int[] next = galaxies.get(j);

                long ydist = Math.abs(current[0] - next[0]);

                for (long row : expandedRows) {
                    if (Math.min(current[0], next[0]) < row && row < Math.max(current[0], next[0])){
                        ydist+=multiplier;
                    }
                }

                long xdist = Math.abs(current[1] - next[1]);

                for (long col : expandedCols) {
                    if (Math.min(current[1], next[1]) < col && col < Math.max(current[1], next[1])){
                        xdist+=multiplier;
                    }
                }

                result.add(xdist + ydist);
            }

        }
         System.out.println(result.stream().mapToLong(x -> x).sum());
    }

}
