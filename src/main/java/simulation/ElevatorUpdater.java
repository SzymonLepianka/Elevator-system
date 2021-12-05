package simulation;

import entities.Elevator;
import utils.Logger;
import world.World;

import java.util.Comparator;
import java.util.stream.Collectors;

public class ElevatorUpdater {
    private static final World world = World.getInstance();

    public static void assignRequests() {
        var allNotMovingElevatorsWithoutTarget =
                world.getElevators()
                .stream()
                .filter(x->x.getTargetFloor().isEmpty() && x.getState()== Elevator.State.NOT_MOVING)
                .collect(Collectors.toList());
        var allUnassignedRequests = world.getRequests().stream().filter(x -> !x.isElevatorAssigned()).collect(Collectors.toList());
        for (var request : allUnassignedRequests) {
            int startFloor = request.getCurrentFloor();
            int targetFloor = request.getTargetFloor();

            var nearestNotMovingElevator = allNotMovingElevatorsWithoutTarget
                    .stream()
                    .min(Comparator.comparing(x -> Math.abs(startFloor - x.getCurrentFloor())))
                    .orElse(null);
            allNotMovingElevatorsWithoutTarget.remove(nearestNotMovingElevator);

            if(nearestNotMovingElevator != null){
                nearestNotMovingElevator.takeRequest(request);
                Logger.getInstance().logNewMessage("request was taken");
            } else {
                break;
            }
        }
    }

    public static void performActionOfElevators() {
        var allElevators =
                world.getElevators();
        for (var elevator: allElevators) {
            if (elevator.getState()== Elevator.State.MOVING_DOWN){
                if (elevator.getTargetFloor().get(0) == elevator.getCurrentFloor()){
                    elevator.getTargetFloor().remove(0);
                    elevator.setState(Elevator.State.NOT_MOVING);
                    Logger.getInstance().logNewMessage("changed moving down to not moving");
                } else{
                    elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    Logger.getInstance().logNewMessage("decrement current floor");
                }
            } else if(elevator.getState()== Elevator.State.MOVING_UP){
                if (elevator.getTargetFloor().get(0) == elevator.getCurrentFloor()){
                    elevator.getTargetFloor().remove(0);
                    elevator.setState(Elevator.State.NOT_MOVING);
                    Logger.getInstance().logNewMessage("changed moving up to not moving");
                }
                else{
                    elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                    Logger.getInstance().logNewMessage("increment current floor");
                }
            } else {
                if (!elevator.getTargetFloor().isEmpty()){
                    if (elevator.getTargetFloor().get(0) > elevator.getCurrentFloor()){
                        elevator.setState(Elevator.State.MOVING_UP);
                        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                        Logger.getInstance().logNewMessage("changed not moving  to  moving up and increment floor");

                    }
                    else if (elevator.getTargetFloor().get(0) < elevator.getCurrentFloor()){
                        elevator.setState(Elevator.State.MOVING_DOWN);
                        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                        Logger.getInstance().logNewMessage("changed not moving  to  moving down and decrement floor");
                    }
                    else{
                        elevator.removeTargetFloor();
                        Logger.getInstance().logNewMessage("not moving elevator is on target floor");
                    }
                }
            }
        }
    }
}
