/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 27/02/2023
 * Time: 09:23
 *
 * Project: csci205_labs
 * Package: lab09
 * Class: FileStats
 *
 * Description:
 *
 * *****************************************/
package lab09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;


public class FileStats {
    public static void main(String[] args) {
        Map<Character, Integer> mapChars = new TreeMap<>();
        int numAlpha = 0;
        int numDigits = 0;
        int numWhiteSpace = 0;

        try (InputStream inStream = FileStats.class.getResourceAsStream("/auto-mpg.csv")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

            String sLine = null;
            while ((sLine = reader.readLine())!=null){
                for (int i = 0; i < sLine.length(); i++) {
                    if (Character.isDigit(sLine.charAt(i))) {
                        numDigits++;
                    } else if (Character.isAlphabetic(sLine.charAt(i))) {
                        numAlpha++;
                    } else if (Character.isWhitespace(sLine.charAt(i))) {
                        numWhiteSpace++;
                    } else {
                        mapChars.merge(sLine.charAt(i), 1, (oldValue, notPresentValue) -> oldValue + notPresentValue);
                    }
                }
                numWhiteSpace ++;
                numWhiteSpace ++;
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Results on /auto-mpg.csv");
        System.out.println("Alpha:          " + numAlpha);
        System.out.println("Digit:          " + numDigits);
        System.out.println("Whitespace:     " + numWhiteSpace);
        for (Character key: mapChars.keySet()) {
            System.out.println("Char: " + key +": " + mapChars.get(key));
        }


    }
}
