package tests.DemoQa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @Test
        // Заполняем только требуемые поля
    void requiredFields() {
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Imya");
        $("[id=lastName]").setValue("Familya");
        $("[id=gender-radio-1]").click();
        $("[id=userNumber]").setValue("1234567890");
        $("[id=submit]").scrollTo().click();
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));
        $("[id=closeLargeModal]").click();
    }
    @Test
    void moveToForm() {
        open("https://demoqa.com");
        $(byText("Forms")).scrollTo().click();
        $(byText("Practice Form")).click();
        $(".text-center").shouldHave(text("Practice Form"));
        //Можно поставить в шапку теста, если нужно именно перейти с самого начала на нужную форму
    }

    @Test
    void noFields () {
        open("https://demoqa.com/automation-practice-form");
        $("[id=submit]").scrollTo().click();
        $("[id=userForm]").shouldHave(cssClass("was-validated"));
        //$("[id=firstName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)")); мы можем завязаться на то что форма окрасилась в красный цвет (красная рамка)
        //Либо на то что у формы не меняется класс, но меняется стиль который к ней применяется (оба пункта, тот что выше и этот лежат в дев тулз в стилях)
        $("[id=firstName]:invalid").shouldBe(visible);
        $("[id=lastName]:invalid").shouldBe(visible);
        $("[id=userNumber]:invalid").shouldBe(visible);
    }

    @Test
    void wrongEmail () {
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Imya");
        $("[id=lastName]").setValue("Familya");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");
        $("[id=userEmail]").setValue("228"); //Можно еще написать на проверку с сиволами в префиксе, без домена, очень длинное значение
        $("[id=submit]").scrollTo().click();
        $ ("[id=userEmail]:invalid").shouldBe(visible);
    }

    @Test
    void lettersPhone () {
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form"; //Попробовал задать через конфигурацию, по хорошему ее выносим в отдельны файл, как делал в другой форме, и сюда просто ходим через / тк мы уже задали в конфигурации что является корнем
        open("/");
        $("[id=firstName]").setValue("Oleg");
        $("[id=lastName]").setValue("Dan");
        $("[id=gender-radio-3]").click();
        $("[id=userNumber]").setValue("wwwwwwwwww");
        $("[id=submit]").scrollTo().click();
        $("[id=userNumber]:invalid").shouldBe(visible);
        $("[id=userNumber]").shouldHave(cssValue("border-top-color", "rgba(220, 53, 69, 1)")); // Узнал что нет просто border-color, у рамки четыре стороны и у каждой может быть свой цвет, по этому нужно явное указание
    }

    @Test
    void maximumHappyPath () {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";

        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Anton");
        $("[id=lastName]").setValue("Baton");
        $("[id=userEmail]").setValue("ABN@mail.ru");
        $("[for=gender-radio-1]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();
        $("[class=react-datepicker__month-select]").selectOption("October");
        $(".react-datepicker__year-select").selectOption("2000");       //class можно сократить в виде .
        $(".react-datepicker__day--011").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-2]").click();
        $("[for=hobbies-checkbox-3]").click();
        $("#uploadPicture").uploadFromClasspath("1.jpg");
        $("#currentAddress").setValue("SPB, Deb. 4 v 3");

        $("#state").scrollTo().click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Anton Baton"));
        $(".table-responsive").shouldHave(text("ABN@mail.ru"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567890"));
        $(".table-responsive").shouldHave(text("11 October,2000")); // Сайт склеивает дату именно так
        $(".table-responsive").shouldHave(text("Maths")); // Если ты исправил Games на Maths
        $(".table-responsive").shouldHave(text("Sports, Reading, Music")); // Хобби выводятся через запятую
        $(".table-responsive").shouldHave(text("1.jpg"));
        $(".table-responsive").shouldHave(text("SPB, Deb. 4 v 3"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

        $("#closeLargeModal").click();
    }
}

