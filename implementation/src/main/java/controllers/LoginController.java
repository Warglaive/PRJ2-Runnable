package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
//import jfxtras.styles.jmetro.JMetro;
//import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    //Fields
    @FXML
    ChoiceBox<String> loginChoiceBox = new ChoiceBox<>();

    @FXML
    Label textfield;

    //TODO put this into an enum or helper class
    private static final Map<String, String> departmentChoice;

    static {
        Map<String, String> bufferChoiceMap = new HashMap<>();
        bufferChoiceMap.put("Owner", "OwnerUI");
        bufferChoiceMap.put("Orders & Invoices", "OrdersInvoicesUI");
        bufferChoiceMap.put("Planning & Operations", "PlanningUI");
        //bufferChoiceMap.put("Truck Driver", "TruckDriverUI");
        bufferChoiceMap.put("Customer", "CustomerUI");
        departmentChoice = Collections.unmodifiableMap(bufferChoiceMap);
    }

    //TODO put this into an enum or helper class
    private static final Map<String, String> departmentChoiceTitle;

    static {
        Map<String, String> bufferChoiceMap = new HashMap<>();
        bufferChoiceMap.put("Owner", "LogistX Planning Software: Owner");
        bufferChoiceMap.put("Orders & Invoices", "LogistX Planning Software: Orders & Invoices");
        bufferChoiceMap.put("Planning & Operations", "LogistX Planning Software: Planning & Operations");
        //bufferChoiceMap.put("Truck Driver", "LogistX Planning Software: Truck Driver");
        bufferChoiceMap.put("Customer", "LogistX Planning Software: Customer");
        departmentChoiceTitle = Collections.unmodifiableMap(bufferChoiceMap);
    }

    //Methods

    /**
     * Method that switches the window as soon as the button is clicked.
     * Also adds the corresponding window title.
     */
    @FXML
    public void loginButtonClicked(ActionEvent event) throws IOException {

        String choice = departmentChoice.get(loginChoiceBox.getValue());

        textfield.setText(choice);
        //Parent guiLoader = FXMLLoader.load(getClass().getResource(choice));
        Scene scene = new Scene(loadFXML(choice));

        // These two lines get the current stage information of the button that is clicked and then closes the current window in favor of the new one opened
        Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisWindow.close();

        // Creates scene for the next window
        Stage window = new Stage();
        window.setScene(scene);
        window.centerOnScreen();
        window.setMaximized(true);

        // Takes the value of the departmentChoiceTitle Map and inserts the title
        window.setTitle(departmentChoiceTitle.get(loginChoiceBox.getValue()));
        window.show();

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(LoginController.class.getClassLoader().getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginChoiceBox.getItems().addAll("Owner", "Orders & Invoices", "Planning & Operations",/* "Truck Driver",*/ "Customer");
        loginChoiceBox.setValue("Planning & Operations");
    }
}