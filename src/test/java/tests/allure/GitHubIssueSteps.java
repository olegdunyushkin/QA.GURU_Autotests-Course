package tests.allure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubIssueSteps {

    @Step("Открываем репозиторий {repositoryUrl}")
    public void openRepository(String repositoryUrl) {
        open(repositoryUrl);
    }

    @Step("Переходим во вкладку Issues")
    public void openIssuesTab() {
        $("#issues-tab").shouldBe(visible).click();
    }

    @Step("Открываем Issue")
    public void openIssue(String issueLink) {
        $("[href='" + issueLink + "']").shouldBe(visible).click();
    }

    @Step("Проверяем название Issue: {issueTitle}")
    public void checkIssueTitle(String issueTitle) {
        $("[data-testid='issue-title']").shouldBe(visible).shouldHave(exactText(issueTitle));
    }
}
