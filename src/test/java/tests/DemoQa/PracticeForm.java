package tests.DemoQa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @Test
        // Заполняем только требуемые поля
    void requiredFieldsTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Imya");
        $("#lastName").setValue("Familya");
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue("1234567890");
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("#closeLargeModal").click();
    }

    @Test
    void moveToFormTest() {
        open("https://demoqa.com");
        $(byText("Forms")).scrollTo().click();
        $(byText("Practice Form")).click();
        $(".text-center").shouldHave(text("Practice Form"));
    }

    @Test
    void noFieldsTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#submit").scrollTo().click();
        $("#userForm").shouldHave(cssClass("was-validated"));
        $("#firstName:invalid").shouldBe(visible);
        $("#lastName:invalid").shouldBe(visible);
        $("#userNumber:invalid").shouldBe(visible);
    }

    @Test
    void wrongEmailTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Imya");
        $("#lastName").setValue("Familya");
        $("[for=gender-radio-2]").click();
        $("#userNumber").setValue("1234567890");
        $("#userEmail").setValue("228");
        $("#submit").scrollTo().click();
        $("#userEmail:invalid").shouldBe(visible);
    }

    @Test
    void lettersPhoneTest() {
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        open("/");
        $("#firstName").setValue("Oleg");
        $("#lastName").setValue("Dan");
        $("[for=gender-radio-3]").click();
        $("#userNumber").setValue("wwwwwwwwww");
        $("#submit").scrollTo().click();
        $("#userNumber:invalid").shouldBe(visible);
        $("#userNumber").shouldHave(cssValue("border-top-color", "rgba(220, 53, 69, 1)"));
    }

    @Test
    void maximumHappyPathTest() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Anton");
        $("#lastName").setValue("Baton");
        $("#userEmail").setValue("ABN@mail.ru");
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("2000");
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
        $(".table-responsive").shouldHave(text("11 October,2000"));
        $(".table-responsive").shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Sports, Reading, Music"));
        $(".table-responsive").shouldHave(text("1.jpg"));
        $(".table-responsive").shouldHave(text("SPB, Deb. 4 v 3"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

        $("#closeLargeModal").click();
    }
}

