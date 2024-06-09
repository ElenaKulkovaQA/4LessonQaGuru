package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchPagesWikiGitHubTest {

    @BeforeAll
    static void setUpTests() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void searchPageOnGithub() {

        //открыть браузер на странице в Github
        open("https://github.com/");

        //Откройте страницу Selenide в Github = ввести в поиск Selenide
        $("div.search-input-container").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("div.kXssRI div").first().$("a").click();

        // - Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");

        // - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(byText("SoftAssertions")).click();// открыть страницу SoftAssertions

        //проверьте что внутри есть пример кода для JUnit5
        //$(byText("JUnit5 extension "));

        $("#wiki-body").shouldHave(text("JUnit5"));
    }

}
