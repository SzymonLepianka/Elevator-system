package entities;

import java.util.UUID;

public abstract class Entity {

    private final UUID uniqueID = UUID.randomUUID();

    protected Entity() {
    }

    public UUID getUniqueID() {
        return uniqueID;
    }

    @Override
    public String toString() {
        return getClass().toString() + ": " + getUniqueID();
    }
}
