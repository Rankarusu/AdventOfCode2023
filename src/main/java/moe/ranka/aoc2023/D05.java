package moe.ranka.aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class D05 extends Day {

    @Override
    public void part1() {
        var file = this.readFile("05.txt");
        var blocks = file.split("\n\n");
        var seeds = Arrays.stream(blocks[0].split(": ")[1].split(" ")).mapToLong(Long::parseLong).toArray();
        List<Converter> converters = new ArrayList<>();
        long lowestLocation = Long.MAX_VALUE;

        for (int i = 1; i < blocks.length; i++) {
            var ranges = blocks[i].split("\n");
            var converter = new Converter();
            for (int j = 1; j < ranges.length; j++) {
                long[] range = Arrays.stream(ranges[j].split(" ")).mapToLong(Long::parseLong).toArray();
                converter.ranges.add(range);
            }
            converters.add(converter);
        }

        for (long seed : seeds) {
            long current = seed;
            for (Converter converter : converters) {
                current = converter.convert(current);
            }
            if (current < lowestLocation) {
                lowestLocation = current;
            }

        }
        System.out.println(lowestLocation);
    }

    public class Converter {
        public List<long[]> ranges = new ArrayList<>();

        public long convert(long input) {
            for (long[] range : ranges) {
                if (input >= range[1] && input < range[1] + range[2]) {
                    return range[0] + (input - range[1]);
                }
            }
            return input;
        }
    }

    @Override
    public void part2() {
        var file = this.readFile("05.txt");
        var blocks = file.split("\n\n");
        var seeds = Arrays.stream(blocks[0].split(": ")[1].split(" ")).mapToLong(Long::parseLong).toArray();
        List<Converter> converters = new ArrayList<>();
        long lowestLocation = Long.MAX_VALUE;

        for (int i = 1; i < blocks.length; i++) {
            var ranges = blocks[i].split("\n");
            var converter = new Converter();
            for (int j = 1; j < ranges.length; j++) {
                long[] range = Arrays.stream(ranges[j].split(" ")).mapToLong(Long::parseLong).toArray();
                converter.ranges.add(range);
            }
            converters.add(converter);
        }

        for (int i = 0; i < seeds.length; i += 2) {
            long start = seeds[i];
            long end = seeds[i] + seeds[i + 1];
            
        for (long j = start; j < end; j++) {
            long current = j;
            for (Converter converter : converters) {
                current = converter.convert(current);
            }
                            if (current < lowestLocation) {
                    lowestLocation = current;
                }
            }
        }
System.out.println(lowestLocation);
    }

}
