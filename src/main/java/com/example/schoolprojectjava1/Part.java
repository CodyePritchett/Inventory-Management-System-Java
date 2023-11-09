package com.example.schoolprojectjava1;

/**
 * Represents a part in the inventory management system.
 */
public class Part {
    /**
     * The unique identifier of the part.
     */
    private int id;

    /**
     * The name of the part.
     */
    private String name;

    /**
     * The price of the part.
     */
    private double price;

    /**
     * The current inventory level of the part.
     */
    private int inventoryLevel;

    /**
     * The minimum allowed inventory level for the part.
     */
    private int min;

    /**
     * The maximum allowed inventory level for the part.
     */
    private int max;

    /**
     * Constructs a Part object with specified attributes.
     *
     * @param id             The unique identifier of the part.
     * @param name           The name of the part.
     * @param price          The price of the part.
     * @param inventoryLevel The current inventory level of the part.
     * @param min            The minimum allowed inventory level for the part.
     * @param max            The maximum allowed inventory level for the part.
     */
    public Part(int id, String name, double price, int inventoryLevel, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventoryLevel = inventoryLevel;
        this.min = min;
        this.max = max;
    }

    /**
     * Sets the unique identifier of the part.
     *
     * @param id The new identifier for the part.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the part.
     *
     * @param name The new name for the part.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the part.
     *
     * @param price The new price for the part.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the inventory level of the part.
     *
     * @param inventoryLevel The new inventory level for the part.
     */
    public void setInventoryLevel(int inventoryLevel) {
        this.inventoryLevel = inventoryLevel;
    }

    /**
     * Sets the minimum allowed inventory level for the part.
     *
     * @param min The new minimum inventory level for the part.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets the maximum allowed inventory level for the part.
     *
     * @param max The new maximum inventory level for the part.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Retrieves the unique identifier of the part.
     *
     * @return The identifier of the part.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the name of the part.
     *
     * @return The name of the part.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the part.
     *
     * @return The price of the part.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retrieves the current inventory level of the part.
     *
     * @return The inventory level of the part.
     */
    public int getInventoryLevel() {
        return inventoryLevel;
    }

    /**
     * Retrieves the minimum allowed inventory level for the part.
     *
     * @return The minimum inventory level for the part.
     */
    public int getMin() {
        return min;
    }

    /**
     * Retrieves the maximum allowed inventory level for the part.
     *
     * @return The maximum inventory level for the part.
     */
    public int getMax() {
        return max;
    }

}



