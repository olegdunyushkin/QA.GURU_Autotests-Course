package tests.DemoQa;

import org.junit.jupiter.api.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void firstConfig() {
        System.out.println("Zaveli obschi config");
    }

    @AfterAll
    static void end () {
        System.out.println("Vse!");

    }
    @BeforeEach
    void startBrowser () {
        System.out.println("Browser Poehal");
    }

    @AfterEach
    void closeBrowser () {
        System.out.println("Zakrili browser");
    }

    @Test
    void firstTest() {
        System.out.println("Perviy");
    }
}