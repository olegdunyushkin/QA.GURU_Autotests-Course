package tests.demoqa;

import org.junit.jupiter.api.Test;
import tests.demoqa.data.TextBoxTestData;
import tests.demoqa.pages.TextBoxPage;

public class TextBoxTests extends TestBase {

    private final TextBoxTestData testData = new TextBoxTestData();
    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void successfulFormTest() {
        textBoxPage.openPage()
                .setFullName(testData.fullName)
                .setEmail(testData.email)
                .setCurrentAddress(testData.currentAddress)
                .setPermanentAddress(testData.permanentAddress)
                .submit()
                .checkSubmittedData(testData);
    }
}
