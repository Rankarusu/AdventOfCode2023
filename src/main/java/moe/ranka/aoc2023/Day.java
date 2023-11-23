package moe.ranka.aoc2023;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class Day {

  private static final String PATH = "src/main/resources/";

  public String readFile(String filename) {
    File file = new File(PATH + filename);

    List<String> fileContent = new ArrayList<>();
    try {
      fileContent = Files.readAllLines(file.toPath(), UTF_8);
    } catch (IOException e) {
      System.err.println("ya goofed");
    }

    return String.join("\n", fileContent);
  }

  public abstract void part1();

  public abstract void part2();

  public void run() {
    this.part1();
    this.part2();
  }

}
