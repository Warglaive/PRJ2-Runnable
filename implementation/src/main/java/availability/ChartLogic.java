package availability;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import controllers.PlanningController;
import nl.fontys.sebivenlo.dao.DAO;
import nl.fontys.sebivenlo.dao.TransactionToken;

import static logic.App.pdaof;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChartLogic<X, Y> extends GanttChart<X, Y> {

    private DAO<Integer, TruckPlan> tpDAO = pdaof().createDao(TruckPlan.class);
    private DAO<Integer, TrailerPlan> trDAO = pdaof().createDao(TrailerPlan.class);
    private DAO<Integer, DriverPlan> drDAO = pdaof().createDao(DriverPlan.class);
    private ObservableList<XYChart.Series<X, Y>> seriesList = super.getData();

    public ObservableList<Series<X, Y>> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(ObservableList<Series<X, Y>> seriesList) {
        this.seriesList = seriesList;
    }

    public ObservableList<Object> getObjectObservableList() {
        return objectObservableList;
    }

    public void setObjectObservableList(ObservableList<Object> objectObservableList) {
        this.objectObservableList = objectObservableList;
    }

    //Stores all displayed entites as objects in a list
    private ObservableList<Object> objectObservableList = FXCollections.observableArrayList();

    public ChartLogic(Axis<X> xAxis, Axis<Y> yAxis) {
        super(xAxis, yAxis);

        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);

        setTitle("Overview");
        setLegendVisible(false);
        setBlockHeight(50);
        getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());
    }

    //Deletes the series and the plan in plans
    public void deleteInChart(int position) {

        seriesList.remove(position - 1);
        objectObservableList.remove(position - 1);
    }

    //Adds a TruckDriver into the chart, on a selected position
    public void addTruckDriver(TruckDriver truckdriver, LocalDate date, int seriesIndex) {

        ObservableList<DriverPlan> driverPlanObservableList = getDriverPlansAsList(truckdriver, date);
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Truck = " + truckdriver.getId());

        ObservableList<XYChart.Data> dataList = FXCollections.observableArrayList();

        for (int i = 0; i != driverPlanObservableList.size(); i++) {

            XYChart.Data data = new XYChart.Data(driverPlanObservableList.get(i).getPlan().getStarthour(), series.getName(), new GanttChart.ExtraData(driverPlanObservableList.get(i).getPlan().getDuration(), "status-red"));
            dataList.add(data);
            objectObservableList.add(driverPlanObservableList.get(i));
        }

        series.setData(dataList);
        seriesList.add(seriesIndex - 1, series);
        super.setData(seriesList);
    }

    //Adds a TruckDriver into the chart
    public void addTruckDriver(TruckDriver truckdriver, LocalDate date) {

        ObservableList<DriverPlan> driverPlanObservableList = getDriverPlansAsList(truckdriver, date);
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Truck = " + truckdriver.getId());

        ObservableList<XYChart.Data> dataList = FXCollections.observableArrayList();

        for (int i = 0; i != driverPlanObservableList.size(); i++) {

            XYChart.Data data = new XYChart.Data(driverPlanObservableList.get(i).getPlan().getStarthour(), series.getName(), new GanttChart.ExtraData(driverPlanObservableList.get(i).getPlan().getDuration(), "status-red"));
            dataList.add(data);
            objectObservableList.add(driverPlanObservableList.get(i));
        }

        series.setData(dataList);
        super.setData(seriesList);
    }

    //Inserts the driverplans associated with the truckdriver into the chart and the DB
    public void insertTruckDriver(TruckDriver truckdriver, LocalDate date, LocalTime from, LocalTime to) {

        LocalDateTime start = LocalDateTime.of(date, from);
        LocalDateTime end = LocalDateTime.of(date, to);
        LocalDateTimeRange newAppointment = new LocalDateTimeRange(start, end);

        DriverPlan insertDriverPlan = new DriverPlan(drDAO.size() + 1, truckdriver.getId(), newAppointment);

        try (TransactionToken ttok = drDAO.startTransaction()) {

            drDAO.getAll().add(insertDriverPlan);
            ttok.commit();
        } catch (Exception ex) {
            Logger.getLogger(PlanningController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        addTruckDriver(truckdriver, date);
    }

    //Deletes the driverplan associated with the truckdriver in the chart and the DB
    public void deleteTruckDriver(TruckDriver truckdriver, LocalDate date) {

        for (int i = 0; i != objectObservableList.size(); i++) {

            if (objectObservableList.get(i).equals(truckdriver)) {
                deleteInChart(i);
            }
        }

        ObservableList<DriverPlan> driverPlanObservableList = getDriverPlansAsList(truckdriver, date);

        for (int i = 0; i != driverPlanObservableList.size(); i++) {
            drDAO.getAll().remove(driverPlanObservableList.get(i));
        }

    }

    //Takes a given truckdriver and updates it in the chart and in the db
    public void updateTruckDriver(TruckDriver truckdriver, LocalDate date, LocalTime from, LocalTime to, int series, int data) {

        ObservableList<DriverPlan> driverPlanObservableList = getDriverPlansAsList(truckdriver, date);
        DriverPlan selectedDriverPlan = driverPlanObservableList.get(data - 1);
        DriverPlan drInDB = null;

        LocalDateTime start = LocalDateTime.of(date, from);
        LocalDateTime end = LocalDateTime.of(date, to);
        LocalDateTimeRange newAppointment = new LocalDateTimeRange(start, end);

        for (int i = 0; i != drDAO.size(); i++) {

            if (selectedDriverPlan.getId() == drDAO.getAll().get(i).getId()) {
                drInDB = drDAO.getAll().get(i);
            }
        }

        //Update in DB
        selectedDriverPlan.setDriverplanid(drInDB.getId());
        selectedDriverPlan.setPlan(newAppointment);
        drDAO.update(selectedDriverPlan);

        deleteInChart(series);
        addTruckDriver(truckdriver, date);
    }


    //Adds a Trailer into the chart
    public void addTrailer(Trailer trailer, LocalDate date) {

        ObservableList<TrailerPlan> trailerPlanObservableList = getTrailerPlansAsList(trailer, date);
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Truck = " + trailer.getId());

        ObservableList<XYChart.Data> dataList = FXCollections.observableArrayList();

        objectObservableList.add(trailer);

        for (int i = 0; i != trailerPlanObservableList.size(); i++) {

            XYChart.Data data = new XYChart.Data(trailerPlanObservableList.get(i).getPlan().getStarthour(), series.getName(), new GanttChart.ExtraData(trailerPlanObservableList.get(i).getPlan().getDuration(), "status-red"));
            dataList.add(data);

        }

        series.setData(dataList);
        seriesList.add(series);
        super.setData(seriesList);
    }

    //Inserts the trailerplans associated with the trailer into the chart and the DB
    public void insertTrailer(Trailer trailer, LocalDate date, LocalTime from, LocalTime to) {

        LocalDateTime start = LocalDateTime.of(date, from);
        LocalDateTime end = LocalDateTime.of(date, to);
        LocalDateTimeRange newAppointment = new LocalDateTimeRange(start, end);

        TrailerPlan insertTrailerPlan = new TrailerPlan(trDAO.size() + 1, trailer.getId(), newAppointment);

        try (TransactionToken ttok = trDAO.startTransaction()) {

            trDAO.getAll().add(insertTrailerPlan);
            ttok.commit();
        } catch (Exception ex) {
            Logger.getLogger(PlanningController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        addTrailer(trailer, date);
    }

    //Deletes the truckplans associated with the truck in the chart and the DB
    public void deleteTrailer(Trailer trailer, LocalDate date) {

        for (int i = 0; i != objectObservableList.size(); i++) {

            if (objectObservableList.get(i).equals(trailer)) {
                deleteInChart(i);
            }
        }

        ObservableList<TrailerPlan> trailerPlanObservableList = getTrailerPlansAsList(trailer, date);

        for (int i = 0; i != trailerPlanObservableList.size(); i++) {
            tpDAO.getAll().remove(trailerPlanObservableList.get(i));
        }

    }


    public void updateTrailer(Trailer trailer, LocalDate date, LocalTime from, LocalTime to, int series, int data) {

        ObservableList<TrailerPlan> trailerPlanObservableList = getTrailerPlansAsList(trailer, date);
        TrailerPlan selectedTrailerPlan = trailerPlanObservableList.get(data - 1);
        TrailerPlan trInDB = null;
        LocalDateTime start = LocalDateTime.of(date, from);
        LocalDateTime end = LocalDateTime.of(date, to);
        LocalDateTimeRange newAppointment = new LocalDateTimeRange(start, end);

        for (int i = 0; i != trDAO.size(); i++) {

            if (selectedTrailerPlan.getId() == trDAO.getAll().get(i).getId()) {
                trInDB = trDAO.getAll().get(i);
            }
        }

        //Update in DB
        selectedTrailerPlan.setTrailerplanid(trInDB.getId());
        selectedTrailerPlan.setPlan(newAppointment);
        trDAO.update(selectedTrailerPlan);

        deleteInChart(series);
        addTrailer(trailer, date);
    }


    public void addTrailer(Trailer trailer, LocalDate date, int seriesIndex) {

        ObservableList<TrailerPlan> trailerPlanObservableList = getTrailerPlansAsList(trailer, date);
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Truck = " + trailer.getId());

        ObservableList<XYChart.Data> dataList = FXCollections.observableArrayList();

        for (int i = 0; i != trailerPlanObservableList.size(); i++) {

            XYChart.Data data = new XYChart.Data(trailerPlanObservableList.get(i).getPlan().getStarthour(), series.getName(), new GanttChart.ExtraData(trailerPlanObservableList.get(i).getPlan().getDuration(), "status-red"));
            dataList.add(data);
            objectObservableList.add(trailerPlanObservableList.get(i));
        }

        series.setData(dataList);
        seriesList.add(seriesIndex - 1, series);
        super.setData(seriesList);
    }

    //Adds a Truck into the chart, on a selected position
    public void addTruck(Truck truck, LocalDate date, int seriesIndex) {

        ObservableList<TruckPlan> truckPlanObservableList = getTruckPlansAsList(truck, date);
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Truck = " + truck.getId());

        ObservableList<XYChart.Data> dataList = FXCollections.observableArrayList();

        for (int i = 0; i != truckPlanObservableList.size(); i++) {

            XYChart.Data data = new XYChart.Data(truckPlanObservableList.get(i).getPlan().getStarthour(), series.getName(), new GanttChart.ExtraData(truckPlanObservableList.get(i).getPlan().getDuration(), "status-red"));
            dataList.add(data);
            objectObservableList.add(truckPlanObservableList.get(i));
        }

        series.setData(dataList);
        seriesList.add(seriesIndex - 1, series);
        super.setData(seriesList);
    }

    //Adds a Truck into the chart
    public void addTruck(Truck truck, LocalDate date) {

        ObservableList<TruckPlan> truckPlanObservableList = getTruckPlansAsList(truck, date);
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Truck = " + truck.getId());

        ObservableList<XYChart.Data> dataList = FXCollections.observableArrayList();

        objectObservableList.add(truck);

        for (int i = 0; i != truckPlanObservableList.size(); i++) {

            XYChart.Data data = new XYChart.Data(truckPlanObservableList.get(i).getPlan().getStarthour(), series.getName(),
                    new GanttChart.ExtraData(truckPlanObservableList.get(i).getPlan().getDuration(), "status-red"));
            dataList.add(data);

        }

        series.setData(dataList);
        seriesList.add(series);
        super.setData(seriesList);
    }

    //Inserts the truckplans associated with the truck into the chart and the DB
    public void insertTruck(Truck truck, LocalDate date, LocalTime from, LocalTime to) {

        LocalDateTime start = LocalDateTime.of(date, from);
        LocalDateTime end = LocalDateTime.of(date, to);
        LocalDateTimeRange newAppointment = new LocalDateTimeRange(start, end);

        TruckPlan insertTruckPlan = new TruckPlan(tpDAO.size() + 1, truck.getId(), newAppointment);

        try (TransactionToken ttok = tpDAO.startTransaction()) {

            tpDAO.getAll().add(insertTruckPlan);
            ttok.commit();
        } catch (Exception ex) {
            Logger.getLogger(PlanningController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        addTruck(truck, date);
    }

    //Deletes the truckplans associated with the truck in the chart and the DB
    public void deleteTruck(Truck truck, LocalDate date) {

        for (int i = 0; i != objectObservableList.size(); i++) {

            if (objectObservableList.get(i).equals(truck)) {
                deleteInChart(i);
            }
        }

        ObservableList<TruckPlan> truckPlanObservableList = getTruckPlansAsList(truck, date);

        for (int i = 0; i != truckPlanObservableList.size(); i++) {
            tpDAO.getAll().remove(truckPlanObservableList.get(i));
        }

    }

    //Takes a given truck and updates it in the chart and in the db
    public void updateTruck(Truck truck, LocalDate date, LocalTime from, LocalTime to, int series, int data) {

        //Get all truckplans from truck and given date
        ObservableList<TruckPlan> truckPlanObservableList = getTruckPlansAsList(truck, date);
        //Get selected truckplan
        TruckPlan selectedTruckPlan = truckPlanObservableList.get(data - 1);


        //The TruckPlan in the DB
        TruckPlan tpInDB = null;

        LocalDateTime start = LocalDateTime.of(date, from);
        LocalDateTime end = LocalDateTime.of(date, to);
        LocalDateTimeRange newAppointment = new LocalDateTimeRange(start, end);

        //Iterate through truckplan Table and select the truckplan thats in the db
        for (int i = 0; i != tpDAO.size(); i++) {

            if (selectedTruckPlan.getId() == tpDAO.getAll().get(i).getId()) {
                tpInDB = tpDAO.getAll().get(i);
            }
        }

        //Update in DB
        selectedTruckPlan.setTruckplanid(tpInDB.getId());
        selectedTruckPlan.setPlan(newAppointment);
        tpDAO.update(selectedTruckPlan);

        deleteInChart(series);
        addTruck(truck, date);
    }

    //List of all TruckPlans from a given Truck on selected date
    private ObservableList<TruckPlan> getTruckPlansAsList(Truck truck, LocalDate date) {

        ObservableList<TruckPlan> truckPlanList = FXCollections.observableArrayList();
        //Go through TruckPlan Table
        for (int i = 0; i != tpDAO.size(); i++) {

            //If selected Truck is in TruckPlan add it to truckPlanList
            TruckPlan tplist = tpDAO.getAll().get(i);

            if (truck.getId() == tplist.getTruckid() && date.isEqual(tpDAO.getAll().get(i).getPlan().getDate())) {

                truckPlanList.add(tpDAO.getAll().get(i));
            }
        }
        return truckPlanList;
    }

    //List of all TrailerPlans associated with the selected trailer
    private ObservableList<TrailerPlan> getTrailerPlansAsList(Trailer trailer, LocalDate date) {

        ObservableList<TrailerPlan> trailerPlanList = FXCollections.observableArrayList();
        //Go through TruckPlan Table
        for (int i = 0; i != trDAO.size(); i++) {

            //If selected Truck is in TruckPlan add it to truckPlanList
            TrailerPlan trlist = trDAO.getAll().get(i);

            if (trailer.getId() == trlist.getTrailerid() && date.isEqual(trDAO.getAll().get(i).getPlan().getDate())) {

                trailerPlanList.add(trDAO.getAll().get(i));
            }
        }
        return trailerPlanList;
    }

    //List of all DriverPlans associated with the selected truckdriver
    private ObservableList<DriverPlan> getDriverPlansAsList(TruckDriver truckdriver, LocalDate date) {

        ObservableList<DriverPlan> driverPlanList = FXCollections.observableArrayList();
        //Go through TruckPlan Table
        for (int i = 0; i != drDAO.size(); i++) {

            //If selected Truck is in TruckPlan add it to truckPlanList
            DriverPlan trdlist = drDAO.getAll().get(i);

            if (truckdriver.getId() == trdlist.getDriverid() && date.isEqual(drDAO.getAll().get(i).getPlan().getDate())) {

                driverPlanList.add(drDAO.getAll().get(i));
            }
        }
        return driverPlanList;
    }

    // public ObservableList<Series<X, Y>> getSeriesList(){
    //       return seriesList;
//    }
    public ObservableList<Series<X, Y>> setSeriesList(Series toAdd) {
        seriesList.add(toAdd);
        return seriesList;
    }
}
