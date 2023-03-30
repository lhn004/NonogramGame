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

    /** A reference to XYChart.Series <Number, Number> which will encapsulate the actual data that will be plotted */
    private XYChart.Series<Number, Number> series;
    private XYChart.Series<Number, Number> seriesWeight;
    private XYChart.Series<Number, Number> seriesCld;
    private XYChart.Series<Number, Number> seriesHrp;
    private XYChart.Series<Number, Number> seriesDplm;
    private XYChart.Series<Number, Number> seriesAcc;


    /** A reference to a ScatterChartinstance, which will encapsulate all the above */
    private ScatterChart<Number, Number> chart;



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
     * Encapsulate the variables to be plotted into the {@link #series} and then
     * add the series to our {@link #chart}
     */
    public void plot(Button_Click btnclick){
        plotLabel(btnclick);
        getData(btnclick);
    }


    /**
     * Add data to be plotted based on the button clicked
     * @param btnclick - the variable that is clicked to be plotted
     */
    private void getData(Button_Click btnclick) {
        XYChart.Series<Number, Number> seriesPlot = new XYChart.Series<>();
        // Add the data to the series
        if (btnclick == Button_Click.BTNACC){
            cars.stream()
                    .forEach(car -> {
                        XYChart.Data<Number,Number> datum = new XYChart.Data<>(car.getAccel(),car.getMpg());
                        series.getData().add(datum);
                    });
        } else if (btnclick == Button_Click.BTNCLD) {
            cars.stream()
                    .forEach(car -> {
                        XYChart.Data<Number,Number> datum = new XYChart.Data<>(car.getCylinders(),car.getMpg());
                        series.getData().add(datum);
                    });
        } else if (btnclick == Button_Click.BTNDPLM) {
            cars.stream()
                    .forEach(car -> {
                        XYChart.Data<Number,Number> datum = new XYChart.Data<>(car.getDisplacement(),car.getMpg());
                        series.getData().add(datum);
                    });
        } else if (btnclick == Button_Click.BTNHRP) {
            cars.stream()
                    .forEach(car -> {
                        XYChart.Data<Number,Number> datum = new XYChart.Data<>(car.getHorsepower(),car.getMpg());
                        series.getData().add(datum);
                    });
        } else {
            cars.stream()
                    .forEach(car -> {
                        XYChart.Data<Number,Number> datum = new XYChart.Data<>(car.getWeight(),car.getMpg());
                        series.getData().add(datum);
                    });
        }

        series.setName("Series 1");
        chart.getData().add(series);
    }


    /**
     * Set title and label of the scatterplot
     * @param btnclick button to choose which variable is plotted
     */
    private void plotLabel(Button_Click btnclick) {
        // Set the title for the chart
        if (btnclick == Button_Click.BTNCLD) {
            chart.setTitle("Cylinder vs MPG for automobiles");
            xAxis.setLabel("Cylinder");
        } else if (btnclick == Button_Click.BTNACC){
            chart.setTitle("Acceleration vs MPG for automobiles");
            xAxis.setLabel("Acceleration");
        } else if ( btnclick == Button_Click.BTNDPLM) {
            chart.setTitle("Displacement vs MPG for automobiles");
            xAxis.setLabel("Displacement");
        } else if (btnclick == Button_Click.BTNHRP) {
            chart.setTitle("Horsepower vs MPG for automobiles");
            xAxis.setLabel("Horsepower");
        } else {
            chart.setTitle("Weight vs MPG for automobiles");
            xAxis.setLabel("Weights");
        }

        // Set the labels
        yAxis.setLabel("MPG");
    }

}


