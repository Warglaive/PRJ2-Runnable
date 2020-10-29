package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.App;

import java.io.IOException;

public class OwnerController {
    private static Stage stage = new Stage();

    public void logoutButtonClicked(ActionEvent event) throws IOException {
        Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisWindow.close();

        Scene scene = new Scene(loadFXML("LoginUI"));
        stage.setScene(scene);
        stage.setTitle("LogistX Planning Software: Login");
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
