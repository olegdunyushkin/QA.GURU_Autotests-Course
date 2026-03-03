package tests.DemoQa;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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

}

