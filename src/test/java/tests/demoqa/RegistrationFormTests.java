package tests.demoqa;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.demoqa.data.RegistrationFormTestData;
import tests.demoqa.pages.MainPage;
import tests.demoqa.pages.RegistrationFormPage;

@Owner("Oleg Dunyushkin")
@Feature("DemoQA")
@Story("Форма регистрации")
@DisplayName("Тесты формы регистрации DemoQA")
public class RegistrationFormTests extends TestBase {

    private final RegistrationFormTestData testData = new RegistrationFormTestData();
    private final MainPage mainPage = new MainPage();
    private final RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @Test
    @DisplayName("Отправка формы с обязательными полями")
    void requiredFieldsTest() {
        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .selectGender(testData.maleGender)
                .setPhone(testData.phone)
                .submit()
                .checkSuccessModalTitle(testData.successModalTitle)
                .closeSuccessModal();
    }

    @Test
    @DisplayName("Переход на страницу формы регистрации")
    void moveToFormTest() {
        mainPage.openPage()
                .openPracticeFormPage()
                .checkPageTitle(testData.formTitle);
    }

    @Test
    @DisplayName("Валидация формы без заполненных полей")
    void noFieldsTest() {
        registrationFormPage.openPage()
                .submit()
                .checkRequiredFieldsValidation(testData.validatedFormClass);
    }

    @Test
    @DisplayName("Валидация поля email")
    void wrongEmailTest() {
        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .selectGender(testData.femaleGender)
                .setPhone(testData.phone)
                .setEmail(testData.invalidEmail)
                .submit()
                .checkEmailInvalid();
    }

    @Test
    @DisplayName("Валидация поля телефона")
    void lettersPhoneTest() {
        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .selectGender(testData.otherGender)
                .setPhone(testData.invalidPhone)
                .submit()
                .checkPhoneInvalid()
                .checkPhoneBorderColor(testData.invalidBorderColor);
    }

    @Test
    @DisplayName("Полная отправка формы с тестовыми данными")
    void maximumHappyPathTest() {
        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .selectGender(testData.gender)
                .setPhone(testData.phone)
                .setBirthDate(testData.birthDay, testData.birthMonth, testData.birthYear)
                .setSubject(testData.subject)
                .selectHobbies(testData.hobbies)
                .uploadPicture(testData.picturePath)
                .setCurrentAddress(testData.currentAddress)
                .setStateAndCity(testData.state, testData.city)
                .submit()
                .checkSuccessModalTitle(testData.successModalTitle)
                .checkSubmittedData(testData.expectedResults())
                .closeSuccessModal();
    }
}
