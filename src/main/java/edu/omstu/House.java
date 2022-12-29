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

    public void work() throws FloorNotFoundException {
        while (!tasks.isEmpty()) {
            Pair<Integer, Integer> task = tasks.poll();
            if (task.getFirst() > nFloors || task.getSecond() > nFloors) {
                throw new FloorNotFoundException();
            }
            if (firstLift.getTasks().size() == secondLift.getTasks().size()) {
                int floorOfFirstLift = firstLift.getTasks().isEmpty() ? firstLift.getFloor()
                        : firstLift.getTasks().peek();
                int floorOfSecondLift = secondLift.getTasks().isEmpty() ? secondLift.getFloor()
                        : secondLift.getTasks().peek();
                if (Math.abs(floorOfFirstLift - task.getFirst()) < Math.abs(floorOfSecondLift - task.getFirst())) {
                    firstLift.getTasks().add(task.getFirst());
                    firstLift.getTasks().add(task.getSecond());
                } else {
                    secondLift.getTasks().add(task.getFirst());
                    secondLift.getTasks().add(task.getSecond());
                }
            } else if (firstLift.getTasks().size() < secondLift.getTasks().size()) {
                firstLift.getTasks().add(task.getFirst());
                firstLift.getTasks().add(task.getSecond());
            } else {
                secondLift.getTasks().add(task.getFirst());
                secondLift.getTasks().add(task.getSecond());
            }
        }
        firstLift.start();
        secondLift.start();
    }
}
