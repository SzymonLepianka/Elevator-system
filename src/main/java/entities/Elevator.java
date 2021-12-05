package entities;

import java.util.ArrayList;
import java.util.List;

public class Elevator extends Entity {
    private final int ID;
    private State state;
    private int currentFloor;
    private final List<Integer> targetFloor = new ArrayList<>();

    public Elevator(int ID) {
        this.ID = ID;
        currentFloor = 0;
    }

    public void takeRequest(Request request) {
        request.setAssignedElevator(this);
        targetFloor.add(request.getCurrentFloor());
        targetFloor.add(request.getTargetFloor());
        if (request.getCurrentFloor() < this.currentFloor) {
            state = State.MOVING_DOWN;
        } else if (request.getCurrentFloor() > this.currentFloor) {
            state = State.MOVING_UP;
        } else {
            state = State.NOT_MOVING;
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getID() {
        return ID;
    }

    public List<Integer> getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor.add(targetFloor);
    }

    public void setTargetFloor(int index, int targetFloor) {
        this.targetFloor.add(index, targetFloor);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void removeTargetFloor() {
        if (!targetFloor.isEmpty()) {
            targetFloor.remove(0);
        }
    }

    public enum State {
        MOVING_UP,
        MOVING_DOWN,
        NOT_MOVING
    }
}
