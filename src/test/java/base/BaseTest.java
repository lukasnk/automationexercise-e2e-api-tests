package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import io.github.cdimascio.dotenv.Dotenv;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected String baseUrl;

    @BeforeAll
    static void globalSetup() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
        dotenv.entries().forEach(entry -> {
            if (System.getProperty(entry.getKey()) == null && System.getenv(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
        playwright = Playwright.create();

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "true")
        );

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
        );
    }

    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = context.newPage();
        baseUrl = System.getProperty("baseUrl", "https://automationexercise.com/");
    }

    @AfterAll
    static void globalTeardown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @AfterEach
    void tearDown() {
        if (context != null) {
            context.close();
        }
    }
}