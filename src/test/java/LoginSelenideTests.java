import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LoginSelenideTests {
    @BeforeAll
    static void setUp(){
        Configuration.browser = "firefox";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDown(){
        closeWebDriver();
    }

    @Test
    void successfulLoginTest(){

        step("Открываем страницу с авторизацией", () -> {
            open("https://niffler.qa.guru");

            sleep(5000);

//            Allure.getLifecycle().addAttachment(
//                    "Пустная страница авторизации",
//                    "image/png",
//                    "png",
//                    ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
//            );
        });

        step("Вводим данные", () -> {
            $("[id=username]").setValue("stas");
            $("[id=password]").setValue("12345");

            Allure.getLifecycle().addAttachment(
                    "Заполненная форма",
                    "image/png",
                    "png",
                    ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            );
        });

        step("Нажимаем на кнопку", () -> {
            $("[id=login-button]").click();
        });

        step("Проверяем наличие текста History of Spendings", () -> {
            $("[id=spendings]").shouldHave(text("History of Spendings"));

            sleep(5000);

            Allure.getLifecycle().addAttachment(
                    "Заполненная форма",
                    "image/png",
                    "png",
                    ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            );
        });
    }
}
