package simulation;

import entities.Elevator;
import entities.Request;
import utils.Logger;
import world.World;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ElevatorUpdater {
    private static final World world = World.getInstance();

    public static void assignRequestsFcfs() {
        var allNotMovingElevatorsWithoutTarget =
                world.getElevators()
                        .stream()
                        .filter(x -> x.getTargetFloor().isEmpty() && x.getState() == Elevator.State.NOT_MOVING)
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

            if (nearestNotMovingElevator != null) {
                nearestNotMovingElevator.takeRequest(request);
                Logger.getInstance().logNewMessage(request + " was taken by " + nearestNotMovingElevator.getID());
            } else {
                break;
            }
        }
    }

    public static void assignRequestsPrimitiveCost() {
        var allElevators = world.getElevators();
        var allUnassignedRequests = world.getRequests().stream().filter(x -> !x.isElevatorAssigned()).collect(Collectors.toList());
        for (var request : allUnassignedRequests) {
            var costs = new HashMap<Elevator, Integer>();
            for (var elevator : allElevators) {
                costs.put(elevator, calculateCost(elevator, request));
            }
            System.out.println(costs);

            // find elevator with the lowest cost
            Map.Entry<Elevator, Integer> min = null;
            for (var entry : costs.entrySet()) {
                if (min == null || min.getValue() > entry.getValue()) {
                    min = entry;
                }
            }
            if (min != null) {
                min.getKey().takeRequest(request);
                Logger.getInstance().logNewMessage(request + " was taken by " + min.getKey().getID());
            }
        }
    }

    private static Integer calculateCost(Elevator elevator, Request request) {
        var targetFloorList = elevator.getTargetFloor();
        var currentElevatorFloor = elevator.getCurrentFloor();

        // TODO improve this
        int cost = 0;
        cost += Math.abs(currentElevatorFloor - request.getCurrentFloor());
        for (var targetFloor : targetFloorList) {
            cost += Math.abs(currentElevatorFloor - targetFloor);
            currentElevatorFloor = targetFloor;
        }
        return cost;
    }

    private static boolean between(int lowerValue, int upperValue, int value1, int value2) {
        return value1 >= lowerValue && value1 <= upperValue && value2 >= lowerValue && value2 <= upperValue;
    }

    private static Elevator.State direction(int startFloor, int targetFloor) {
        if (startFloor < targetFloor) {
            return Elevator.State.MOVING_UP;
        } else {
            return Elevator.State.MOVING_DOWN;
        }
    }

    public static void performActionOfElevators() {
        var allElevators =
                world.getElevators();
        for (var elevator : allElevators) {
            if (elevator.getState() == Elevator.State.MOVING_DOWN) {
                if (elevator.getTargetFloor().get(0) == elevator.getCurrentFloor()) {
                    elevator.getTargetFloor().remove(0);
                    elevator.setState(Elevator.State.NOT_MOVING);
                    Logger.getInstance().logNewMessage(elevator + " changed from MOVING_DOWN to NOT_MOVING");
                } else {
                    elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    Logger.getInstance().logNewMessage(elevator + " decrement current floor");
                }
            } else if (elevator.getState() == Elevator.State.MOVING_UP) {
                if (elevator.getTargetFloor().get(0) == elevator.getCurrentFloor()) {
                    elevator.getTargetFloor().remove(0);
                    elevator.setState(Elevator.State.NOT_MOVING);
                    Logger.getInstance().logNewMessage(elevator + " changed from MOVING_UP to NOT_MOVING");
                } else {
                    elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                    Logger.getInstance().logNewMessage(elevator + " increment current floor");
                }
            } else {
                if (!elevator.getTargetFloor().isEmpty()) {
                    if (elevator.getTargetFloor().get(0) > elevator.getCurrentFloor()) {
                        elevator.setState(Elevator.State.MOVING_UP);
                        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                        Logger.getInstance().logNewMessage(elevator + " changed from NOT_MOVING to MOVING_UP and increment floor");

                    } else if (elevator.getTargetFloor().get(0) < elevator.getCurrentFloor()) {
                        elevator.setState(Elevator.State.MOVING_DOWN);
                        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                        Logger.getInstance().logNewMessage(elevator + " changed from NOT_MOVING to MOVING_DOWN and decrement floor");
                    } else {
                        elevator.removeTargetFloor();
                        Logger.getInstance().logNewMessage(elevator + " not moving, is on target floor");
                    }
                }
            }
        }
    }
}
