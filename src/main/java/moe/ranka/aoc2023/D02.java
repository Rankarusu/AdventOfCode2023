package moe.ranka.aoc2023;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D02 extends Day {

    private static final int RED = 12;
    private static final int GREEN = 13;
    private static final int BLUE = 14;

    @Override
    public void part1() {
        var lines = this.readFile("02.txt");
        int result = 0;

        Pattern p = Pattern.compile("((((?<blue>\\d+) blue)|((?<red>\\d+) red)|((?<green>\\d+) green))([,;]? ?))+");
        int gameNumber = 1;
        for (String line : lines.split("\n")) {
            var pulls = line.split(";");
            int highestRed = 0;
            int highestGreen = 0;
            int highestBlue = 0;

            for (String pull : pulls) {
                int blue = 0;
                int red = 0;
                int green = 0;
                Matcher m = p.matcher(pull);
                while (m.find()) {
                    var redMatch = m.group("red");
                    var greenMatch = m.group("green");
                    var blueMatch = m.group("blue");
                    red = redMatch == null ? 0 : Integer.parseInt(redMatch);
                    green = greenMatch == null ? 0 : Integer.parseInt(greenMatch);
                    blue = blueMatch == null ? 0 : Integer.parseInt(blueMatch);

                }
                if (blue >= highestBlue) {
                    highestBlue = blue;
                }
                if (red >= highestRed) {
                    highestRed = red;
                }
                if (green >= highestGreen) {
                    highestGreen = green;
                }

            }

            if (highestBlue <= BLUE && highestRed <= RED && highestGreen <= GREEN) {
                result += gameNumber;
            }
            gameNumber++;

        }
        System.out.println(result);
    }

    @Override
    public void part2() {
        // TODO Auto-generated method stub

    }

}
