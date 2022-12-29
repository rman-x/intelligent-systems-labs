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

    Queue<Integer> getTasks() {
        return tasks;
    }

    LiftState getState() {
        return state;
    }

    void setState(LiftState state) {
        this.state = state;
    }

    int getFloor() {
        return currentFloor;
    }

    void setFloor(int floor) {
        currentFloor = floor;
    }

    public void start() {
        System.out.println(String.format("The floors which served by lift %s  %s.", title, tasks));
        while (!tasks.isEmpty()) {
            switch (state) {
                case WAIT:
                    destinationFloor = tasks.peek();
                    if (currentFloor < destinationFloor) {
                        state = LiftState.GO_UP;
                    }
                    if (currentFloor > destinationFloor) {
                        state = LiftState.GO_DOWN;
                    }
                    if (currentFloor == destinationFloor) {
                        tasks.poll();
                        break;
                    }
                    count = 0;
                    break;
                case GO_UP:
                    if (currentFloor == destinationFloor) {
                        state = LiftState.WAIT;
                        System.out.println(String.format(
                                "Lift %s is stopped on %d-nth the floor, opened and then closed its doors with step count %d.",
                                title, currentFloor, count));
                        tasks.poll();
                        break;
                    }
                    System.out.println(
                            String.format("Lift %s is on %d-nth the floor and going up.", title, currentFloor));
                    currentFloor++;
                    count++;
                    break;
                case GO_DOWN:
                    if (currentFloor == destinationFloor) {
                        state = LiftState.WAIT;
                        System.out.println(String.format(
                                "Lift %s is stopped on %d-nth the floor, opened and then closed its doors with step count %d.",
                                title, currentFloor, count));
                        tasks.poll();
                        break;
                    }
                    System.out.println(
                            String.format("Lift %s is on %d-nth the floor and going down.", title, currentFloor));
                    currentFloor--;
                    count++;
                    break;
                default:
                    break;
            }
        }
        System.out.println(String.format("Lift %s has done all its work.", title));
    }
}
