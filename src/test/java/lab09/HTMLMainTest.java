package lab09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static lab09.HTMLMain.isValidURLFormat;
import static org.junit.jupiter.api.Assertions.*;

class HTMLMainTest {
    private String URL;
    private String URL1;
    private String URL2;
    private String validURL;

    @BeforeEach
    void setUp() {
        validURL = "https://www.bucknell.edu";
        URL = "https:\\www.bucknell.edu";
        URL1 = "https://www.eg.bucknell.edu/bad.html";
        URL2 = "https://bucknel.edu";
    }

    /**
     * A test to check for URL format
     */
    @Test
    void isValidURLFormatTest() {
        // Test for incorrect URL format
        assertEquals(false, isValidURLFormat(URL));

        // Test for valid URL format
        assertEquals(true, isValidURLFormat(URL1));
        assertEquals(true, isValidURLFormat(URL2));
        assertEquals(true, isValidURLFormat(validURL));
    }

    /**
     * A test to check for URL connection
     * @throws IOException
     */
    @Test
    void checkURLConnection() throws IOException {
        // Test for invalid URL connection
        assertEquals(false, HTMLMain.checkURLConnection(URL)); // Not a valid URL format
        assertEquals(false, HTMLMain.checkURLConnection(URL1)); // Could not find resources on host
        assertEquals(false, HTMLMain.checkURLConnection(URL2)); // Could not identify host

        // Test for valid URL connection
        assertEquals(true, HTMLMain.checkURLConnection(validURL));

    }


}