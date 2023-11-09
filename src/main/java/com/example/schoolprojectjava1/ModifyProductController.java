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
 * controller for the modify product screen
 */
public class ModifyProductController implements Initializable {

    /**
     * TableView for displaying associated parts in the product modification screen.
     */
    @FXML
    public TableView<Part> associatedPartProdcutTV;

    /**
     * TableColumn displaying the ID of associated parts in the product modification screen.
     */
    @FXML
    public TableColumn<Part, Integer> associatedPartProdcutID;

    /**
     * TableColumn displaying the name of associated parts in the product modification screen.
     */
    @FXML
    public TableColumn<Part, String> associatedPartProdcutName;

    /**
     * TableColumn displaying the inventory level of associated parts in the product modification screen.
     */
    @FXML
    public TableColumn<Part, Integer> associatedPartProdcutIL;

    /**
     * TableColumn displaying the price of associated parts in the product modification screen.
     */
    @FXML
    public TableColumn<Part, Double> associatedPartProdcutPrice;

    /**
     * TableColumn displaying the ID of parts in the add/edit product screen.
     */
    @FXML
    public TableColumn<Part, Integer> partProductID;

    /**
     * TableColumn displaying the name of parts in the add/edit product screen.
     */
    @FXML
    public TableColumn<Part, String> partProductName;

    /**
     * TableColumn displaying the inventory level of parts in the add/edit product screen.
     */
    @FXML
    public TableColumn<Part, Integer> partProductIL;

    /**
     * TableColumn displaying the price of parts in the add/edit product screen.
     */
    @FXML
    public TableColumn<Part, Double> partProductPrice;

    /**
     * TableView for displaying parts in the add/edit product screen.
     */
    @FXML
    public TableView<Part> partProductTV;

    /**
     * TextField used for searching parts.
     */
    @FXML
    public TextField partSearchField;

    /**
     * TextField for displaying and editing the ID of a part.
     */
    @FXML
    public TextField idTextField;

    /**
     * TextField for displaying and editing the name of a part.
     */
    @FXML
    public TextField nameTextField;

    /**
     * TextField for displaying and editing the inventory level of a part.
     */
    @FXML
    public TextField invTextField;

    /**
     * TextField for displaying and editing the price of a part.
     */
    @FXML
    public TextField priceTextField;

    /**
     * TextField for displaying and editing the maximum inventory level of a part.
     */
    @FXML
    public TextField maxTextField;

    /**
     * TextField for displaying and editing the minimum inventory level of a part.
     */
    @FXML
    public TextField minTextField;


    /**
     * Button for saving changes to a part.
     */
    public Button saveButton;

    /**
     * Button for adding a part to the associated parts list of a product.
     */
    public Button addPart;

    /**
     * Button for removing an associated part from the product.
     */
    public Button removeAssociatedPartButton;

    /**
     * Button for searching parts.
     */
    @FXML
    public Button searchButton;

    /**
     * Represents the currently selected product.
     */
    @FXML
    private Product selectedProduct;



    /**
     * Creates an observable list to hold filtered parts
     */
    private ObservableList<Part> filteredParts = FXCollections.observableArrayList();

    /**
     * Creates an observable list to hold filtered parts
     */
    private List<Part> associatedPartsList = new ArrayList<>();

    /**
     * Initializes the part table view by setting up columns and populating it with data from the inventory.
     *
     * @param location   The URL location of the FXML file.
     * @param resources  The resource bundle containing locale-specific resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize columns and bind them to the appropriate properties for the part table view
        partProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partProductIL.setCellValueFactory(new PropertyValueFactory<>("inventoryLevel"));
        partProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Set the items of the part table view to display all parts from the Inventory
        partProductTV.setItems(Inventory.getAllParts());
    }



    /**
     * Handles the action of saving the modifications made to the selected product.
     *
     * @param event The action event triggering this method.
     */
    @FXML
    private void handleSaveAction(ActionEvent event) {
        // Update the selected product with the modified data
        selectedProduct.setName(nameTextField.getText());
        selectedProduct.setInventoryLevel(Integer.parseInt(invTextField.getText()));
        selectedProduct.setPrice(Double.parseDouble(priceTextField.getText()));
        selectedProduct.setMax(Integer.parseInt(maxTextField.getText()));
        selectedProduct.setMin(Integer.parseInt(minTextField.getText()));

        // Add the associated parts to the selected product
        selectedProduct.addAllAssociatedParts(associatedPartsList);

        // Navigate back to the main screen
        navigateToMainScreen(event);
    }



    /**
     * Initializes the "Modify Product" screen with data from the selected product.
     *
     * @param product               The selected product to be modified.
     * @param getAllAssociatedParts List of associated parts with the selected product.
     */
    public void initData(Product product, ObservableList<Part> getAllAssociatedParts) {
        selectedProduct = product;

        // Populate the modify product UI fields
        idTextField.setText(String.valueOf(product.getId()));
        nameTextField.setText(product.getName());
        invTextField.setText(String.valueOf(product.getInventoryLevel()));
        priceTextField.setText(String.valueOf(product.getPrice()));
        maxTextField.setText(String.valueOf(product.getMax()));
        minTextField.setText(String.valueOf(product.getMin()));

        // Populate the associated part table view
        associatedPartProdcutTV.setItems(selectedProduct.getAllAssociatedParts());
        associatedPartProdcutID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartProdcutName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartProdcutIL.setCellValueFactory(new PropertyValueFactory<>("inventoryLevel"));
        associatedPartProdcutPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /**
     * Associates a selected part with the current product and adds it to the associated parts table.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void handleAddPartAction(ActionEvent event) {
        Part selectedPart = partProductTV.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            showErrorAlert("Please select a part to add."); // Show an error if no part is selected
        } else {
            associatedPartsList.add(selectedPart); // Add the selected part to the list of associated parts
            associatedPartProdcutTV.setItems(FXCollections.observableArrayList(associatedPartsList)); // Update the associated parts table view
        }
    }

    /**
     * Removes the selected associated part from the table and un-associates it with the current product.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void handleRemoveAssociatedPartAction(ActionEvent event) {
        Part selectedAssociatedPart = associatedPartProdcutTV.getSelectionModel().getSelectedItem();

        if (selectedAssociatedPart == null) {
            showErrorAlert("Please select an associated part to remove.");
        } else {
            boolean removed = selectedProduct.deleteAssociatedPart(selectedAssociatedPart);

            if (removed) {
                associatedPartProdcutTV.setItems(selectedProduct.getAllAssociatedParts());
            } else {
                showErrorAlert("Failed to remove associated part.");
            }
        }
    }

    /**
     * Handles the search functionality for the parts table.
     *
     * This method filters the parts table based on the search text provided by the user.
     *
     * @param event The action event triggered by the user.
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

    /**
     * Handles the action to navigate back to the main screen.
     *
     * This method loads the main screen's FXML file and sets it as the scene for the main stage.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void navigateToMainScreen(ActionEvent event) {
        try {
            // Load the main screen's FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent mainScreenRoot = fxmlLoader.load();
            Scene mainScreenScene = new Scene(mainScreenRoot);

            // Get the main stage and set the new scene
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainStage.setScene(mainScreenScene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Display an error alert if there's an issue loading the main screen
            showErrorAlert("Error loading the main screen.");
        }
    }

    /**
     * Handles the action to confirm cancellation and navigate back to the main screen.
     *
     * This method displays a confirmation alert to the user, asking for their confirmation to cancel the ongoing action.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void handleCancelAction(ActionEvent event) {
        // Create a confirmation alert to ensure user's intention
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Confirm Cancel");
        confirmAlert.setContentText("Are you sure you want to cancel? Any unsaved changes will be lost.");

        // Show the confirmation alert and wait for user response
        Optional<ButtonType> result = confirmAlert.showAndWait();

        // If the user confirms the cancellation, navigate back to the main screen
        if (result.isPresent() && result.get() == ButtonType.OK) {
            navigateToMainScreen(event);
        }
    }


    /**
     * Displays an error alert dialog with the provided message.
     *
     * @param message The message to be displayed in the error alert.
     */
    private void showErrorAlert(String message) {
        // Create an error alert with a specified title and message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // Clear header text for simplicity
        alert.setContentText(message); // Set the error message

        // Show the error alert and wait for user interaction
        alert.showAndWait();
    }


}
