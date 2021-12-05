package entities;

import world.World;

import java.util.ArrayList;
import java.util.List;

public class Elevator extends Entity {
    private final int ID;
    private final List<Integer> targetFloor = new ArrayList<>();
    private State state;
    private int currentFloor;

    public Elevator(int ID) {
        this.ID = ID;
        currentFloor = 0;
    }

    private static boolean between(int lowerValue, int upperValue, int value1, int value2) {
        if (lowerValue < upperValue) {
            return value1 >= lowerValue && value1 <= upperValue && value2 >= lowerValue && value2 <= upperValue;
        } else {
            return value1 >= upperValue && value1 <= lowerValue && value2 >= upperValue && value2 <= lowerValue;
        }
    }

    private static Elevator.State direction(int startFloor, int targetFloor) {
        if (startFloor < targetFloor) {
            return Elevator.State.MOVING_UP;
        } else {
            return Elevator.State.MOVING_DOWN;
        }
    }

    public void takeRequest(Request request) {
        request.setAssignedElevator(this);
        addRequestFloorsToTargetFloors(request);
        World.getInstance().removeEntity(request);
    }

    private void addRequestFloorsToTargetFloors(Request request) {
        var startFloor = currentFloor;
        for (int i = 0; i < targetFloor.size(); i++) {
            if (direction(startFloor, targetFloor.get(i)) == request.getDirection() && between(startFloor, targetFloor.get(i), request.getCurrentFloor(), request.getTargetFloor())) {
                if (targetFloor.get(i) != request.getTargetFloor()) {
                    targetFloor.add(i, request.getTargetFloor());
                }
                if (startFloor != request.getCurrentFloor()) {
                    targetFloor.add(i, request.getCurrentFloor());
                }
                return;
            }
            startFloor = targetFloor.get(i);
        }
        targetFloor.add(request.getCurrentFloor());
        targetFloor.add(request.getTargetFloor());
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

    @Override
    public String toString() {
        return "Elevator{" +
                "ID=" + ID +
                '}';
    }
}
