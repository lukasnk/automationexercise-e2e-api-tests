package tests.ui;

import base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NegativeLoginFailureTest extends BaseTest {

    @Test
    @Tag("demo-failure")
    void shouldFailOnInvalidLogin() {
        HomePage homePage = new HomePage(page);
        homePage.open(baseUrl);
        homePage.signupLoginButton().click();

        LoginPage loginPage = new LoginPage(page);
        loginPage.login("invalid@example.com", "badpassword");

        assertThat(loginPage.loginError()).isVisible();
    }
}