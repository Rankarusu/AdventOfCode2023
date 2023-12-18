package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D15 extends Day {

    @Override
    public void part1() {
        var line = this.readFile("15.txt");
        var instructions = line.split(",");
        List<Integer> result = new ArrayList<>();
        for (String instruction : instructions) {

            result.add(hash(instruction));
        }

        System.out.println(result.stream().mapToInt(Integer::valueOf).sum());
    }

    private int hash(String instruction) {
        int instructionValue = 0;
        for (int i = 0; i < instruction.length(); i++) {
            var current = (int) instruction.charAt(i);
            instructionValue += current;
            instructionValue *= 17;
            instructionValue %= 256;
        }
        return instructionValue;

    }

    @Override
    public void part2() {
        var line = this.readFile("15.txt");

        Pattern p = Pattern.compile("(?<label>\\w+)(?<operation>[-=])(?<focalPower>\\d?+)");
        var instructions = line.split(",");

        Map<Integer, List<Lens>> map = new HashMap<>();

        List<Integer> result = new ArrayList<>();

        for (String instruction : instructions) {
            Matcher m = p.matcher(instruction);
            String label = "";
            String operation = "";
            String focalPower = "";
            while (m.find()) {
                label = m.group("label");
                operation = m.group("operation");
                focalPower = m.group("focalPower");
            }
            int boxNr = hash(label);
            List<Lens> box = map.computeIfAbsent(boxNr, s -> new ArrayList<Lens>());

            if (operation.equals("-")) {
                final String innerLabel = label;
                box.removeIf(lens -> lens.label().equals(innerLabel));
            } else {
                int lensIndex = findLens(box, label);
                if (lensIndex == -1) {
                    box.add(new Lens(label, Integer.parseInt(focalPower)));
                } else {
                    box.set(lensIndex, new Lens(label, Integer.parseInt(focalPower)));
                }
            }

        }

        for (Entry<Integer, List<Lens>> entry : map.entrySet()) {
            var key = entry.getKey();
            var values = entry.getValue();
            if (entry.getValue().isEmpty()) {
                continue;
            }

            for (int i = 0; i < values.size(); i++) {
                result.add((key + 1) * (i + 1) * values.get(i).focalPower);
            }

        }

        System.out.println(result.stream().mapToInt(Integer::valueOf).sum());
    }

    private int findLens(List<Lens> list, String label) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).label.equals(label)) {
                return i;
            }
        }
        return -1;
    }

    record Lens(String label, int focalPower) {
    }

}
