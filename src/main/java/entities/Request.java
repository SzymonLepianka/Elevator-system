package entities;

public class Request extends Entity {
    private final int currentFloor;
    private final int targetFloor;
    private final Elevator.State direction;
    private Elevator assignedElevator;

    public Request(int currentFloor, int targetFloor) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        direction = currentFloor < targetFloor ? Elevator.State.MOVING_UP : Elevator.State.MOVING_DOWN;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public boolean isElevatorAssigned() {
        return assignedElevator != null;
    }

    public void setAssignedElevator(Elevator assignedElevator) {
        this.assignedElevator = assignedElevator;
    }

    public Elevator.State getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Request{" +
                "currentFloor=" + currentFloor +
                ", targetFloor=" + targetFloor +
                '}';
    }
}
