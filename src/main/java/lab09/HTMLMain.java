/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 27/02/2023
 * Time: 13:57
 *
 * Project: csci205_labs
 * Package: lab09
 * Class: HTMLMain
 *
 * Description:
 *
 * *****************************************/
package lab09;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLMain {
    private static BufferedInputStream addressIn;
    private static Scanner scnr = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        String address;

        while (true) {
            System.out.print("Enter the url to scan, or Q to quit: ");
            address = scnr.nextLine();


            if ((address.strip().equalsIgnoreCase("q"))) {
                break;
            } else {
                // If the URL is in correct format and can be connected, print tag stats
                if (isValidURLFormat(address)) {
                    if(checkURLConnection(address)) {
                        reportHTMLTagStats();
                    }
                } else {
                    System.out.println("Not a valid URL. Try again.");
                }
            }
        }
    }


    /**
     * Check if the URL provided by user is in valid format
     * @param address URL provided by user
     * @return true if the URL is in correct format
     */
    public static boolean isValidURLFormat(String address) {
        Pattern p = Pattern.compile("^https[s]?://[^\\.\\s]+(\\.[^\\.\\s]+)+$");
        Matcher matcher = p.matcher(address);
        return matcher.matches();
    }



    /**
     * Print the report tag statistics in file or screen
     */
    public static void reportHTMLTagStats() {
        // Create HTMLScanner object
        HTMLScanner tagScanner = new HTMLScanner(addressIn);

        // Print number of tags
        tagScanner.scanForAllTags();
        System.out.println("TOTAL TAG: " + tagScanner.getTotalTagCount());
        System.out.println("TOTAL UNIQUE TAG: " + tagScanner.getTotalUniqueTagCount());

        // Ask user what kind of sort they want (by frequency or by name)
        ReportSortType sortType = null;
        System.out.print("Sort by reverse frequency or by tag name: [f|t]:");
        if (scnr.nextLine().strip().equalsIgnoreCase("f")) {
            sortType = ReportSortType.SORT_BY_TAG_FREQUENCY;
        } else {
            sortType = ReportSortType.SORT_BY_TAG_NAME;
        }

        // Ask user if they want to send report to file or screen
        System.out.print("Send report to a file or screen? [f|s]?");
        if (scnr.nextLine().strip().equalsIgnoreCase("f")) {
            System.out.println("Report sent to htmltag_report.txt");
            tagScanner.printReport(sortType,"f");
        } else {
            tagScanner.printReport(sortType,"s");
        }
    }


    /**
     * Check if the provided URL can be connected and throw exception if the URL is malformed or cannot be connected
     * @param address the input URL
     * @return true if the URL is successfully connected
     * @throws IOException
     */
    public static boolean checkURLConnection(String address) throws IOException {
        boolean isConnected = false;
        try {
            URL locator = new URL(address);
            addressIn = new BufferedInputStream(locator.openStream());
            System.out.println("Connection established!");
            isConnected = true;
        } catch (MalformedURLException e) {
            System.out.println("Not a valid URL. Try again!");
        } catch (UnknownHostException e) {
            System.out.println("Could not identify host!");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find resource on host!");
        } finally {
            return isConnected;
        }

    }

}



