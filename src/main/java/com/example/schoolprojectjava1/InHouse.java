package com.example.schoolprojectjava1;


/**
 * Represents a subclass of Part called InHouse, with additional machineId property.
 */
public class InHouse extends Part {

    /**
     *Stores the machine ID associated with the part
     */
    private int machineId;

    /**
     * Constructs an InHouse part with the given properties, including machineId.
     *
     * @param id            The ID of the part.
     * @param name          The name of the part.
     * @param price         The price of the part.
     * @param inventoryLevel The inventory level of the part.
     * @param min           The minimum inventory required for the part.
     * @param max           The maximum inventory allowed for the part.
     * @param machineId     The machine ID associated with the InHouse part.
     */
    public InHouse(int id, String name, double price, int inventoryLevel, int min, int max, int machineId) {
        super(id, name, price, inventoryLevel, min, max);
        this.machineId = machineId;
    }

    /**
     * Sets the machine ID for the InHouse part.
     *
     * @param machineId The machine ID to set.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * Retrieves the machine ID of the InHouse part.
     *
     * @return The machine ID associated with the InHouse part.
     */
    public int getMachineId() {
        return machineId;
    }
}

