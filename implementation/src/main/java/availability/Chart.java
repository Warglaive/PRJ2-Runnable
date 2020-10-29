package availability;

import entities.TruckPlan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public class Chart extends GanttChart {

    private NumberAxis xAxis;
    private CategoryAxis yAxis;

    public Chart(NumberAxis xAxis, CategoryAxis yAxis) {
        super(xAxis,yAxis);

        this.xAxis = xAxis;
        this.yAxis = yAxis;

        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);

        setTitle("Overview");
        setLegendVisible(false);
        setBlockHeight( 50);
        getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());
    }

    public void seriesListAdded(ObservableList<Series> seriesObservableList){

        for(int i = 0; i != seriesObservableList.size(); i++){
            seriesAdded(seriesObservableList.get(i), i);
        }
    }

    @Override
    public void seriesAdded(Series series, int seriesIndex) {
        super.seriesAdded(series, seriesIndex);
    }

    public void dataListAdded(ObservableList<XYChart.Data> dataList, Series series){
        for(int i = 0; i != dataList.size(); i++){
            dataItemAdded(series, i, dataList.get(i));
        }
    }

    @Override
    protected void dataItemAdded(Series series, int itemIndex, Data item) {
        super.dataItemAdded(series, itemIndex, item);
    }


    //Takes a list of Truckplans and a series. Inserts data from the truckplans into
    public ObservableList<XYChart.Data> generateDataList(ObservableList<TruckPlan> truckPlanObservableList, XYChart.Series series){

        ObservableList<XYChart.Data> dataList = FXCollections.observableArrayList();
        for(int i = 0; i != truckPlanObservableList.size(); i++){

            XYChart.Data data = new XYChart.Data(truckPlanObservableList.get(i).getPlan().getStarthour(), series.getName(), new GanttChart.ExtraData(truckPlanObservableList.get(i).getPlan().getDuration(), "status-red"));
            dataList.add(data);
        }
        return dataList;
    }
}
