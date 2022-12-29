package edu.omstu;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Application {
    public static <T> void main(String[] args) throws FloorNotFoundException {
        int nthFirst = 5;
        int nthSecond = 3;
        int nFloors = 12;
        Queue<Pair<Integer, Integer>> tasks = new PriorityQueue<Pair<Integer, Integer>>(
                new Comparator<Pair<Integer, Integer>>() {
                    @Override
                    public int compare(Pair<Integer, Integer> x, Pair<Integer, Integer> y) {
                        return x.getFirst() - y.getFirst();
                    }

                });
        tasks.add(new Pair<Integer, Integer>(1, 2));
        tasks.add(new Pair<Integer, Integer>(1, 5));
        tasks.add(new Pair<Integer, Integer>(5, 3));
        tasks.add(new Pair<Integer, Integer>(5, 1));
        tasks.add(new Pair<Integer, Integer>(7, 2));
        tasks.add(new Pair<Integer, Integer>(12, 7));
        House house = new House(nthFirst, nthSecond, nFloors, tasks);
        house.work();
    }
}
