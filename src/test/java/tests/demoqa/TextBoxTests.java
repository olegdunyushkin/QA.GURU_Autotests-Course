package tests.demoqa;

import org.junit.jupiter.api.Test;
import tests.demoqa.data.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFormTest() {
        open("/text-box");

        $("#userName").setValue(TestData.name);
        $("#userEmail").setValue(TestData.email);
        $("#currentAddress").setValue(TestData.currentAddress);
        $("#permanentAddress").setValue(TestData.permanentAddress);

        $("#submit").scrollTo().click();

        $("#output #name").shouldHave(text(TestData.name));
        $("#output #email").shouldHave(text(TestData.email));
        $("#output #currentAddress").shouldHave(text(TestData.currentAddress));
        $("#output #permanentAddress").shouldHave(text(TestData.permanentAddress));
    }
}