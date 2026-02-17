import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {

    @Test
    void 321successfulSearchTest() {
        open("https://www.google.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=search]").shouldHave(text("https://selenide.org"));
    }
    @Test
    void positiveyaserch () {
        open("http://www.ya.ru");
        $("#text").click();
        $("#text").setValue("QA.GURU").pressEnter();
        sleep(2000);
        $(".DistributionButtonClose_view_cross").click();
        sleep(2000);
        $("[id=search-result]").shouldHave(text("Курсы тестировщиков - обучение... | QA.GURU"));
    }
    @Test
    void positiveducksearch () {
        open("https://duckduckgo.com/");
        $("[id=searchbox_input]").setValue("QA.GURU").pressEnter();
        $("[data-testid=result]").shouldHave(text("Курсы Тестировщиков - Обучение Тестированию Онлайн С Нуля | Qa.guru"));
    }
}
