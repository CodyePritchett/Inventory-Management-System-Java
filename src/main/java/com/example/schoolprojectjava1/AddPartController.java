package com.example.schoolprojectjava1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * is the controller for the add part screen
 */
public class AddPartController {
    /**
     * the save button
     */
    @FXML
    public Button saveButton;
    /**
     * the cancel button
     */
    @FXML
    public Button cancelButton;

    /**
     * text field for name
     */
    @FXML
    private TextField nameTextField;
    /**
     * text field for inventory level
     */
    @FXML
    private TextField invTextField;
    /**
     * text field for price
     */
    @FXML
    private TextField priceTextField;
    /**
     * text field for max
     */
    @FXML
    private TextField maxTextField;
    /**
     * text field for min
     */
    @FXML
    private TextField minTextField;
    /**
     * radio button for in-house
     */
    @FXML
    private RadioButton inHouseRadioButton;
    /**
     * radio button for outsourced
     */
    @FXML
    private RadioButton outsourcedRadioButton;
    /**
     * the text field for company name
     */
    @FXML
    private TextField companyNameTextField;
    /**
     * the text field for machine id
     */
    @FXML
    private TextField machineIdTextField;
    /**
     * the toggle group for company name/machine id
     */
    @FXML
    private ToggleGroup toggleGroup;
    /**
     * the label for machine id
     */
    @FXML
    private Text machineIdLabel;

    /**
     * for generating new ids
     */
    private static int lastUsedId = 0;

    /**
     * Handles radio button selection for part type (In-House or Outsourced).
     *
     * This method is triggered when a radio button is selected from the toggle group. It updates the visibility and text
     *
     * @param event The ActionEvent associated with the radio button selection.
     */
    @FXML
    private void handleRadioButtonSelection(ActionEvent event) {
        // Get the selected radio button from the toggle group
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        // Update UI fields based on selected radio button
        if (selectedRadioButton == outsourcedRadioButton) {
            // Show company name field and hide machine ID field
            companyNameTextField.setVisible(true);
            machineIdTextField.setVisible(false);
            machineIdLabel.setText("Company Name:"); // Update label text
        } else {
            // Show machine ID field and hide company name field
            machineIdTextField.setVisible(true);
            companyNameTextField.setVisible(false);
            machineIdLabel.setText("Machine ID:"); // Update label text
        }
    }


    /**
     * Handles saving the created part.
     * This method gathers input data from text fields and creates a new part based on the selected radio button.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    public void handleSaveAction(ActionEvent event) {
        // Gather input data from text fields
        String name = nameTextField.getText();
        String invText = invTextField.getText();
        String priceText = priceTextField.getText();
        String maxText = maxTextField.getText();
        String minText = minTextField.getText();
        String companyName = companyNameTextField.getText();
        String machineIdText = machineIdTextField.getText();

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
        int machineId = 0;

        try {
            inv = Integer.parseInt(invText);
            price = Double.parseDouble(priceText);
            max = Integer.parseInt(maxText);
            min = Integer.parseInt(minText);
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid input format. Please enter valid numbers for ID, Inventory, Price, Max, and Min.");
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

        // Create the part based on radio button selection
        int id = ++lastUsedId;
        Part part;
        if (outsourcedRadioButton.isSelected()) {
            if (companyName.isEmpty()) {
                showErrorAlert("Please enter the company name.");
                return;
            }
            part = new Outsourced(id, name, price, inv, min, max, companyName);
        } else if (inHouseRadioButton.isSelected()) {
            if (machineIdText.isEmpty()) {
                showErrorAlert("Please enter the machine ID.");
                return;
            }
            try {
                machineId = Integer.parseInt(machineIdText);
            } catch (NumberFormatException e) {
                showErrorAlert("Invalid input format. Please enter a valid number for Machine ID.");
                return;
            }
            part = new InHouse(id, name, price, inv, min, max, machineId);
        } else {
            showErrorAlert("Please select either In-House or Outsourced.");
            return;
        }


        // Add the created part to the inventory
        Inventory.addPart(part);

        // Navigate back to the main screen
        navigateToMainScreen(event);
    }

    /**
     * Navigates back to the main screen.
     *
     * This method is responsible for transitioning the application's user interface back to the main screen
     * @param event The ActionEvent associated with the navigation request.
     */
    private void navigateToMainScreen(ActionEvent event) {
        try {
            // Load the main screen layout from FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent mainScreenRoot = fxmlLoader.load();

            // Create a scene with the main screen content
            Scene mainScreenScene = new Scene(mainScreenRoot);

            // Get the current stage (window) and set the new scene
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainStage.setScene(mainScreenScene);

            // Show the main screen
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Navigates back to the main screen.
     *
     * This method is responsible for transitioning the application's user interface back to the main screen
     *
     * @param event The ActionEvent associated with the navigation request.
     */
    @FXML
    private void handleCancelAction(ActionEvent event) {
        // Show confirmation dialog
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Confirm Cancel");
        confirmAlert.setContentText("Are you sure you want to cancel? Any unsaved changes will be lost.");

        // Wait for user response
        Optional<ButtonType> result = confirmAlert.showAndWait();

        // If user confirmed, navigate back to main screen
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Load main screen and set it as scene
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent mainScreenRoot = fxmlLoader.load();
                Scene mainScreenScene = new Scene(mainScreenRoot);

                // Set the main screen scene and show the stage
                Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainStage.setScene(mainScreenScene);
                mainStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showErrorAlert("Error loading the main screen.");
            }
        } else {
            // User cancelled, do nothing
        }
    }
    /**
     * Shows an error alert dialog with the provided message.
     *
     * This method displays an error alert dialog to the user, containing a brief description of the error or issue.
     * @param message The error message to be displayed in the alert dialog.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set alert content to the message
        alert.showAndWait(); // Display the alert and wait for user response
    }


}
