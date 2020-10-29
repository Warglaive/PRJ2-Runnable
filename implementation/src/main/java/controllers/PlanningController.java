package controllers;

import entities.*;
import availability.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimePicker;
import logic.App;
import nl.fontys.sebivenlo.dao.DAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import static logic.App.pdaof;

public class PlanningController implements Initializable {
    @FXML ChoiceBox<String> requestOpChoiceBox = new ChoiceBox<>();
    @FXML ChoiceBox<String> ordersOpChoiceBox = new ChoiceBox<>();
    @FXML ChoiceBox<String> invoicesOpChoiceBox = new ChoiceBox<>();
    @FXML private Pane paneThePane;
    @FXML private TableView<Truck> truckview;
    @FXML private TableColumn<Truck, Integer> truckIdColumn;
    @FXML private TableColumn<Truck, String> truckLicenseplateColumn;
    @FXML private TableColumn<Truck, Integer> truckMaxloadweightColumn;
    @FXML private TableColumn<Truck, Integer> truckMileageColumn;
    @FXML private TableColumn<Truck, Integer> truckPowerColumn;
    @FXML private TableColumn<Truck, LocalDate> truckApkColumn;
    @FXML private TableView<Trailer> trailerview;
    @FXML private TableColumn<Trailer, Integer> trailerIdColumn;
    @FXML private TableColumn<Trailer, String> trailerLicenseplate;
    @FXML private TableColumn<Trailer, Integer> trailerMaxloadweight;
    @FXML private TableView<TruckDriver> driverview;
    @FXML private TableColumn<TruckDriver, Integer> driverID;
    @FXML private TableColumn<TruckDriver, Integer> driverEmployeeId;
    @FXML private TableColumn<TruckDriver, LocalDate> driverLicensevalidtill;
    @FXML private DatePicker datePicker;
    @FXML private LocalTimePicker from;
    @FXML private LocalTimePicker to;
    @FXML private TextField seriesIdSelector;
    @FXML private TextField dataItemIdSelector;

    //Create the DAOs
    private DAO<Integer, Truck> truckDAO = App.pdaof.createDao(Truck.class);
    private DAO<Integer, Trailer> trailerDAO = App.pdaof.createDao(Trailer.class);
    private DAO<Integer, TruckDriver> truckdriverDAO = App.pdaof.createDao(TruckDriver.class);

    //The List Contains the informations the second one displays them
    private List<Truck> truckList = truckDAO.getAll();
    private ObservableList<Truck> truckObservableList = FXCollections.observableArrayList();
    private List<Trailer> trailerList = trailerDAO.getAll();
    private ObservableList<Trailer> trailerObservableList = FXCollections.observableArrayList();
    private List<TruckDriver> truckDriverList = truckdriverDAO.getAll();
    private ObservableList<TruckDriver> truckDriverObservableList = FXCollections.observableArrayList();

    //Create and insert Chart
    private NumberAxis xAxis = new NumberAxis(1, 24, 1);
    private CategoryAxis yAxis = new CategoryAxis();

    private ChartLogic chartLogic = new ChartLogic(xAxis, yAxis);

    private int seriesIdSelectorInt;
    private int dataItemIdSelectorInt;

    /**
     * Method that switches the scene as soon as the button is clicked
     */
    public void logoutButtonClicked(ActionEvent event) throws IOException {
        System.out.println("User logged out...");
        Parent OrdersInvoicesParent = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
        Scene OrdersInvoicesScene = new Scene(OrdersInvoicesParent);

        //This line gets the stage information of the button that is clicked
        Stage thisWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisWindow.close();

        //TODO: Make stage bigger
        Stage window = new Stage();

        //Creates scene for the next window
        window.setScene(OrdersInvoicesScene);
        window.centerOnScreen();
        window.setTitle("LogistX Planning Software: Login");
        window.setResizable(false);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Adding elements to the choiceBox of the "Offer Requests" tab
        requestOpChoiceBox.getItems().addAll("Accept", "Reject");
        requestOpChoiceBox.setValue("Accept");

        // Adding elements to the choiceBox of the "Orders" tab
        ordersOpChoiceBox.getItems().addAll("Accept1", "Reject1");
        ordersOpChoiceBox.setValue("Accept1");

        //Adding elements to the choiceBox of the "Invoices" tab
        invoicesOpChoiceBox.getItems().addAll("Accept2", "Reject2");
        invoicesOpChoiceBox.setValue("Accept2");

        //Bind the Columns
        truckIdColumn.setCellValueFactory(new PropertyValueFactory<Truck, Integer>("id"));
        truckLicenseplateColumn.setCellValueFactory(new PropertyValueFactory<Truck, String>("licenseplate"));
        truckMaxloadweightColumn.setCellValueFactory(new PropertyValueFactory<Truck, Integer>("maxloadweight"));
        truckMileageColumn.setCellValueFactory(new PropertyValueFactory<Truck, Integer>("hasmileage"));
        truckPowerColumn.setCellValueFactory(new PropertyValueFactory<Truck, Integer>("haspower"));
        truckApkColumn.setCellValueFactory(new PropertyValueFactory<Truck, LocalDate>("apkdate"));

        trailerIdColumn.setCellValueFactory(new PropertyValueFactory<Trailer, Integer>("id"));
        trailerLicenseplate.setCellValueFactory(new PropertyValueFactory<Trailer, String>("licenseplate"));
        trailerMaxloadweight.setCellValueFactory(new PropertyValueFactory<Trailer, Integer>("maxloadweight"));

        driverID.setCellValueFactory(new PropertyValueFactory<TruckDriver, Integer>("id"));
        driverEmployeeId.setCellValueFactory(new PropertyValueFactory<TruckDriver, Integer>("employeeid"));
        driverLicensevalidtill.setCellValueFactory(new PropertyValueFactory<TruckDriver, LocalDate>("licensevalidtill"));

        //Inserts the informations to the tableview
        truckview.setItems(display(truckList, truckObservableList));
        trailerview.setItems(display(trailerList, trailerObservableList));
        driverview.setItems(display(truckDriverList, truckDriverObservableList));
    }

    /*
    This Button inserts the given Truck into the Chart
     */
    public void truckInserCharttButton(ActionEvent event) throws Exception {

        //connect selected row to plan
        Truck truck = truckview.getSelectionModel().getSelectedItem();

        chartLogic.addTruck(truck, datePicker.getValue());

        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    /*
    This Button updates the given Truck in the Chart
     */
    @FXML
    void truckUpdateButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        Truck truck = truckview.getSelectionModel().getSelectedItem();

        seriesIdSelectorInt = Integer.parseInt(seriesIdSelector.getText());
        dataItemIdSelectorInt = Integer.parseInt(dataItemIdSelector.getText());

        chartLogic.updateTruck(truck, datePicker.getValue() , from.getLocalTime(), to.getLocalTime(), seriesIdSelectorInt, dataItemIdSelectorInt);

        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    //Deletes the selected truck from the chart and the DB
    @FXML
    void truckDeleteButton(ActionEvent event) throws Exception {
        //connect selected row to plan
        Truck truck = truckview.getSelectionModel().getSelectedItem();

        chartLogic.deleteTruck(truck, datePicker.getValue());
        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    //Removes the selected truck, trailer or driver from the chart
    @FXML
    void removeButton(ActionEvent event) {

        seriesIdSelectorInt = Integer.parseInt(seriesIdSelector.getText());
        chartLogic.deleteInChart(seriesIdSelectorInt);
    }

    @FXML
    void truckInsertButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        Truck truck = truckview.getSelectionModel().getSelectedItem();

        chartLogic.insertTruck(truck, datePicker.getValue(), from.getLocalTime(), to.getLocalTime());
        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    @FXML
    void driverDeleteButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        TruckDriver truckDriver = driverview.getSelectionModel().getSelectedItem();

        chartLogic.deleteTruckDriver(truckDriver, datePicker.getValue());
        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    @FXML
    void driverInserCharttButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        TruckDriver truckDriver = driverview.getSelectionModel().getSelectedItem();

        chartLogic.addTruckDriver(truckDriver, datePicker.getValue());

        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    @FXML
    void driverInsertButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        TruckDriver truckDriver = driverview.getSelectionModel().getSelectedItem();

        chartLogic.insertTruckDriver(truckDriver, datePicker.getValue(), from.getLocalTime(), to.getLocalTime());
        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    @FXML
    void driverUpdateButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        TruckDriver truckDriver = driverview.getSelectionModel().getSelectedItem();

        seriesIdSelectorInt = Integer.parseInt(seriesIdSelector.getText());
        dataItemIdSelectorInt = Integer.parseInt(dataItemIdSelector.getText());

        chartLogic.updateTruckDriver(truckDriver, datePicker.getValue() , from.getLocalTime(), to.getLocalTime(), seriesIdSelectorInt, dataItemIdSelectorInt);

        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    @FXML
    void trailerDeleteButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        Trailer trailer = trailerview.getSelectionModel().getSelectedItem();

        chartLogic.deleteTrailer(trailer, datePicker.getValue());
        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    @FXML
    void trailerInserCharttButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        Trailer trailer = trailerview.getSelectionModel().getSelectedItem();

        chartLogic.addTrailer(trailer, datePicker.getValue());

        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    @FXML
    void trailerInsertButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        Trailer trailer = trailerview.getSelectionModel().getSelectedItem();

        chartLogic.insertTrailer(trailer, datePicker.getValue(), from.getLocalTime(), to.getLocalTime());

        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    @FXML
    void trailerUpdateButton(ActionEvent event) throws Exception{

        //connect selected row to plan
        Trailer trailer = trailerview.getSelectionModel().getSelectedItem();

        seriesIdSelectorInt = Integer.parseInt(seriesIdSelector.getText());
        dataItemIdSelectorInt = Integer.parseInt(dataItemIdSelector.getText());

        chartLogic.updateTrailer(trailer, datePicker.getValue() , from.getLocalTime(), to.getLocalTime(), seriesIdSelectorInt, dataItemIdSelectorInt);

        if(!paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().clear();
            paneThePane.getChildren().add(chartLogic);
        }else if (paneThePane.getChildren().isEmpty()){

            paneThePane.getChildren().add(chartLogic);
        }else{
            throw new Exception("There is no pane");
        }
    }

    //Iterates through given lists. Used to display the lists
    private ObservableList display(List first, ObservableList second){
        //take everything from the list and insert it to the observable list
        for(int i = 0; i< first.size(); i++){
            second.add(first.get(i));
        }
        return second;                                               //This solution isnt very nice but there is not much time left
    }
}
