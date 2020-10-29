package availability;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public class availabilityBuisness {

    ObservableList<String> seriesNames = FXCollections.observableArrayList(); //Can react to changes made to the list. Helpfull for changing a part
    private String machine;
    private NumberAxis xAxis;
    private CategoryAxis yAxis;
    private GanttChart chart;
    private ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList();

    public availabilityBuisness(NumberAxis xAxis, CategoryAxis yAxis, GanttChart chart){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.chart = chart;

        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);

        chart.setTitle("Overview");
        chart.setLegendVisible(false);
        chart.setBlockHeight( 50);
        chart.getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());
    }

    public ObservableList<XYChart.Series> getSeriesList() {
        return seriesList;
    }

    //Generates a part for insert
    public XYChart.Data<Integer, String> insertData(XYChart.Series series, int begin, long length) {

        XYChart.Data<Integer, String> data = new XYChart.Data<>(begin, series.getName(), new GanttChart.ExtraData( length, "status-red"));
        return data;
    }

    public NumberAxis getxAxis() {
        return xAxis;
    }

    public CategoryAxis getyAxis() {
        return yAxis;
    }

    //Inserts the series List into the Chart
    public GanttChart insertSeries(XYChart.Series series){

        chart.seriesAdded(series, 1);
        //chart.setData(series);
        return chart;
    }


    //Generates a series with a given name
    public XYChart.Series generateSeries(String name){

        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        return series;
    }

    public GanttChart getChart() {
        return chart;
    }
}
