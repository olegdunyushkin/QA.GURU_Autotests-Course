package guru.qa.demoqa;

import org.junit.jupiter.api.Test;
import guru.qa.demoqa.data.TextBoxTestData;
import guru.qa.demoqa.pages.TextBoxPage;

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
