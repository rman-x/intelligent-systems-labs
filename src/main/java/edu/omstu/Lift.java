package edu.omstu;

import java.util.LinkedList;
import java.util.Queue;

public class Lift {
    private String title;
    private LiftState state = LiftState.WAIT;
    private int currentFloor;
    private int destinationFloor;
    private int count = 0;
    private Queue<Integer> tasks = new LinkedList<>();


    public Lift(String title, int startFloor) {
        this.title = title;
        this.currentFloor = startFloor;
    }

    void addTask(Integer task) {
        tasks.add(task);
    }

    int getCurrentFloor() {
        return currentFloor;
    }

    void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public void work() {
        while (!tasks.isEmpty()) {
            switch (state) {
                case WAIT:
                    destinationFloor = tasks.poll();
                    System.out.println(String.format("Lift %s is stopped on %d-nth the floor with step count %d.", title, currentFloor, count));
                    if (currentFloor < destinationFloor) {
                        state = LiftState.GO_UP;
                    }
                    if (currentFloor > destinationFloor) {
                        state = LiftState.GO_DOWN;
                    }
                    count = 0;
                    break;
                case GO_UP:
                    System.out.println(String.format("Lift %s is on %d-nth the floor and going up.", title, currentFloor));
                    if (currentFloor == destinationFloor) {
                        state = LiftState.WAIT;
                        break;
                    }
                    currentFloor++;
                    count++;
                    break;
                case GO_DOWN:
                    System.out.println(String.format("Lift %s is on %d-nth the floor and going down.", title, currentFloor));
                    if (currentFloor == destinationFloor) {
                        state = LiftState.WAIT;
                        break;
                    }
                    currentFloor--;
                    count++;
                    break;
                default:
                    break;
            }
        }
    }
}
