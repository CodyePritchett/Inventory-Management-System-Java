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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * is the controller for the add product screen
 */
public class AddProductController implements Initializable{

    /**
     * TableView to display the list of associated parts for the product.
     */
    @FXML
    public TableView<Part> associatedPartProdcutTV;

    /**
     * TableColumn for displaying the IDs of associated parts in the product's associated parts table.
     */
    @FXML
    public TableColumn<Part, Integer> associatedPartProdcutID;

    /**
     * TableColumn for displaying the names of associated parts in the product's associated parts table.
     */
    @FXML
    public TableColumn<Part, String> associatedPartProdcutName;

    /**
     * TableColumn for displaying the inventory levels of associated parts in the product's associated parts table.
     */
    @FXML
    public TableColumn<Part, Integer> associatedPartProdcutIL;

    /**
     * TableColumn for displaying the prices of associated parts in the product's associated parts table.
     */
    @FXML
    public TableColumn<Part, Double> associatedPartProdcutPrice;

    /**
     * TableColumn for displaying the IDs of available parts in the product's parts table.
     */
    @FXML
    public TableColumn<Part, Integer> partProductID;

    /**
     * TableColumn for displaying the names of available parts in the product's parts table.
     */
    @FXML
    public TableColumn<Part, String> partProductName;

    /**
     * TableColumn for displaying the inventory levels of available parts in the product's parts table.
     */
    @FXML
    public TableColumn<Part, Integer> partProductIL;


    /**
     * TableColumn for displaying the prices of available parts in the product's parts table.
     */
    @FXML
    public TableColumn<Part, Double> partProductPrice;

    /**
     * TableView to display the list of available parts for the product.
     */
    @FXML
    public TableView<Part> partProductTV;


    /**
     * Text field for entering search criteria when searching for parts.
     */
    @FXML
    public TextField partSearchField;

    /**
     * Text field for displaying or entering the ID of the part.
     */
    @FXML
    public TextField idTextField;

    /**
     * Text field for displaying or entering the name of the part.
     */
    @FXML
    public TextField nameTextField;

    /**
     * Text field for displaying or entering the inventory level of the part.
     */
    @FXML
    public TextField invTextField;

    /**
     * Text field for displaying or entering the price of the part.
     */
    @FXML
    public TextField priceTextField;

    /**
     * Text field for displaying or entering the maximum allowable quantity of the part.
     */
    @FXML
    public TextField maxTextField;

    /**
     * Text field for displaying or entering the minimum allowable quantity of the part.
     */
    @FXML
    public TextField minTextField;

    /**
     * Button for removing an associated part from a product.
     */
    @FXML
    public Button removeAssociatedPartButton;

    /**
     * Button for saving modifications or additions to a product.
     */
    @FXML
    public Button saveButton;

    /**
     * Button for adding a part to a product.
     */
    @FXML
    public Button addPartProductButton;

    /**
     * Button for searching for parts related to a product.
     */
    @FXML
    public Button partProductSearchButton;


    /**
     * for generating new ids
     */
    private static int lastUsedId = 0;

    /**
     * used in searching parts
     */
    private ObservableList<Part> filteredParts = FXCollections.observableArrayList();

    /**
     * list of parts to associate when save is clicked
     */
    private List<Part> associatedPartsList = new ArrayList<>();



    /**
     * Initializes the tables and sets up the initial data for the part and associated parts table views.
     *
     * @param location   The location used to resolve relative paths for the root object.
     * @param resources  The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the part table view with columns and data
        partProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partProductIL.setCellValueFactory(new PropertyValueFactory<>("inventoryLevel"));
        partProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partProductTV.setItems(Inventory.getAllParts());

        // Initialize the associated parts table view with columns and data
        associatedPartProdcutID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartProdcutName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartProdcutIL.setCellValueFactory(new PropertyValueFactory<>("inventoryLevel"));
        associatedPartProdcutPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /**
     * Handles the action of saving the product with the provided input values and associated parts.
     *
     * @param event The action event triggered when the "Save" button is clicked.
     */
    @FXML
    private void handleSaveAction(ActionEvent event) {
        // Get input values
        String name = nameTextField.getText();
        String invText = invTextField.getText();
        String priceText = priceTextField.getText();
        String maxText = maxTextField.getText();
        String minText = minTextField.getText();

        // Check for empty fields
        if (name.isEmpty() || invText.isEmpty() || priceText.isEmpty() || maxText.isEmpty() || minText.isEmpty()) {
            showErrorAlert("Please fill in all required fields.");
            return;
        }

        // Parse input values
        int inv;
        double price;
        int max;
        int min;

        try {
            inv = Integer.parseInt(invText);
            price = Double.parseDouble(priceText);
            max = Integer.parseInt(maxText);
            min = Integer.parseInt(minText);
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid input format. Please enter valid numbers for Inventory, Price, Max, and Min.");
            return;
        }

        // Validate Min and Max values
        if (min >= max) {
            showErrorAlert("Min value must be less than Max value.");
            return;
        }

        // Validate Inventory value
        if (inv < min || inv > max) {
            showErrorAlert("Inventory value must be between Min and Max.");
            return;
        }

        // Create a new product
        int id = ++lastUsedId;
        Product product = new Product(id, name, price, inv, min, max);

        // Associate selected parts with the product
        product.addAllAssociatedParts(associatedPartsList);

        // Add the product to inventory
        Inventory.addProduct(product);

        // Navigate to the main screen
        navigateToMainScreen(event);
    }

    /**
     * Handles the action of adding the selected part to the list of associated parts.
     *
     * @param event The action event triggered when the "Add Part" button is clicked.
     */
    @FXML
    private void handleAddPartAction(ActionEvent event) {
        Part selectedPart = partProductTV.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            showErrorAlert("Please select a part to add.");
        } else {
            associatedPartsList.add(selectedPart);
            associatedPartProdcutTV.setItems(FXCollections.observableArrayList(associatedPartsList));
        }
    }

    /**
     * Handles the action of removing the selected associated part from the list.
     *
     * @param event The action event triggered when the "Remove Associated Part" button is clicked.
     */
    @FXML
    private void handleRemoveAssociatedPartAction(ActionEvent event) {
        Part selectedAssociatedPart = associatedPartProdcutTV.getSelectionModel().getSelectedItem();

        if (selectedAssociatedPart == null) {
            showErrorAlert("Please select an associated part to remove.");
        } else {
            associatedPartsList.remove(selectedAssociatedPart);
            associatedPartProdcutTV.setItems(FXCollections.observableArrayList(associatedPartsList));
        }
    }

    /**
     * Navigates to the main screen view when called.
     *
     * @param event The action event triggered when the navigation is initiated.
     */
    private void navigateToMainScreen(ActionEvent event) {
        try {
            // Load main screen layout from FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent mainScreenRoot = fxmlLoader.load();
            Scene mainScreenScene = new Scene(mainScreenRoot);

            // Set main screen layout and show it
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainStage.setScene(mainScreenScene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Error loading the main screen.");// Show error if loading fails
        }
    }

    /**
     * Handles the cancellation of the current creation process and navigates back to the main screen view.
     *
     * @param event The action event triggered when the cancel action is initiated.
     */
    @FXML
    private void handleCancelAction(ActionEvent event) {
        // Show a confirmation alert before canceling
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Confirm Cancel");
        confirmAlert.setContentText("Are you sure you want to cancel? Any unsaved changes will be lost.");

        // Wait for user's response
        Optional<ButtonType> result = confirmAlert.showAndWait();

        // If user confirms, navigate back to the main screen
        if (result.isPresent() && result.get() == ButtonType.OK) {
            navigateToMainScreen(event);
        }
    }

    /**
     * Displays an error alert with the provided message.
     *
     * @param message The error message to be displayed in the alert.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set the alert content to the provided message
        alert.showAndWait(); // Display the alert and wait for user interaction
    }

    /**
     * Handles searching through the list of parts and updates the displayed table accordingly.
     *
     * @param event The event that triggered the search, typically a user action.
     */
    @FXML
    private void handlePartSearch(ActionEvent event) {
        String searchText = partSearchField.getText().trim().toLowerCase();

        // Clear the previous search results
        filteredParts.clear();

        // If the search text is empty, show all parts and return
        if (searchText.isEmpty()) {
            filteredParts.addAll(Inventory.getAllParts());
            partProductTV.setItems(filteredParts);
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
        partProductTV.setItems(filteredParts);

        // Show alert if no matches were found
        if (!foundMatches) {
            showErrorAlert("No matching parts were found for the search.");
        }
    }
}
