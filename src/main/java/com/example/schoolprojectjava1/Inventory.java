package com.example.schoolprojectjava1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *Manages the central inventory system.
 */
public class Inventory {
    /**
     * Keeps track of the last assigned part/product ID
     */
    private static int lastPartId = 0;

    /**
     * Keeps track of the last assigned product ID.
     */
    private static int lastProductId = 0;

    /**
     * stores the part/products
     */
    public static ObservableList<Part> allParts = FXCollections.observableArrayList(); // Stores all parts

    /**
     * Stores all products currently in the inventory.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList(); // Stores all products


    /**
     * Retrieves an observable list containing all parts in the inventory.
     *
     * @return An observable list of all parts in the inventory.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Retrieves an observable list containing all products in the inventory.
     *
     * @return An observable list of all products in the inventory.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Adds a new part to the inventory.
     * Increments the part ID, assigns it to the new part, and adds the part to the list of all parts.
     *
     * @param newPart The new part to be added to the inventory.
     */
    public static void addPart(Part newPart) {
        lastPartId++;
        newPart.setId(lastPartId);
        allParts.add(newPart);
    }

    /**
     * Adds a new product to the inventory.
     * Increments the product ID, assigns it to the new product, and adds the product to the list of all products.
     *
     * @param newProduct The new product to be added to the inventory.
     */
    public static void addProduct(Product newProduct) {
        lastProductId++;
        newProduct.setId(lastProductId);
        allProducts.add(newProduct);
    }

}
