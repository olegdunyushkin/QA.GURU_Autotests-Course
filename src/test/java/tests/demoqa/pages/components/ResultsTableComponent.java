package tests.demoqa.pages.components;

import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");
    private final SelenideElement resultsTable = $(".table-responsive");
    private final SelenideElement closeButton = $("#closeLargeModal");

    public ResultsTableComponent checkModalTitle(String expectedTitle) {
        modalTitle.shouldHave(text(expectedTitle));
        return this;
    }

    public ResultsTableComponent checkResults(Map<String, String> expectedResults) {
        expectedResults.forEach(this::checkResult);
        return this;
    }

    public ResultsTableComponent closeModal() {
        closeButton.click();
        return this;
    }

    private void checkResult(String key, String value) {
        resultsTable.$(byText(key)).parent().shouldHave(text(value));
    }
}
