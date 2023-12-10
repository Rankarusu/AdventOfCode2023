package moe.ranka.aoc2023;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
import java.util.Queue;

public class D10 extends Day {

    private Map<Character, int[]> offsetsMap = Map.of('n', new int[] { 0, -1 }, 's', new int[] { 0, 1 }, 'w',
            new int[] { -1, 0 }, 'e', new int[] { 1, 0 });

    private Map<Character, char[]> cardinalsMap = Map.of('|', new char[] { 'n', 's' }, '-', new char[] { 'e', 'w' },
            'L', new char[] { 'n', 'e' }, 'J', new char[] { 'n', 'w' }, '7', new char[] { 's', 'w' }, 'F',
            new char[] { 's', 'e' }, '.', new char[] {}, 'S', new char[] { 'n', 's', 'e', 'w' });

    private char getOppositeCardinal(char cardinal) {
        return switch (cardinal) {
        case 'n' -> 's';
        case 's' -> 'n';
        case 'e' -> 'w';
        case 'w' -> 'e';
        default -> '0';
        };
    }

    private boolean isPartOfPipe(char currentCardinal, char nextSymbol) {
        var nextSymbolCardinals = cardinalsMap.get(nextSymbol);
        var oppositeCardinal = this.getOppositeCardinal(currentCardinal);

        for (int i = 0; i < nextSymbolCardinals.length; i++) {
            if (nextSymbolCardinals[i] == oppositeCardinal) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void part1() {
        var lines = this.readFile("10.txt").split("\n");
        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
        boolean[][] visited = new boolean[map.length][map[0].length];

        int startX = 0;
        int startY = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[j][i] == 'S') {
                    startX = j;
                    startY = i;
                    break;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { startX, startY });
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            var current = queue.poll();
            int x = current[0];
            int y = current[1];
            char symbol = map[x][y];

            char[] cardinals = this.cardinalsMap.get(symbol);

            for (char c : cardinals) {
                int[] offset = offsetsMap.get(c);
                int nextX = x + offset[1];
                int nextY = y + offset[0];

                if (nextY >= map.length || nextY < 0 || nextX >= map[0].length || nextX < 0) {
                    // stay in bounds
                    continue;
                }

                var nextSymbol = map[nextX][nextY];
                if (!isPartOfPipe(c, nextSymbol) || visited[nextY][nextX]) {
                    continue;
                }

                queue.offer(new int[] { nextX, nextY });
                visited[nextY][nextX] = true;
            }

        }

        int result = 0;

        for (int i = 0; i < visited.length; i++) {
            System.out.println();
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j]) {
                    result++;
                }
                System.out.print(visited[j][i] ? '#' : "O");
            }
        }
        System.out.println();
        System.out.println(result / 2);

    }

    @Override
    public void part2() {
        var lines = this.readFile("10.txt").split("\n");
        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
        char[][] visited = new char[map.length][map[0].length];

        int startX = 0;
        int startY = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'S') {
                    startX = j;
                    startY = i;

                    map[i][j] = '-';
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { startX, startY });
        visited[startY][startX] = '#';

        while (!queue.isEmpty()) {
            var current = queue.poll();
            int x = current[0];
            int y = current[1];
            char symbol = map[y][x];

            char[] cardinals = this.cardinalsMap.get(symbol);

            for (char c : cardinals) {
                int[] offset = offsetsMap.get(c);
                int nextX = x + offset[0];
                int nextY = y + offset[1];

                if (nextY >= map.length || nextY < 0 || nextX >= map[0].length || nextX < 0) {
                    // stay in bounds
                    continue;
                }

                var nextSymbol = map[nextY][nextX];
                if (!isPartOfPipe(c, nextSymbol) || visited[nextY][nextX] == '#') {
                    continue;
                }

                queue.offer(new int[] { nextX, nextY });
                visited[nextY][nextX] = '#';
            }

        }

        int pointsInside = 0;

        for (int i = 0; i < map.length; i++) {
            System.out.println(map[i]);
            for (int j = 0; j < map[i].length; j++) {
                System.out.println(map[i][j]);
                int count = 0;

                if (visited[i][j] == '#') {
                    continue;
                }

                for (int k = j + 1; k < map[i].length; k++) {
                    char c = map[i][k];
                    System.out.println("checking " + c);
                    System.out.println(visited[i][k] == '#');
                    if (visited[i][k] == '#' && (c == '|' || c == 'L' || c == 'J')) {
                        System.out.println("found " + c + " incrementing..");
                        count++;
                    }

                }
                if (count != 0 && count % 2 == 1) {
                    System.out.println("adding point, count " + count);
                    pointsInside++;
                    visited[i][j] = 'I';
                }
            }
        }
        System.out.println(pointsInside);

        for (int i = 0; i < visited.length; i++) {
            System.out.println();
            for (int j = 0; j < visited[i].length; j++) {

                System.out.print(visited[i][j] == '\u0000' ? 'O' : visited[i][j]);
            }
        }

    }

}
