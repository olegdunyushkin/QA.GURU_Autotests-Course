package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.demoqa.helpers.Attach;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = getBrowserVersion();
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        String remoteUrl = System.getProperty("remoteUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        if (!remoteUrl.isBlank()) {
            Configuration.remote = remoteUrl;
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    void addAttachments() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("Скриншот после теста");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            if (Configuration.remote != null) {
                Attach.addVideo();
            }
            closeWebDriver();
        }
    }

    private static String getBrowserVersion() {
        String customBrowserVersion = System.getProperty("customBrowserVersion", "");

        if (!customBrowserVersion.isBlank()) {
            return customBrowserVersion;
        }

        return System.getProperty("browserVersion", "148.0");
    }
}
