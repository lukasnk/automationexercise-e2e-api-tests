package tests.ui;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    void shouldOpenHomePageSuccessfully() {
        HomePage homePage = new HomePage(page);

        homePage.open();

        Assertions.assertTrue(
                homePage.getTitle().contains("Automation Exercise"),
                "Page title should contain 'Automation Exercise'"
        );

        Assertions.assertTrue(
                homePage.isLogoVisible(),
                "Logo should be visible"
        );

        Assertions.assertTrue(
                homePage.isSignupLoginButtonVisible(),
                "Signup/Login button should be visible"
        );
    }
}