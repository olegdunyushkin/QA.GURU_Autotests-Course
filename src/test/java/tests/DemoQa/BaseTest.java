package tests.DemoQa;

//Сюда мы можем вынесли всю конфигурацию из тестов и потом просто подключать его одной строкой куда надо

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.Closeable;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome"; //Будет запущен голый браузер, без расширений (в тч блока рекламы)
        //Configuration.browserVersion = "144.0"; Если тестим на специфичесской
        Configuration.browserSize = "1920x1080";
        // Configuration.baseUrl = "https://demoqa.com"; Сюда выносим базовый адрес, а внизу идут уже от него переходы на
        // нужную страницу (в самом тесте). Это удобно если у нас несколько тестовых сред, и можем просто менять базовые адреса.
        // Configuration.pageLoadStrategy = "eager"; Если плохо грузится или селенид капризничает, лучше таким не пользоваться
        // Configuration.timeout = "10000"; По дефолту 4 секунды, нужно вынести сюда чем каждому сувать слипы по 4секунды и тд,
        // те выносим в общее ожидание если нужно для все, или в частное если для какого то, но не суем в сам тест
    }

    @AfterEach //В конце теста закрывает все драйверы, тянется сразу из зависимости для всех
    void afterEach () {
        closeWebDriver();
    }
}