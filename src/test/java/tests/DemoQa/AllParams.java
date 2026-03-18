package tests.DemoQa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class AllParams {
    @BeforeAll
    static void beforeAll () {
            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1080";
        }

    @AfterEach
    void afterEach () {
        closeWebDriver();
    }

    public class TestData {
        // Переменные для TestBoxTests
        public static String site = "https://demoqa.com";
        public static String siteTestBox = "https://demoqa.com/text-box";
        public static String name = "Imya";
        public static String email = "pochta@mail.ru";
        public static String currentAddress = "Adres Doma, k.1";
        public static String permanentAddress = "kv. 228";
        public static String formUrl = "https://demoqa.com/automation-practice-form";

        // Переменные для PracticeForm
        public static String firstName = "Anton";
        public static String lastName = "Baton";
        // public static String email = "ABN@mail.ru";
        public static String wrongEmail = "228";

        public static String phone = "1234567890";
        public static String wrongPhone = "wwwwwwwwww";

        // public static String currentAddress = "SPB, Deb. 4 v 3";
        public static String picturePath = "1.jpg";
        public static String subject = "Maths";
    }
}

