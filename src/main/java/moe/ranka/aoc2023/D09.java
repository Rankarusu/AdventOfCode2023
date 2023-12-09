package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D09 extends Day {

    @Override
    public void part1() {
        var lines = this.readFile("09.txt").split("\n");

        int result = 0;

        for (String line : lines) {
            List<Integer> nums = Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();

            List<List<Integer>> prediction = new ArrayList<>();
            prediction.add(nums);

            do {
                List<Integer> lastRow = prediction.getLast();
                List<Integer> newList = new ArrayList<>();

                for (int i = 1; i < lastRow.size(); i++) {
                    newList.add(lastRow.get(i) - lastRow.get(i - 1));
                }
                prediction.add(newList);

            } while (!prediction.getLast().stream().allMatch(a -> a == 0));

            System.out.println(prediction);

            int sum = 0;
            // no need to go through the 0 only row
            for (int i = prediction.size() - 2; i >= 0; i--) {
                sum += prediction.get(i).getLast();
            }
            result += sum;

        }
        System.out.println(result);
    }

    @Override
    public void part2() {
        // TODO Auto-generated method stub

    }

}
