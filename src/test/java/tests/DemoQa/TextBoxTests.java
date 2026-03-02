package tests.DemoQa;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebElementCondition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TextBoxTests extends BaseTest { //Через extends подключили отдельный файл с сетапом, подключаем далее куда нужно

    //@BeforeAll - мы вынесли его в отдельный файл, который подключаем. Но можем раскомитеть и будет тут, но если тестов станет много, то будет слишком массивно
    //static void beforeAll () {
    //Configuration.browser = "chrome";
    //Configuration.browserVersion = "144.0"; Если тестим на специфичесской
    //Configuration.browserSize = "1920x1080";
    // Configuration.baseUrl = "https://demoqa.com"; Сюда выносим базовый адрес, а внизу идут уже от него переходы на
    // нужную страницу (в самом тесте). Это удобно если у нас несколько тестовых сред, и можем просто менять базовые адреса.
    // Configuration.pageLoadStrategy = "eager"; Если плохо грузится или селенид капризничает, лучше таким не пользоваться
    // Configuration.timeout = "10000"; По дефолту 4 секунды, нужно вынести сюда чем каждому сувать слипы по 4секунды и тд,
    // те выносим в общее ожидание если нужно для все, или в частное если для какого то, но не суем в сам тест

    @Test
    void succesfullFormTest() {
        open("https://demoqa.com/text-box");
        $("[id=userName]").setValue("Imya");
        $("[id=userEmail]").setValue("pochta@mail.ru");
        $("[id=currentAddress]").setValue("Adres Doma, k.1");
        $("[id=permanentAddress]").setValue("kv. 228");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Imya"));
        $("[id=output] [id=email]").shouldHave(text("pochta@mail.ru"));
        $("[id=output] [id=currentAddress]").shouldHave(text("Adres Doma, k.1"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("kv. 228"));
    }
}


