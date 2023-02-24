/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2022
 *
 * Name: Linh Nguyen
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: HRUtils
 * Description:
 * This is a simple test for utility class that encapsulates some helper methods
 * for our HR system
 * ****************************************
 */
package lab08;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HRUtilsTest {

    /**
     * Confirm that datetoStr indeed takes a {@link java.time.LocalDate} object
     * and returns a standard String as a formatted date
     */
    @Test
    void dateToStr() {
        String sExpected = "2022-09-18";
        LocalDate dateExpected = LocalDate.parse(sExpected);
        String sActual = HRUtils.dateToStr(dateExpected);
        assertEquals(sExpected,sActual);
    }

    /**
     * Confirm that strToDate indeed takes a {@link String} object
     * and returns a standard LocalDate
     */
    @Test
    void strToDate() {
        LocalDate dateExpected = LocalDate.of(2022,9,18);
        String sExpected = "2022-09-18";
        LocalDate dateActual = HRUtils.strToDate(sExpected);
        assertEquals(dateExpected,dateActual);
    }
}