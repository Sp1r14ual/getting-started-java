import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPlaywrightTests {

    private static Playwright playwright;
    private static Browser browser;
    private static Page page;

    @BeforeEach
    void init(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterAll
    static void destroy(){
        browser.close();
        browser = null;
        page = null;
    }



    @Test
    void successfulLoginTest() {

            page.navigate("https://niffler.qa.guru");
            page.fill("#username", "stas");
            page.fill("#password", "12345");
            page.click("#login-button");

            page.waitForSelector("#spendings");
            String spendingsText = page.textContent("#spendings");

            assertTrue(spendingsText.contains("History of Spendings"));

    }
}
