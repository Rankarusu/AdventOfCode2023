package moe.ranka.aoc2023;

import java.util.Arrays;

public class D06 extends Day {

    @Override
    public void part1() {
        var lines = this.readFile("06.txt").split("\n");
        int[] times = Arrays.stream(lines[0].split(":")[1].trim().split("\s+")).mapToInt(Integer::parseInt).toArray();
        int[] distances = Arrays.stream(lines[1].split(":")[1].trim().split("\s+")).mapToInt(Integer::parseInt)
                .toArray();
        int result = 1;

        for (int i = 0; i < distances.length; i++) {
            int time = times[i];
            int distance = distances[i];

            int wins = 0;
            for (int j = 0; j <= time; j++) {
                if (j * (time - j) > distance) {
                    wins++;
                }
            }
            result *= wins;
        }
        System.out.println(result);
    }

    @Override
    public void part2() {
        var lines = this.readFile("06.txt").split("\n");
        long time = Long.parseLong(lines[0].split(":")[1].trim().replaceAll("\s+", ""));
        long distance = Long.parseLong(lines[1].split(":")[1].trim().replaceAll("\s+", ""));

            int wins = 0;
            for (long j = 0; j <= time; j++) {
                if (j * (time - j) > distance) {
                    wins++;
                }
            }
        System.out.println(wins);
    }
}
