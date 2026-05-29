package guru.qa.demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import guru.qa.demoqa.data.RegistrationFormTestData;
import guru.qa.demoqa.pages.MainPage;
import guru.qa.demoqa.pages.RegistrationFormPage;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@Tag("web")
@DisplayName("Тесты формы регистрации DemoQA")
public class RegistrationFormTests extends TestBase {

    private final RegistrationFormTestData testData = new RegistrationFormTestData();
    private final MainPage mainPage = new MainPage();
    private final RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @ValueSource(strings = {"Male", "Female", "Other"})
    @ParameterizedTest(name = "Отправка формы с обязательными полями, проверка работы выбора пола: {0}")
    @Tag("smoke")
    @DisplayName("Отправка формы с обязательными полями работает для каждого вида пола (проверка всех 3 вариантов)")
    void requiredFieldsTest(String gender) {
        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .selectGender(gender)
                .setPhone(testData.phone)
                .submit()
                .checkSuccessModalTitle(testData.successModalTitle)
                .closeSuccessModal();
    }

    @Test
    @Tag("navigation")
    @DisplayName("Форма Practice Form открывается с главной страницы")
    void moveToFormTest() {
        mainPage.openPage()
                .openPracticeFormPage()
                .checkPageTitle(testData.formTitle);
    }

    @Test
    @Tag("negative")
    @DisplayName("Пустая форма показывает валидацию обязательных полей")
    void noFieldsTest() {
        registrationFormPage.openPage()
                .submit()
                .checkRequiredFieldsValidation(testData.validatedFormClass);
    }

    @CsvFileSource(resources = "/invalid-emails.csv")
    @ParameterizedTest(name = "{0}: email \"{1}\" невалидный")
    @Tag("negative")
    @DisplayName("Форма отклоняет невалидные email")
    void wrongEmailTest(String caseName, String invalidEmail, String gender) {
        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .selectGender(gender)
                .setPhone(testData.phone)
                .setEmail(invalidEmail)
                .submit()
                .checkEmailInvalid();
    }

    @CsvSource(value = {
            "только буквы,wwwwwwwwww,Other",
            "слишком короткий,12345,Male",
            "девять цифр,123456789,Female"
    })
    @ParameterizedTest(name = "{0}: phone \"{1}\" невалидный")
    @Tag("negative")
    @DisplayName("Форма отклоняет невалидные номер телефона")
    void wrongPhoneTest(String caseName, String invalidPhone, String gender) {
        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .selectGender(gender)
                .setPhone(invalidPhone)
                .submit()
                .checkPhoneInvalid()
                .checkPhoneBorderColor(testData.invalidBorderColor);
    }

    @MethodSource
    @ParameterizedTest(name = "{0}")
    @Tag("smoke")
    @DisplayName("Полная отправка формы работает с русскими и английскими данными")
    void maximumHappyPathTest(String caseName, RegistrationFormTestData data) {
        registrationFormPage.openPage()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.email)
                .selectGender(data.gender)
                .setPhone(data.phone)
                .setBirthDate(data.birthDay, data.birthMonth, data.birthYear)
                .setSubject(data.subject)
                .selectHobbies(data.hobbies)
                .uploadPicture(data.picturePath)
                .setCurrentAddress(data.currentAddress)
                .setStateAndCity(data.state, data.city)
                .submit()
                .checkSuccessModalTitle(data.successModalTitle)
                .checkSubmittedData(data.expectedResults())
                .closeSuccessModal();
    }

    static Stream<Arguments> maximumHappyPathTest() {
        return Stream.of(
                arguments("Полная отправка формы с русскими данными",
                        new RegistrationFormTestData(Locale.forLanguageTag("ru"))),
                arguments("Полная отправка формы с английскими данными",
                        new RegistrationFormTestData(Locale.ENGLISH))
        );
    }
}
