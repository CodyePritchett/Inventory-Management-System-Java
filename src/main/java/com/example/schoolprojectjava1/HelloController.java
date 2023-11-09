package com.example.schoolprojectjava1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.schoolprojectjava1.Inventory.allParts;

/**
 * FUTURE ENHANCEMENT expanding the tables so that they display Company Name or Machine-ID
 */

/**
 * the javadoc is located in scr/java/com.example/javadoc
 */

/**
 * RUNTIME ERROR occurred when trying to open the Add Part screen.
 * The issue was caused by attempting to include all classes within HelloController.
 * The problem was resolved by creating separate controllers, such as AddPartController, for each class.
 */

/**
 *  Main controller handles opening screens, opening tables, and many other functions
 */
public class HelloController implements Initializable {



    /**
     * The table displaying the list of parts.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * The column displaying the ID of the parts.
     */
    @FXML
    private TableColumn<Part, Integer> PTid;

    /**
     * The column displaying the name of the parts.
     */
    @FXML
    private TableColumn<Part, String> PTname;

    /**
     * The column displaying the inventory level of the parts.
     */
    @FXML
    private TableColumn<Part, Integer> PTinventoryLevel;

    /**
     * The column displaying the price of the parts.
     */
    @FXML
    private TableColumn<Part, Double> PTprice;

    /**
     * The table displaying the list of products.
     */
    @FXML
    public TableView<Product> productTableView;

    /**
     * The column displaying the ID of the products.
     */
    @FXML
    public TableColumn<Product, Integer> PDid;

    /**
     * The column displaying the name of the products.
     */
    @FXML
    public TableColumn<Product, String> PDname;

    /**
     * The column displaying the inventory level of the products.
     */
    @FXML
    public TableColumn<Product, Integer> PDinventoryLevel;

    /**
     * The column displaying the price of the products.
     */
    /**
     * The column displaying the price of the products.
     */
    @FXML
    public TableColumn<Product, Double> PDprice;

    /**
     * The text field for searching products.
     */
    public TextField productSearchField;

    /**
     * The text field for searching parts.
     */
    @FXML
    public TextField partSearchField;

    /**
     * The button for initiating the part search.
     */
    @FXML
    public Button partSearchButton;

    /**
     * The button for initiating the product search.
     */
    public Button productSearchButton;

    /**
     * The button for adding a new part.
     */
    @FXML
    private Button PTADD;

    /**
     * The button for modifying a selected part.
     */
    @FXML
    private Button PTMOD;

    /**
     * The button for adding a new product.
     */
    @FXML
    private Button PDADD;

    /**
     * The button for modifying a selected product.
     */
    @FXML
    private Button PDMOD;

    /**
     * The button for exiting the application.
     */
    @FXML
    private Button exitButton;


    /**
     * The list containing parts filtered based on search criteria.
     */
    private ObservableList<Part> filteredParts = FXCollections.observableArrayList();

    /**
     * The list containing products filtered based on search criteria.
     */
    private ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    /**
     * Set up the table views for displaying parts and products.
     * Populates the table views with data from the Inventory and configures columns to display relevant properties.
     */
    private void setupTableViews() {

        // Populate the parts table view with data from the Inventory's list of all parts
        partTableView.setItems(Inventory.getAllParts());

        // Configure the columns to display specific properties of the Part object
        PTid.setCellValueFactory(new PropertyValueFactory<>("id"));
        PTname.setCellValueFactory(new PropertyValueFactory<>("name"));
        PTinventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inventoryLevel"));
        PTprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Populate the products table view with data from the Inventory's list of all products
        productTableView.setItems(Inventory.getAllProducts());

        // Configure the columns to display specific properties of the Product object
        PDid.setCellValueFactory(new PropertyValueFactory<>("id"));
        PDname.setCellValueFactory(new PropertyValueFactory<>("name"));
        PDinventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inventoryLevel"));
        PDprice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }




    /**
     * Initializes the controller after its root element has been completely processed.
     * This method runs the setupTableViews method to configure and populate the parts and products table views.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableViews();
    }





    /**
     * Opens the add part screen.
     *
     * @param event The event triggering the action, typically a button click.
     * @throws IOException If an I/O error occurs while loading the "Add_Part.fxml" layout.
     */
    @FXML
    private void openAddPartScreen(ActionEvent event) throws IOException {
        // Load "Add_Part.fxml" layout for adding parts
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add_Part.fxml"));
        Parent addPartRoot = fxmlLoader.load();
        Scene addPartScene = new Scene(addPartRoot);

        // Create and display the "Add Part" stage
        Stage addPartStage = new Stage();
        addPartStage.setTitle("Add Part");
        addPartStage.setScene(addPartScene);
        addPartStage.show();

        // Close the current main stage
        Stage mainStage = (Stage) PTADD.getScene().getWindow();
        mainStage.close();
    }

    /**
     * Opens the Modify part screen.
     *
     * @param event The event triggering the action, typically a button click.
     * @throws IOException If an I/O error occurs while loading the "Modify_Part.fxml" layout.
     */
    @FXML
    private void openModifyPartScreen(ActionEvent event) throws IOException {
        // Get the selected part from the table view
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        // Check if a part is selected
        if (selectedPart == null) {
            showErrorAlert("Please select a part to modify.");
            return;
        }

        // Load "Modify_Part.fxml" layout for modifying parts
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Modify_Part.fxml"));
        Parent modifyPartRoot = fxmlLoader.load();

        // Pass the selected part to the ModifyPartController
        ModifyPartController modifyPartController = fxmlLoader.getController();
        modifyPartController.setSelectedPart(selectedPart);

        // Create and display the "Modify Part" stage
        Scene modifyPartScene = new Scene(modifyPartRoot);
        Stage modifyPartStage = new Stage();
        modifyPartStage.setTitle("Modify Part");
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();

        // Close the current main stage
        Stage mainStage = (Stage) PTMOD.getScene().getWindow();
        mainStage.close();
    }



    /**
     * Opens the add products screen.
     *
     * @param event The event triggering the action, typically a button click.
     * @throws IOException If an I/O error occurs while loading the "Add_Product.fxml" layout.
     */
    @FXML
    private void openAddProductsScreen(ActionEvent event) throws IOException {
        // Load "Add_Product.fxml" layout for adding products
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add_Product.fxml"));
        Parent addProductsRoot = fxmlLoader.load();

        // Create and display the "Add Products" stage
        Scene addProductsScene = new Scene(addProductsRoot);
        Stage addProductsStage = new Stage();
        addProductsStage.setTitle("Add Products");
        addProductsStage.setScene(addProductsScene);
        addProductsStage.show();

        // Close the current main stage
        Stage mainStage = (Stage) PDADD.getScene().getWindow();
        mainStage.close();
    }

    /**
     * Opens the modify products screen.
     *
     * @param event The event triggering the action, typically a button click.
     * @throws IOException If an I/O error occurs while loading the "Modify_Product.fxml" layout.
     */
    @FXML
    private void openModifyProductScreen(ActionEvent event) throws IOException {
        // Get the selected product from the table view
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            showErrorAlert("Please select a product to modify.");
            return;
        }

        // Load "Modify_Product.fxml" layout for modifying products
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Modify_Product.fxml"));
        Parent modifyProductRoot = fxmlLoader.load();

        // Create and display the "Modify Product" stage
        Scene modifyProductScene = new Scene(modifyProductRoot);
        Stage modifyProductStage = new Stage();
        modifyProductStage.setTitle("Modify Product");
        modifyProductStage.setScene(modifyProductScene);
        modifyProductStage.show();

        // Pass the selected product to the controller
        ModifyProductController modifyProductController = fxmlLoader.getController();
        modifyProductController.initData(selectedProduct, selectedProduct.getAllAssociatedParts());

        // Close the current main screen
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.close();
    }


    /**
     * Handles searching for parts based on the provided search text.
     *
     * @param event The event triggering the action, typically a button click.
     */
    @FXML
    private void handlePartSearch(ActionEvent event) {
        // Get the search text from the input field
        String searchText = partSearchField.getText().trim().toLowerCase();

        // Clear the previous search results
        filteredParts.clear();

        // If search text is empty, show all parts and return
        if (searchText.isEmpty()) {
            filteredParts.addAll(Inventory.getAllParts());
            partTableView.setItems(filteredParts);
            return;
        }

        // Iterate through all parts and add the matches to the filtered list
        boolean foundMatches = false; // Flag to track if matches were found
        for (Part part : Inventory.getAllParts()) {
            String partIdText = String.valueOf(part.getId()).toLowerCase();
            String partName = part.getName().toLowerCase();

            if (partIdText.contains(searchText) || partName.contains(searchText)) {
                filteredParts.add(part);
                foundMatches = true; // Set the flag to true if matches were found
            }
        }

        // Update the table view with the filtered parts
        partTableView.setItems(filteredParts);

        // Show an alert if no matches were found
        if (!foundMatches) {
            showErrorAlert("No matching parts were found for the search.");
        }
    }

    /**
     * Handles searching for products based on the provided search text.
     *
     * @param actionEvent The action event that triggers the search, typically a button click.
     */
    @FXML
    public void handleProductSearch(ActionEvent actionEvent) {
        String searchText = productSearchField.getText().trim().toLowerCase();

        // Clear the previous search results
        filteredProducts.clear();

        // If the search text is empty, show all products and return
        if (searchText.isEmpty()) {
            filteredProducts.addAll(Inventory.getAllProducts());
            productTableView.setItems(filteredProducts);
            return;
        }

        // Iterate through all products and add the matches to the filtered list
        boolean foundMatches = false; // Flag to track if matches were found
        for (Product product : Inventory.getAllProducts()) {
            String productIdText = String.valueOf(product.getId()).toLowerCase();
            String productName = product.getName().toLowerCase();

            if (productIdText.contains(searchText) || productName.contains(searchText)) {
                filteredProducts.add(product);
                foundMatches = true; // Set the flag to true if matches were found
            }
        }

        // Update the table view with the filtered products
        productTableView.setItems(filteredProducts);

        // Show alert if no matches were found
        if (!foundMatches) {
            showErrorAlert("No matching parts were found for the search.");
        }
    }

    /**
     * Handles closing the application by closing the main stage.
     *
     * @param event The action event that triggers the exit, typically a button click.
     */
    @FXML
    private void handleExitAction(ActionEvent event) {
        // Get the current stage and close it
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the action of deleting a part from the inventory.
     *
     * @param actionEvent The action event that triggers the delete action, typically a button click.
     */
    public void handleDeletePartAction(ActionEvent actionEvent) {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            // No part is selected, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Part Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
        } else if (isPartAssociatedWithProducts(selectedPart)) {
            // Part is associated with products, show an error alert
            showErrorAlert("Cannot delete a part that is associated with products.");
        } else {
            // Confirm deletion with an alert
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to delete this part?");
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // User confirmed the deletion, remove the part
                    Inventory.getAllParts().remove(selectedPart);
                }
            });
        }
    }

    /**
     * Checks if a given part is associated with any products.
     *
     * @param part The part to check for associations.
     * @return {@code true} if the part is associated with any products, {@code false} otherwise.
     */
    private boolean isPartAssociatedWithProducts(Part part) {
        for (Product product : Inventory.getAllProducts()) {
            if (product.getAllAssociatedParts().contains(part)) {
                return true; // Part is linked to at least one product.
            }
        }
        return false; // Part is not associated with any products.
    }

    /**
     * Handles the deletion of a selected product.
     *
     * @param actionEvent The event that triggers the delete action.
     */
    public void handleDeleteProductAction(ActionEvent actionEvent) {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            // No product is selected, show an alert
            showErrorAlert("Please select a product to delete.");
            return;
        }

        if (!selectedProduct.getAllAssociatedParts().isEmpty()) {
            // Product has associated parts, show an alert
            showErrorAlert("Cannot delete a product with associated parts.");
            return;
        }

        // Confirm deletion with an alert
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete this product?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // User confirmed the deletion, remove the product
                Inventory.getAllProducts().remove(selectedProduct);
            }
        });
    }


    /**
     * Displays an error alert dialog with the provided message.
     *
     * @param message The error message to display in the alert.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null); // No header text in the alert
        alert.setContentText(message); // Set the content message of the alert
        alert.showAndWait(); // Display the alert and wait for user interaction
    }


}
