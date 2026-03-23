package tests.demoqa;

import org.junit.jupiter.api.Test;
import tests.demoqa.data.TestData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm extends TestBase {

    @Test
    void requiredFieldsTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(TestData.firstName);
        $("#lastName").setValue(TestData.lastName);

        $("#genterWrapper").$(byText(TestData.gender)).click();
        $("#userNumber").setValue(TestData.phone);

        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("#closeLargeModal").click();
    }

    @Test
    void moveToFormTest() {
        open("/");
        $(byText("Forms")).scrollTo().click();
        $(byText("Practice Form")).click();
        $(".text-center").shouldHave(text("Practice Form"));
    }

    @Test
    void noFieldsTest() {
        open("/automation-practice-form");
        $("#submit").scrollTo().click();

        $("#userForm").shouldHave(cssClass("was-validated"));
        $("#firstName:invalid").shouldBe(visible);
        $("#lastName:invalid").shouldBe(visible);
        $("#userNumber:invalid").shouldBe(visible);
    }

    @Test
    void wrongEmailTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(TestData.firstName);
        $("#lastName").setValue(TestData.lastName);
        $("#genterWrapper").$(byText(TestData.genderFemale)).click();
        $("#userNumber").setValue(TestData.phone);
        $("#userEmail").setValue(TestData.wrongEmail);

        $("#submit").scrollTo().click();
        $("#userEmail:invalid").shouldBe(visible);
    }

    @Test
    void lettersPhoneTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(TestData.firstName);
        $("#lastName").setValue(TestData.lastName);
        $("#genterWrapper").$(byText(TestData.genderOther)).click();
        $("#userNumber").setValue(TestData.wrongPhone);

        $("#submit").scrollTo().click();

        $("#userNumber:invalid").shouldBe(visible);
        $("#userNumber").shouldHave(cssValue("border-top-color", "rgba(220, 53, 69, 1)"));
    }

    @Test
    void maximumHappyPathTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(TestData.firstName);
        $("#lastName").setValue(TestData.lastName);
        $("#userEmail").setValue(TestData.email);
        $("#genterWrapper").$(byText(TestData.gender)).click();
        $("#userNumber").setValue(TestData.phone);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(TestData.birthMonth);
        $(".react-datepicker__year-select").selectOption(TestData.birthYear);

        $(".react-datepicker__day--0" + TestData.birthDay + ":not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue(TestData.subject).pressEnter();

        $("#hobbiesWrapper").$(byText(TestData.hobby1)).click();
        $("#hobbiesWrapper").$(byText(TestData.hobby2)).click();
        $("#hobbiesWrapper").$(byText(TestData.hobby3)).click();

        $("#uploadPicture").uploadFromClasspath(TestData.picturePath);
        $("#currentAddress").setValue(TestData.currentAddress);

        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(TestData.state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(TestData.city)).click();

        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text(TestData.firstName + " " + TestData.lastName));
        $(".table-responsive").shouldHave(text(TestData.email));
        $(".table-responsive").shouldHave(text(TestData.gender));
        $(".table-responsive").shouldHave(text(TestData.phone));

        $(".table-responsive").shouldHave(text(TestData.birthDay + " " + TestData.birthMonth + "," + TestData.birthYear));
        $(".table-responsive").shouldHave(text(TestData.subject));
        $(".table-responsive").shouldHave(text(TestData.hobby1 + ", " + TestData.hobby2 + ", " + TestData.hobby3));
        $(".table-responsive").shouldHave(text(TestData.picturePath));
        $(".table-responsive").shouldHave(text(TestData.currentAddress));
        $(".table-responsive").shouldHave(text(TestData.state + " " + TestData.city));

        $("#closeLargeModal").click();
    }
}