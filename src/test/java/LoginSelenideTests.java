import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginSelenideTests {
    @BeforeAll
    static void init(){
        Configuration.browser = "firefox";
    }

    @AfterEach
    void destruct(){
        closeWebDriver();
    }


    @Test
    void successfulLoginTest() {
        open("https://niffler.qa.guru");
        $("[id=username]").setValue("stas");
        $("[id=password]").setValue("12345");
        $("[id=login-button]").click();
        $("[id=spendings]").shouldHave(text("History of Spendings"));
    }
}
