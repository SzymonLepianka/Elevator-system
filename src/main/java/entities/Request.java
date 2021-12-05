package entities;

public class Request extends Entity {
    private final int currentFloor;
    private final int targetFloor;
    private Elevator assignedElevator;

    public Request(int currentFloor, int targetFloor) {
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
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
}
