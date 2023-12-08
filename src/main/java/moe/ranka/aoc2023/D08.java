package moe.ranka.aoc2023;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D08 extends Day {

    @Override
    public void part1() {
        Pattern p = Pattern.compile("(?<name>\\w{3}) = \\((?<left>\\w{3}), (?<right>\\w{3})\\)");

        var lines = this.readFile("08.txt").split("\n");
        char[] instructions = lines[0].toCharArray();
        Map<String, Node> nodeMap = new HashMap<>();
        Node current = new Node("");

        for (int i = 2; i < lines.length; i++) {

            Matcher m = p.matcher(lines[i]);
            String name = "";
            String leftName = "";
            String rightName = "";

            while (m.find()) {
                name = m.group("name");
                leftName = m.group("left");
                rightName = m.group("right");
            }

            Node node = nodeMap.get(name);
            if (node == null) {
                node = new Node(name);
                nodeMap.put(name, node);
            } else {
                node.name = name;
            }

            Node leftNode = nodeMap.get(leftName);
            if (leftNode == null) {
                leftNode = new Node(leftName);
                nodeMap.put(leftName, leftNode);
            }
            node.setLeft(leftNode);

            Node rightNode = nodeMap.get(rightName);
            if (rightNode == null) {
                rightNode = new Node(rightName);
                nodeMap.put(rightName, rightNode);
            }
            node.setRight(rightNode);

            if (name.equals("AAA")) {
                current = node;
            }
        }

        int neededInstructions = 0;

        do {
            char instruction = instructions[neededInstructions % instructions.length];

            if (instruction == 'L') {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
            neededInstructions++;

        } while (!current.getName().equals("ZZZ"));

        System.out.println(neededInstructions);
    }

    class Node {
        private String name;
        private Node left;
        private Node right;

        public Node(String name) {
            this.name = name;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public String getName() {
            return name;
        }

    }

    @Override
    public void part2() {
        Pattern p = Pattern.compile("(?<name>\\w{3}) = \\((?<left>\\w{3}), (?<right>\\w{3})\\)");

        var lines = this.readFile("08.txt").split("\n");
        char[] instructions = lines[0].toCharArray();
        Map<String, Node> nodeMap = new HashMap<>();
        Map<Integer, Long> stepsTillGoal = new HashMap<>();

        List<Node> nodes = new LinkedList<>();

        for (int i = 2; i < lines.length; i++) {

            Matcher m = p.matcher(lines[i]);
            String name = "";
            String leftName = "";
            String rightName = "";

            while (m.find()) {
                name = m.group("name");
                leftName = m.group("left");
                rightName = m.group("right");
            }

            Node node = nodeMap.get(name);
            if (node == null) {
                node = new Node(name);
                nodeMap.put(name, node);
            } else {
                node.name = name;
            }

            Node leftNode = nodeMap.get(leftName);
            if (leftNode == null) {
                leftNode = new Node(leftName);
                nodeMap.put(leftName, leftNode);
            }
            node.setLeft(leftNode);

            Node rightNode = nodeMap.get(rightName);
            if (rightNode == null) {
                rightNode = new Node(rightName);
                nodeMap.put(rightName, rightNode);
            }
            node.setRight(rightNode);

            if (name.endsWith("A")) {
                nodes.add(node);
            }
        }

        long neededInstructions = 0;

        do {
            char instruction = instructions[(int) (neededInstructions % instructions.length)];

            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);

                if (instruction == 'L') {
                    nodes.set(i, node.getLeft());
                } else {
                    nodes.set(i, node.getRight());
                }

                if (node.getName().endsWith("Z")) {

                    stepsTillGoal.putIfAbsent(i, neededInstructions);
                }
            }
            neededInstructions++;

        } while (stepsTillGoal.size() != nodes.size());

        System.out.println(calculateLCM(stepsTillGoal.values().stream().mapToLong(x -> x).toArray()));

    }

    private static long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static long calculateLCM(long[] numbers) {
        return Arrays.stream(numbers).reduce(1, D08::lcm);
    }

}
