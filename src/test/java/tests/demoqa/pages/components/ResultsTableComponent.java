package tests.demoqa.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");
    private final SelenideElement resultsTable = $(".table-responsive");
    private final SelenideElement closeButton = $("#closeLargeModal");

    @Step("Проверяем заголовок результата: {expectedTitle}")
    public ResultsTableComponent checkModalTitle(String expectedTitle) {
        modalTitle.shouldHave(text(expectedTitle));
        return this;
    }

    @Step("Проверяем таблицу результата")
    public ResultsTableComponent checkResults(Map<String, String> expectedResults) {
        expectedResults.forEach(this::checkResult);
        return this;
    }

    @Step("Закрываем модальное окно")
    public ResultsTableComponent closeModal() {
        closeButton.click();
        return this;
    }

    @Step("Проверяем поле результата: {key} = {value}")
    private void checkResult(String key, String value) {
        resultsTable.$(byText(key)).parent().shouldHave(text(value));
    }
}
