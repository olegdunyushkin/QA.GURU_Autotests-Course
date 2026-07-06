package tests.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.demoqa.pages.components.CalendarComponent;
import tests.demoqa.pages.components.ResultsTableComponent;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormPage {

    private final SelenideElement pageTitle = $(".text-center");
    private final SelenideElement form = $("#userForm");
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement phoneInput = $("#userNumber");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement pictureUpload = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateInput = $("#state");
    private final SelenideElement cityInput = $("#city");
    private final SelenideElement stateCityWrapper = $("#stateCity-wrapper");
    private final SelenideElement submitButton = $("#submit");

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    @Step("Открываем страницу формы регистрации")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Проверяем заголовок страницы: {expectedTitle}")
    public RegistrationFormPage checkPageTitle(String expectedTitle) {
        pageTitle.shouldHave(text(expectedTitle));
        return this;
    }

    @Step("Вводим имя: {value}")
    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Вводим фамилию: {value}")
    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Вводим email: {value}")
    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Выбираем gender: {value}")
    public RegistrationFormPage selectGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Вводим телефон: {value}")
    public RegistrationFormPage setPhone(String value) {
        phoneInput.setValue(value);
        return this;
    }

    @Step("Выбираем дату рождения: {day} {month} {year}")
    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Вводим subject: {value}")
    public RegistrationFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбираем hobbies: {values}")
    public RegistrationFormPage selectHobbies(List<String> values) {
        for (String value : values) {
            hobbiesWrapper.$(byText(value)).click();
        }
        return this;
    }

    @Step("Загружаем картинку: {picturePath}")
    public RegistrationFormPage uploadPicture(String picturePath) {
        pictureUpload.uploadFromClasspath(picturePath);
        return this;
    }

    @Step("Вводим адрес: {value}")
    public RegistrationFormPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    @Step("Выбираем state и city: {state}, {city}")
    public RegistrationFormPage setStateAndCity(String state, String city) {
        stateInput.scrollTo().click();
        stateCityWrapper.$(byText(state)).click();
        cityInput.click();
        stateCityWrapper.$(byText(city)).click();
        return this;
    }

    @Step("Отправляем форму")
    public RegistrationFormPage submit() {
        submitButton.scrollTo().click();
        return this;
    }

    @Step("Проверяем заголовок модального окна: {expectedTitle}")
    public RegistrationFormPage checkSuccessModalTitle(String expectedTitle) {
        resultsTableComponent.checkModalTitle(expectedTitle);
        return this;
    }

    @Step("Проверяем отправленные данные")
    public RegistrationFormPage checkSubmittedData(Map<String, String> expectedResults) {
        resultsTableComponent.checkResults(expectedResults);
        return this;
    }

    @Step("Закрываем модальное окно результата")
    public RegistrationFormPage closeSuccessModal() {
        resultsTableComponent.closeModal();
        return this;
    }

    @Step("Проверяем подсветку обязательных полей")
    public RegistrationFormPage checkRequiredFieldsValidation(String expectedClass) {
        form.shouldHave(cssClass(expectedClass));
        $("#firstName:invalid").shouldBe(visible);
        $("#lastName:invalid").shouldBe(visible);
        $("#userNumber:invalid").shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что email невалидный")
    public RegistrationFormPage checkEmailInvalid() {
        $("#userEmail:invalid").shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что телефон невалидный")
    public RegistrationFormPage checkPhoneInvalid() {
        $("#userNumber:invalid").shouldBe(visible);
        return this;
    }

    @Step("Проверяем цвет рамки поля телефона")
    public RegistrationFormPage checkPhoneBorderColor(String expectedColor) {
        phoneInput.shouldHave(cssValue("border-top-color", expectedColor));
        return this;
    }
}
