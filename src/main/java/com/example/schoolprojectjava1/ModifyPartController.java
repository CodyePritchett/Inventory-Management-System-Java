package com.example.schoolprojectjava1;

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


/**
 * is the controller for the modify part screen
 */
public class ModifyPartController implements Initializable {


    /**
     * The "Save" button used for saving modifications to the part.
     */
    @FXML
    public Button savePart;

    /**
     * Text field for entering the company name (used for outsourced parts).
     */
    @FXML
    private TextField companyNameTextField;

    /**
     * Text field for entering the machine ID (used for in-house parts).
     */
    @FXML
    private TextField machineIdTextField;

    /**
     * Label displaying the context of the machine ID input (either "Machine ID" or "Company Name").
     */
    @FXML
    private Text machineIdLabel;

    /**
     * Text field for entering the ID of the part.
     */
    @FXML
    private TextField idTextField;

    /**
     * Text field for entering the name of the part.
     */
    @FXML
    private TextField nameTextField;

    /**
     * Text field for entering the inventory level of the part.
     */
    @FXML
    private TextField invTextField;

    /**
     * Text field for entering the price of the part.
     */
    @FXML
    private TextField priceTextField;

    /**
     * Text field for entering the maximum stock level of the part.
     */
    @FXML
    private TextField maxTextField;

    /**
     * Text field for entering the minimum stock level of the part.
     */
    @FXML
    private TextField minTextField;

    /**
     * The currently selected part being modified.
     */
    private Part selectedPart;

    /**
     * Handles the selection of radio buttons for part type (In-House or Outsourced).
     * Adjusts visibility of relevant input fields based on the selected part type.
     *
     * @param event The action event triggered by radio button selection.
     */
    @FXML
    private void handleRadioButtonSelection(ActionEvent event) {
        if (selectedPart instanceof InHouse) {
            // In-House is selected
            machineIdTextField.setVisible(true);
            companyNameTextField.setVisible(false);
            // Update the text next to the text field
            machineIdLabel.setText("Machine ID:");
        } else if (selectedPart instanceof Outsourced) {
            // Outsourced is selected
            companyNameTextField.setVisible(true);
            machineIdTextField.setVisible(false);
            // Update the text next to the text field
            machineIdLabel.setText("Company Name:");
        }
    }

    /**
     * Sets the selected part that will be used to populate the modify part screen fields.
     * Populates the input fields with data from the selected part.
     * Handles additional logic for displaying either InHouse or Outsourced part data.
     *
     * @param part The selected part to be modified.
     */
    public void setSelectedPart(Part part) {
        this.selectedPart = part;
        // Populate the fields with the data of the selected part
        idTextField.setText(String.valueOf(part.getId()));
        nameTextField.setText(part.getName());
        invTextField.setText(String.valueOf(part.getInventoryLevel()));
        priceTextField.setText(String.valueOf(part.getPrice()));
        maxTextField.setText(String.valueOf(part.getMax()));
        minTextField.setText(String.valueOf(part.getMin()));

        // Additional logic for handling either InHouse or Outsourced part data
        if (part instanceof InHouse) {
            InHouse inHousePart = (InHouse) part;
            machineIdTextField.setText(String.valueOf(inHousePart.getMachineId()));
        } else if (part instanceof Outsourced) {
            Outsourced outsourcedPart = (Outsourced) part;
            companyNameTextField.setText(outsourcedPart.getCompanyName());
        }
    }


    /**
     * Handles saving the modifications made to the selected part.
     * Validates input, updates the selected part with new data, and navigates back to the main screen.
     *
     * @param event The action event triggered when the save button is clicked.
     */
    @FXML
    private void handleSaveAction(ActionEvent event) {
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
        int id;
        int inv;
        double price;
        int max;
        int min;

        try {
            id = Integer.parseInt(idTextField.getText());
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

        // Update the selected part with new data
        selectedPart.setId(id);
        selectedPart.setName(name);
        selectedPart.setInventoryLevel(inv);
        selectedPart.setPrice(price);
        selectedPart.setMax(max);
        selectedPart.setMin(min);

        if (selectedPart instanceof InHouse) {
            InHouse inHousePart = (InHouse) selectedPart;
            inHousePart.setMachineId(Integer.parseInt(machineIdTextField.getText()));
        } else if (selectedPart instanceof Outsourced) {
            Outsourced outsourcedPart = (Outsourced) selectedPart;
            outsourcedPart.setCompanyName(companyNameTextField.getText());
        }

        navigateToMainScreen(event);
    }

    /**
     * Navigates to the main screen view.
     *
     * @param event The action event triggered when the navigation is requested.
     */
    private void navigateToMainScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent mainScreenRoot = fxmlLoader.load();
            Scene mainScreenScene = new Scene(mainScreenRoot);

            // Get the current stage (window) from the event and replace its scene with the main screen scene.
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainStage.setScene(mainScreenScene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Error loading the main screen."); // Show an error alert if there's an issue loading the main screen.
        }
    }

    /**
     * Navigates to the main screen view.
     *
     * @param event The action event triggered when the navigation is requested.
     */
    @FXML
    private void handleCancelAction(ActionEvent event) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Confirm Cancel");
        confirmAlert.setContentText("Are you sure you want to cancel? Any unsaved changes will be lost.");

        // Show the confirmation dialog and wait for the user's response.
        Optional<ButtonType> result = confirmAlert.showAndWait();

        // If the user clicked OK in the confirmation dialog, navigate back to the main screen.
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
        // Create and display an error alert dialog.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Initializes the Modify Part screen with data from the selected part.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (selectedPart != null) {
            // Populate the ID and Name fields with the selected part's data
            idTextField.setText(String.valueOf(selectedPart.getId()));
            nameTextField.setText(selectedPart.getName());

            // Depending on the part type, display additional data
            if (selectedPart instanceof InHouse) {
                InHouse inHousePart = (InHouse) selectedPart;
                machineIdTextField.setText(String.valueOf(inHousePart.getMachineId()));
            } else if (selectedPart instanceof Outsourced) {
                Outsourced outsourcedPart = (Outsourced) selectedPart;
                companyNameTextField.setText(outsourcedPart.getCompanyName());
            }
        }
    }
}
