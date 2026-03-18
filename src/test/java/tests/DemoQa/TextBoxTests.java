package tests.DemoQa;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;

public class TextBoxTests extends AllParams {

    @Test
    void successfulFormTest() {
        open(TestData.siteTestBox);

        $("[id=userName]").setValue(TestData.name);
        $("[id=userEmail]").setValue(TestData.email);
        $("[id=currentAddress]").setValue(TestData.currentAddress);
        $("[id=permanentAddress]").setValue(TestData.permanentAddress);

        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text(TestData.name));
        $("[id=output] [id=email]").shouldHave(text(TestData.email));
        $("[id=output] [id=currentAddress]").shouldHave(text(TestData.currentAddress));
        $("[id=output] [id=permanentAddress]").shouldHave(text(TestData.permanentAddress));
    }
}


