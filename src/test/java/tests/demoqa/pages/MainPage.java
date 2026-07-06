package tests.demoqa.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    @Step("Открываем главную страницу DemoQA")
    public MainPage openPage() {
        open("/");
        return this;
    }

    @Step("Переходим на страницу Practice Form")
    public RegistrationFormPage openPracticeFormPage() {
        $(byText("Forms")).scrollTo().click();
        $(byText("Practice Form")).click();
        return new RegistrationFormPage();
    }
}
