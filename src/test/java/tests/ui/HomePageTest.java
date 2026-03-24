package tests.ui;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePageTest extends BaseTest {

    @Test
    void shouldOpenHomePageSuccessfully() {
        HomePage homePage = new HomePage(page);

        homePage.open(baseUrl);

        assertThat(page).hasTitle("Automation Exercise");

        assertThat(homePage.logo()).isVisible();
        assertThat(homePage.signupLoginButton()).isVisible();
    }
}
