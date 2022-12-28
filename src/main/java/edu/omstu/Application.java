package edu.omstu;

import java.util.LinkedList;
import java.util.Queue;

public class Application {
    public static void main(String[] args) {
        int nthFirst = 5;
        int nthSecond = 3;
        int nFloors = 10;
        Queue<Pair<Integer, Integer>> tasks = new LinkedList<>();
        tasks.add(new Pair<Integer, Integer> (1, 2));
        tasks.add(new Pair<Integer, Integer> (1, 5));
        tasks.add(new Pair<Integer, Integer> (5, 3));
        tasks.add(new Pair<Integer, Integer> (5, 1));
        tasks.add(new Pair<Integer, Integer> (7, 2));
        tasks.add(new Pair<Integer, Integer> (12, 7));
        House house = new House(nthFirst, nthSecond, nFloors, tasks);
        house.work();
    }
}
