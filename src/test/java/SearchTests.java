import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {
    @BeforeAll
    static void init(){
        browser = "firefox";
    }

    @AfterEach
    void destruct(){
        closeWebDriver();
    }

    @Test
    void successfulSearchTest() {
        open("https://www.duckduckgo.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=react-layout]").shouldHave(text("https://selenide.org"));
    }

    @Test
    void captchaTest() {
        open("https://www.google.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("html").shouldHave(text("Об этой странице"));
    }
}
