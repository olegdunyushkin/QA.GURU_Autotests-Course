package guru.qa.demoqa.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    public MainPage openPage() {
        open("/");
        return this;
    }

    public RegistrationFormPage openPracticeFormPage() {
        $(byText("Forms")).scrollTo().click();
        $(byText("Practice Form")).click();
        return new RegistrationFormPage();
    }
}
