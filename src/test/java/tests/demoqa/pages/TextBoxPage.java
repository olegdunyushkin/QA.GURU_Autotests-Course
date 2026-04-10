package tests.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import tests.demoqa.data.TextBoxTestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    private final SelenideElement fullNameInput = $("#userName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement nameOutput = $("#output #name");
    private final SelenideElement emailOutput = $("#output #email");
    private final SelenideElement currentAddressOutput = $("#output #currentAddress");
    private final SelenideElement permanentAddressOutput = $("#output #permanentAddress");

    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage setFullName(String value) {
        fullNameInput.setValue(value);
        return this;
    }

    public TextBoxPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage submit() {
        submitButton.scrollTo().click();
        return this;
    }

    public TextBoxPage checkSubmittedData(TextBoxTestData testData) {
        nameOutput.shouldHave(text(testData.fullName));
        emailOutput.shouldHave(text(testData.email));
        currentAddressOutput.shouldHave(text(testData.currentAddress));
        permanentAddressOutput.shouldHave(text(testData.permanentAddress));
        return this;
    }
}
