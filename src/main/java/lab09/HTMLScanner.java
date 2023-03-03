/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 27/02/2023
 * Time: 13:59
 *
 * Project: csci205_labs
 * Package: lab09
 * Class: HTMLScanner
 *
 * Description:
 *
 * *****************************************/
package lab09;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum ReportSortType {
    SORT_BY_TAG_NAME,
    SORT_BY_TAG_FREQUENCY
}

public class HTMLScanner {

    /** Map of unique tags and their frequency */
    private Map<String, Integer> mapOfTags;

    /** Inputstream of URL provided */
    private InputStream inStream;

    /** Total number of tags in HTML file */
    private int totalTagCount;

    public HTMLScanner(InputStream inStream) {
        this.inStream = inStream;
        this.totalTagCount = 0;
        this.mapOfTags = new TreeMap<>();
    }

    /**
     * @return total number of start name tag
     */
    public int getTotalTagCount() {
        return totalTagCount;
    }


    /**
     * @return number of unique start name tag
     */
    public int getTotalUniqueTagCount() {
        return this.mapOfTags.size();
    }


    /**
     * Scan for all start name tags in the HTML file and count the number of tags and their frequency
     */
    public void scanForAllTags() {
        Scanner scnr = new Scanner(this.inStream);
        Pattern p = Pattern.compile("<\\s*([a-zA-Z]\\w*)(\\s+[a-zA-Z]+\\s*=\\s*(\"[^\"]*\"|'[^']*')*)*\\s*/?>");
        String sMatch;
        while ((sMatch = scnr.findWithinHorizon(p, 0)) != null) {
            MatchResult match = scnr.match();
            this.totalTagCount++; // Increment tag count if a tag is found
            // Add the tag to the map and update its frequency
            this.mapOfTags.merge(match.group(1), 1, (oldValue, notPresentValue) -> oldValue + notPresentValue);
        }
    }


    /**
     * Helper method to sort the tag map by frequency
     * @return the array list of keys in the map that are sorted by its value
     */
    private ArrayList<String> sortByValue() {
        ArrayList<String> sortedKeys = new ArrayList(mapOfTags.keySet());
        Collections.sort(sortedKeys, Comparator.comparing(c -> mapOfTags.get(c)));
        Collections.reverse(sortedKeys);
        return sortedKeys;
    }

    /**
     * Helper method to sort the tag map by tag name
     * @return the array list of keys in the map that are sorted by alphabetical order
     */
    private ArrayList<String> sortByTag() {
        ArrayList<String> sortedKeys = new ArrayList(mapOfTags.keySet());
        Collections.sort(sortedKeys, Comparator.naturalOrder());
        return sortedKeys;
    }


    /**
     * Print tag statistics after being sorted
     * @param sortType the type of sort users want to sort
     * @param filePrint if user wants to print the tag stats in a file
     */
    public void printReport(ReportSortType sortType, String filePrint) {
        // Get the array list of keys (tag names) after being sorted
        ArrayList<String> sortedKeys = new ArrayList<>();
        if (sortType == ReportSortType.SORT_BY_TAG_FREQUENCY) {
            sortedKeys = sortByValue();
        } else if (sortType == ReportSortType.SORT_BY_TAG_NAME) {
            sortedKeys = sortByTag();
        }

        // If user wants the stats to be printed to a file, print it to a file. Otherwise, print it to screen
        if (filePrint.equals("f")) {
            printReportFile(sortedKeys); // Call helper method to print to file
        } else {
            for (String key : sortedKeys) {
                System.out.printf("%s: %d \n", key, this.mapOfTags.get(key));
            }
        }

    }

    /**
     * Helper method to print report to file
     * @param sortedKeys the array list of keys (tags) after being sorted
     */
    private void printReportFile(ArrayList<String> sortedKeys) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/lab09/htmltag_report.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String key : sortedKeys) {
                printWriter.printf("%s: %d \n", key, this.mapOfTags.get(key));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println();
        }
    }
}










