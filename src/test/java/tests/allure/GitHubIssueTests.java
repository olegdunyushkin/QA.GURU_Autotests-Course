package tests.allure;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Epic("GitHub")
@Feature("Issues в репозитории")
@Owner("Олег Дунюшкин")
@Link(name = "Репозиторий qa_guru_14_10", url = "https://github.com/qa-guru/qa_guru_14_10")
@DisplayName("Проверка Issue в репозитории GitHub")
public class GitHubIssueTests extends AllureTestBase {

    private static final String REPOSITORY_URL = "https://github.com/qa-guru/qa_guru_14_10";
    private static final String ISSUE_LINK = "/qa-guru/qa_guru_14_10/issues/2";
    private static final String ISSUE_TITLE = "Issue for Autotest";

    GitHubIssueSteps steps = new GitHubIssueSteps();

    @Test
    @DisplayName("Проверка названия Issue через чистый Selenide")
    @Story("Проверка названия Issue")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Issue #2", url = "https://github.com/qa-guru/qa_guru_14_10/issues/2")
    @Description("Проверяем, что в репозитории qa_guru_14_10 открывается Issue #2 с ожидаемым названием.")
    void issueTitleShouldBeVisibleWithSelenideTest() {
        open(REPOSITORY_URL);
        $("#issues-tab").shouldBe(visible).click();
        $("[href='" + ISSUE_LINK + "']").shouldBe(visible).click();
        $("[data-testid='issue-title']").shouldBe(visible).shouldHave(exactText(ISSUE_TITLE));
    }

    @Test
    @DisplayName("Проверка названия Issue через лямбда-шаги")
    @Story("Проверка названия Issue")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Issue #2", url = "https://github.com/qa-guru/qa_guru_14_10/issues/2")
    @Description("Проверяем, что в репозитории qa_guru_14_10 открывается Issue #2 с ожидаемым названием.")
    void issueTitleShouldBeVisibleWithLambdaStepsTest() {
        step("Открываем репозиторий qa_guru_14_10", () -> {
            open(REPOSITORY_URL);
        });

        step("Переходим во вкладку Issues", () -> {
            $("#issues-tab").shouldBe(visible).click();
        });

        step("Открываем Issue", () -> {
            $("[href='" + ISSUE_LINK + "']").shouldBe(visible).click();
        });

        step("Проверяем название Issue", () -> {
            $("[data-testid='issue-title']").shouldBe(visible).shouldHave(exactText(ISSUE_TITLE));
        });
    }

    @Test
    @DisplayName("Проверка названия Issue через аннотацию @Step")
    @Story("Проверка названия Issue")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Issue #2", url = "https://github.com/qa-guru/qa_guru_14_10/issues/2")
    @Description("Проверяем, что в репозитории qa_guru_14_10 открывается Issue #2 с ожидаемым названием.")
    void issueTitleShouldBeVisibleWithAnnotatedStepsTest() {
        steps.openRepository(REPOSITORY_URL);
        steps.openIssuesTab();
        steps.openIssue(ISSUE_LINK);
        steps.checkIssueTitle(ISSUE_TITLE);
    }
}
