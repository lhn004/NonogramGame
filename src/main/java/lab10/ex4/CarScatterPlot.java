/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 21/03/2023
 * Time: 10:20
 *
 * Project: csci205_labs
 * Package: lab10.ex4
 * Class: CarScatterPlot
 *
 * Description:
 *
 * *****************************************/
package lab10.ex4;



import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import lab10.Car;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * A class that represents a Scatterplot object, including
 * the data that will be plotted and the axes
 */
public class CarScatterPlot {
    /** Our list of {@link Car} objects */
    private ArrayList<Car> cars;

    /** Two NumberAxis objects, representing each axis in the scatterplot */
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    /** A reference to a ScatterChartinstance, which will encapsulate all the above */
    private ScatterChart<Number, Number> chart;

    /** A series to store data */
    private XYChart.Series<Number,Number> series;



    /**
     * Initialize CarScatterplot to encapsulate the auto-mpg.csv data
     * together with the components needed for a ScatterChart instance
     * @param cars the list of cars whose data will be plotted
     */
    public CarScatterPlot(ArrayList<Car> cars) {
        this.cars = cars;

        // Instantiate the objects needed for our ScatterChart
        this.xAxis = new NumberAxis();
        this.yAxis = new NumberAxis();
        this.series = new XYChart.Series<>();

        // Instantiate a ScatterPlot object
        this.chart = new ScatterChart<>(xAxis,yAxis);

    }

    /**
     * @return chart object
     */
    public ScatterChart<Number, Number> getChart() {
        return chart;
    }

    /**
     * Encapsulate the variable to be plotted into the {@link #series} and then
     * add the series to {@link #chart}
     */
    public void plot(){
        // Set up the title for the chart
        chart.setTitle("Weight vs MPG for automobiles");

        // Set the labels
        xAxis.setLabel("Weight");
        yAxis.setLabel("MPG");

        // Add the data to the series
        cars.stream().forEach(car -> {
                XYChart.Data<Number,Number> datum = new XYChart.Data<>(car.getWeight(),car.getMpg());
                series.getData().add(datum);
        });

        series.setName("series 1");
        chart.getData().add(series);
    }
}


