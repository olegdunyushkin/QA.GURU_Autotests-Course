package tests.demoqa;

import org.junit.jupiter.api.Test;
import tests.demoqa.data.RegistrationFormTestData;
import tests.demoqa.pages.MainPage;
import tests.demoqa.pages.RegistrationFormPage;

public class RegistrationFormTests extends TestBase {

    private final RegistrationFormTestData testData = new RegistrationFormTestData();
    private final MainPage mainPage = new MainPage();
    private final RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @Test
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
    void moveToFormTest() {
        mainPage.openPage()
                .openPracticeFormPage()
                .checkPageTitle(testData.formTitle);
    }

    @Test
    void noFieldsTest() {
        registrationFormPage.openPage()
                .submit()
                .checkRequiredFieldsValidation(testData.validatedFormClass);
    }

    @Test
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
    void maximumHappyPathTest() {
        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .selectGender(testData.maleGender)
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
