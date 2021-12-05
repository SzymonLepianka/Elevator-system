package world;

import entities.Elevator;
import entities.Entity;
import entities.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    private static World instance;
    private final WorldConfiguration worldConfig = new WorldConfiguration();
    private final List<Entity> allEntities = new ArrayList<>();
    private int simulationStep = 0;

    public static World getInstance() {
        // Result variable here may seem pointless, but it's needed for DCL (Double-checked locking).
        var result = instance;
        if (instance != null) {
            return result;
        }
        synchronized (World.class) {
            if (instance == null) {
                instance = new World();
            }
            return instance;
        }
    }

    public WorldConfiguration getConfig() {
        return worldConfig;
    }

    public List<Entity> getAllEntities() {
        return new ArrayList<>(this.allEntities);
    }

    public void addEntity(Entity entity) {
        synchronized (allEntities) {
            allEntities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        synchronized (allEntities) {
            allEntities.remove(entity);
        }
    }

    public List<Elevator> getElevators() {
        synchronized (allEntities) {
            return allEntities.stream().filter(Elevator.class::isInstance).map(Elevator.class::cast).collect(Collectors.toList());
        }
    }

    public List<Request> getRequests() {
        synchronized (allEntities) {
            return allEntities.stream().filter(Request.class::isInstance).map(Request.class::cast).collect(Collectors.toList());
        }
    }

    public int getSimulationStep() {
        return simulationStep;
    }

    public void incrementSimulationStep() {
        this.simulationStep++;
    }
}
