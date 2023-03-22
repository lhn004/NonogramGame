/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: LINH NGUYEN
 * Date: 03/19/2023
 * Time: 11:26 AM
 *
 * Project: csci205_labs
 * Package: lab10
 * Class: CarAnalyzer
 * Description:
 *
 * ****************************************
 */

package lab10;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A simple checked exception to check for wrong answers from each test below
 */
class CarAnalyzerException extends Exception {
    public CarAnalyzerException(String message) {
        super(message);
    }
}

/**
 * A class that encapsulates a collection of Car instances, a constructor that
 * will handle reading in a dataset of cars (called "auto-mpg.csv"), and
 * a series of tests that the student must complete using the Streams API
 *
 * NOTE - this closely mirrors many of the examples presented in the lecture on
 * the Streams API - WATCH IT FIRST!
 */
public class CarAnalyzer {

    /** The collection of Car instances */
    private final ArrayList<Car> cars;

    /**
     * Construct an instance of CarAnalyzer that takes a filename representing
     * the name of the auto-mpg.csv dataset, and reads in the entire dataset,
     * storing it in our ArrayList<Car> object.
     *
     * @param sFileName is the name of the file, which should be stored
     *                  in the resources folder
     */
    public CarAnalyzer(String sFileName) throws IOException {
        this.cars = new ArrayList<>();
        InputStream inStream = Car.class.getClassLoader().getResourceAsStream(sFileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        LineNumberReader lineNumberReader = new LineNumberReader(bufferedReader);

        String line;
        lineNumberReader.readLine(); // skip the first line
        while ((line = lineNumberReader.readLine()) != null) {
            Car newCar = new Car(line);
            this.cars.add(newCar);
        }

        System.out.printf("Stored %d cars\n", this.cars.size());
    }


    /**
     * test1
     *
     * Print the total number of cars that have four cylinders
     */
    public void test1() throws CarAnalyzerException {
        System.out.println("**** test1() ****");
        System.out.println("==> Number of cars with 4 cylinders");

        long totalWith4cyl = 0;

        totalWith4cyl = this.cars.stream()
                .filter(c -> c.getCylinders() == 4)
                .count();

        System.out.println(totalWith4cyl);
        if (totalWith4cyl != 204) throw new CarAnalyzerException("test1() fail");
    }

    /**
     * test2
     *
     * Count the number of cars that have 4 cylinders and at least 35 mpg
     */
    public void test2() throws CarAnalyzerException {
        System.out.println("**** test2() ****");
        System.out.println("==> Count of cars with 4 cylinders and at least 35 mpg");

        long total = 0;

        total = this.cars.stream()
                .filter(c -> (c.getCylinders() == 4) & (c.getMpg() >= 35))
                .count();


        System.out.println(total);
        if (total != 34) throw new CarAnalyzerException("test2() fail");
    }

    /**
     * test3
     *
     * Print a list of the names of the cars with 4 cylinders and at
     * least 35 mpg. Each car is printed on its own line in the format:
     *
     * name : year=??, mpg=??.?.
     *
     * For example, one line should be:
     * datsun 1200 : Year=71, mpg=35.0
     */
    public void test3() {
        System.out.println("**** test3() ****");
        System.out.println("==> List of cars with 4 cylinders and at least 35 mpg");

       this.cars.stream()
               .filter(c -> (c.getCylinders() == 4) & (c.getMpg() >= 35))
               .forEach((c) -> System.out.println(c.getCarName()));
    }

    /**
     * test4
     *
     * What is the lowest and highest mpg observed in this dataset?
     *
     * HINT - you could do this with two different streams, but it can
     * be done with just one stream and a DoubleSummaryStatistics object.
     */
    public void test4() throws CarAnalyzerException {
        System.out.println("**** test4() ****");
        System.out.println("==> The lowest and highest mpg in this dataset");

        double minMpg;
        double maxMpg;

        minMpg = this.cars.stream()
                        .mapToDouble(Car::getMpg)
                        .min()
                        .getAsDouble();

        maxMpg = this.cars.stream()
                .mapToDouble(Car::getMpg)
                .max()
                .getAsDouble();

        System.out.printf("Min MPG = %.1f, Max MPG = %.1f\n",minMpg,maxMpg);
        if (minMpg != 9 || maxMpg != 46.6) throw new CarAnalyzerException("test4() fail");
    }

    /**
     * test5
     *
     * What is the manufacturer of the car with the highest mpg?
     *
     * NOTE - the manufacturer is the first word in the name field. For example,
     * "plymouth horizon miser" would have a manufacturer of "plymouth"
     */
    public void test5() throws CarAnalyzerException {
        System.out.println("**** test5() ****");
        System.out.println("==> The make of the car with the highest mpg");

        String carBrand = "";

        carBrand = this.cars.stream()
                .sorted(Comparator.comparing(Car::getMpg).reversed())
                .map(c-> c.getCarName().split(" ")[1])
                .findFirst()
                .orElse(null);


        System.out.println(carBrand);
        if (!carBrand.equals("mazda")) throw new CarAnalyzerException("test5() fail");
    }

    /**
     * test6
     *
     * Obtain a list of distinct manufacturer names in the dataset
     * as a List<String> object
     */
    public void test6() throws CarAnalyzerException {
        System.out.println("**** test6() ****");
        System.out.println("==> The list of distinct brand names in the dataset");

        List<String> brands = null;

        brands = this.cars.stream()
                .map((c) -> c.getCarName().split(" ")[1])
                .distinct()
                .collect(Collectors.toList());


        System.out.println(brands);
        System.out.println("Total number of brands: " + brands.size());
        if (brands.size() != 37) throw new CarAnalyzerException("test6() - fail");
    }

    /**
     * test7
     *
     * What is the distribution of cars by cylinder? For this one, you should end up
     * with a Map<<Integer, Long> data collection that has the cylinder value
     * integer as the key, and the number of cars as the value
     */
    public void test7() throws CarAnalyzerException {
        System.out.println("**** test7() ****");
        System.out.println("==> Distribution of number of cars by cylinder");

        Map<Integer,Long> mapOfCars = null;

        mapOfCars = this.cars.stream()
                        .collect(Collectors.groupingBy(c -> c.getCylinders(),Collectors.counting()));

        System.out.println(mapOfCars);
        if (mapOfCars.keySet().size() != 5 || mapOfCars.get(3) != 4 || mapOfCars.get(4) != 204)
            throw new CarAnalyzerException("test7() - fail");
    }

    /**
     * test8
     *
     * Print out the name, year, cylinder and mpg of all Volvo's in the dataset. Each
     * car should be printed on its own line
     *
     * For example, one line should be:
     * "volvo 145e (sw) year=72 cyl=4 mpg=18.0"
     */
    public void test8() {
        System.out.println("**** test8() ****");
        System.out.println("==> Model year cylinder and mpg of all Volvo's");

        this.cars.stream()
                .filter(c -> c.getCarName().contains("volvo"))
                .forEach(c -> System.out.printf("%s year=%d cyl=%d mpg=%.1f \n",c.getCarName(),c.getModelYear(),c.getCylinders(), c.getMpg()));
    }

    /**
     * test9
     *
     * Print the number of cars by manufacturer to the output. This should result
     * in a list printed with the manufacturer, and the count.
     *
     * You should print in the following format:
     * manufacturer --> count
     *
     * For example:
     * vw --> 6
     * buick --> 17
     * mercury --> 11
     * ...
     *
     */
    public void test9() {
        System.out.println("**** test9() ****");
        System.out.println("==> Print the number of cars by manufacturer:");

        this.cars.stream()
                .collect(Collectors.groupingBy(c -> c.getCarName().split(" ")[1], Collectors.counting()))
                .forEach((key,val)-> System.out.printf("%s -> %d\n",key,val));

    }

    /**
     * test10
     *
     * Which car manufacturer has the largest number of cars in the dataset?
     */
    public void test10() throws CarAnalyzerException {
        System.out.println("**** test10() ****");
        System.out.println("==> Car manufacturer with the largest number of cars in the dataset");

        String carName = "";

        carName = this.cars.stream()
                .collect(Collectors.groupingBy(c -> c.getCarName().split(" ")[1], Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .findFirst().orElse(null).getKey();

        System.out.println(carName);
        if (!carName.equals("ford"))
            throw new CarAnalyzerException("test10() - fail");
    }

    /**
     * A main program to run all of the tests. It checks ofr the occurrence of
     * a CarAnalyzerException and will stop tests if one occurrs
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        CarAnalyzer analyzer = new CarAnalyzer("auto-mpg.csv");
        try {
            analyzer.test1();
            analyzer.test2();
            analyzer.test3();
            analyzer.test4();
            analyzer.test5();
            analyzer.test6();
            analyzer.test7();
            analyzer.test8();
            analyzer.test9();
            analyzer.test10();
        }
        catch (CarAnalyzerException e) {
            System.err.println(e.getMessage());
        }
    }

}
