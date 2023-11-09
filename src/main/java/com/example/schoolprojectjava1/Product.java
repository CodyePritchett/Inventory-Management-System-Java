package com.example.schoolprojectjava1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Represents a product in the inventory system.
 */
public class Product {
    /**
     * A list of parts associated with the product.
     */
    private ObservableList<Part> associatedParts;

    /**
     * The unique identifier of the product.
     */
    private int id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The price of the product.
     */
    private double price;

    /**
     * The current inventory level of the product.
     */
    private int inventoryLevel;

    /**
     * The minimum allowed inventory level for the product.
     */
    private int min;

    /**
     * The maximum allowed inventory level for the product.
     */
    private int max;

    /**
     * Creates a Product object with specified attributes and initializes its associated parts list.
     *
     * @param id The unique identifier of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param inventoryLevel The current inventory level of the product.
     * @param min The minimum allowed inventory level for the product.
     * @param max The maximum allowed inventory level for the product.
     */
    public Product(int id, String name, double price, int inventoryLevel, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventoryLevel = inventoryLevel;
        this.min = min;
        this.max = max;
        this.associatedParts = FXCollections.observableArrayList();
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID to set for the product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The name to set for the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The price to set for the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the inventory level of the product.
     *
     * @param inventoryLevel The inventory level to set for the product.
     */
    public void setInventoryLevel(int inventoryLevel) {
        this.inventoryLevel = inventoryLevel;
    }

    /**
     * Sets the minimum allowed inventory level for the product.
     *
     * @param min The minimum inventory level to set for the product.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets the maximum allowed inventory level for the product.
     *
     * @param max The maximum inventory level to set for the product.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Returns the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the product.
     *
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the inventory level of the product.
     *
     * @return The inventory level of the product.
     */
    public int getInventoryLevel() {
        return inventoryLevel;
    }

    /**
     * Returns the minimum allowed inventory level for the product.
     *
     * @return The minimum inventory level for the product.
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the maximum allowed inventory level for the product.
     *
     * @return The maximum inventory level for the product.
     */
    public int getMax() {
        return max;
    }


    /**
     * Removes an associated part from the product.
     *
     * @param selectedAssociatedPart The part to be removed from the associated parts list.
     * @return True if the part was successfully removed, false otherwise.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Adds a list of parts to the associated parts list.
     *
     * @param parts The list of parts to be added.
     */
    public void addAllAssociatedParts(List<Part> parts) {
        associatedParts.addAll(parts);
    }

    /**
     * Retrieves a list of all associated parts for the product.
     *
     * @return The list of associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
