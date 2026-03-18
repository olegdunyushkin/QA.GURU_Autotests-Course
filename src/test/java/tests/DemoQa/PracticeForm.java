package tests.DemoQa;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm extends AllParams {

    @Test
    void requiredFields() {
        open(TestData.formUrl);

        $("[id=firstName]").setValue(TestData.firstName);
        $("[id=lastName]").setValue(TestData.lastName);
        $("[for=gender-radio-1]").click();
        $("[id=userNumber]").setValue(TestData.phone);

        $("[id=submit]").scrollTo().click();
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));
        $("[id=closeLargeModal]").click();
    }

    @Test
    void moveToForm() {
        open(TestData.site);
        $(byText("Forms")).scrollTo().click();
        $(byText("Practice Form")).click();
        $(".text-center").shouldHave(text("Practice Form"));
    }

    @Test
    void noFields() {
        open(TestData.formUrl);
        $("[id=submit]").scrollTo().click();

        $("[id=userForm]").shouldHave(cssClass("was-validated"));
        $("[id=firstName]:invalid").shouldBe(visible);
        $("[id=lastName]:invalid").shouldBe(visible);
        $("[id=userNumber]:invalid").shouldBe(visible);
    }

    @Test
    void wrongEmail() {
        open(TestData.formUrl);

        $("[id=firstName]").setValue(TestData.firstName);
        $("[id=lastName]").setValue(TestData.lastName);
        $("[for=gender-radio-2]").click();
        $("[id=userNumber]").setValue(TestData.phone);
        $("[id=userEmail]").setValue(TestData.wrongEmail);

        $("[id=submit]").scrollTo().click();
        $("[id=userEmail]:invalid").shouldBe(visible);
    }

    @Test
    void lettersPhone() {
        open(TestData.formUrl);

        $("[id=firstName]").setValue(TestData.firstName);
        $("[id=lastName]").setValue(TestData.lastName);
        $("[for=gender-radio-3]").click();
        $("[id=userNumber]").setValue(TestData.wrongPhone);

        $("[id=submit]").scrollTo().click();

        $("[id=userNumber]:invalid").shouldBe(visible);
        $("[id=userNumber]").shouldHave(cssValue("border-top-color", "rgba(220, 53, 69, 1)"));
    }

    @Test
    void maximumHappyPath() {
        open(TestData.formUrl);

        $("[id=firstName]").setValue(TestData.firstName);
        $("[id=lastName]").setValue(TestData.lastName);
        $("[id=userEmail]").setValue(TestData.email);
        $("[for=gender-radio-1]").click();
        $("[id=userNumber]").setValue(TestData.phone);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--011").click();

        $("#subjectsInput").setValue(TestData.subject).pressEnter();

        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-2]").click();
        $("[for=hobbies-checkbox-3]").click();

        $("#uploadPicture").uploadFromClasspath(TestData.picturePath);
        $("#currentAddress").setValue(TestData.currentAddress);

        $("#state").scrollTo().click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text(TestData.firstName + " " + TestData.lastName));
        $(".table-responsive").shouldHave(text(TestData.email));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text(TestData.phone));
        $(".table-responsive").shouldHave(text("11 October,2000"));
        $(".table-responsive").shouldHave(text(TestData.subject));
        $(".table-responsive").shouldHave(text("Sports, Reading, Music"));
        $(".table-responsive").shouldHave(text(TestData.picturePath));
        $(".table-responsive").shouldHave(text(TestData.currentAddress));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

        $("#closeLargeModal").click();
    }
}