package edu.omstu;

import java.util.Queue;

public class House {
    private int nFloors;
    private Lift firstLift;
    private Lift secondLift;
    private Queue<Pair<Integer, Integer>> tasks;

    public House(int nthFirst, int nthSecond, int nFloors, Queue<Pair<Integer, Integer>> tasks) {
        this.tasks = tasks;
        this.firstLift = new Lift("№1", nthFirst);
        this.secondLift = new Lift("№2", nthSecond);
        this.nFloors = nFloors;
    }

    public void work() {
        int nthFirst = firstLift.getCurrentFloor();
        int nthSecond = secondLift.getCurrentFloor();
        while (!tasks.isEmpty()) {
            Pair<Integer, Integer> task = tasks.poll();
            Integer source = task.getFirst();
            Integer destination = task.getSecond();
            if (Math.abs(source - firstLift.getCurrentFloor()) < Math.abs(source - secondLift.getCurrentFloor())) {
                firstLift.addTask(source);
                firstLift.addTask(destination);
                firstLift.setCurrentFloor(destination);
            } else {
                secondLift.addTask(source);
                secondLift.addTask(destination);
                secondLift.setCurrentFloor(destination);
            }
        }
        firstLift.setCurrentFloor(nthFirst);
        secondLift.setCurrentFloor(nthSecond);
        firstLift.work();
        secondLift.work();
    }
}
