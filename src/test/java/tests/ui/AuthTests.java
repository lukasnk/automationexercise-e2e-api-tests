package tests.ui;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
public class AuthTests extends BaseTest {
    private static final String FIRST_NAME = "Test";
    private static final String LAST_NAME = "User";
    private static final String ADDRESS = "123 Main St";
    private static final String STATE = "CA";
    private static final String CITY = "Los Angeles";
    private static final String ZIPCODE = "90001";
    private static final String MOBILE = "1234567890";
    private static String registeredEmail;

    @Test
    @Order(1)
    void shouldRegisterUserIfMissing() {
        String password = getSecret("LOGIN_PASSWORD");
        Assertions.assertTrue(
                password != null && !password.isBlank(),
                "Set LOGIN_PASSWORD in .env to run the register test"
        );

        String email = getSecret("LOGIN_EMAIL");
        Assertions.assertTrue(
                email != null && !email.isBlank(),
                "Set LOGIN_EMAIL in .env to run the register test"
        );

        String name = "Test User";

        boolean created = registerUser(name, email, password);
        registeredEmail = email;

        if (created) {
            LoginPage loginPage = new LoginPage(page);
            assertThat(loginPage.logoutLink()).isVisible();
        }
    }

    @Test
    @Order(2)
    void shouldLoginWithValidCredentials() {
        String password = getSecret("LOGIN_PASSWORD");
        Assertions.assertTrue(
                password != null && !password.isBlank(),
                "Set LOGIN_PASSWORD in .env to run the valid login test"
        );
        Assertions.assertTrue(
                registeredEmail != null && !registeredEmail.isBlank(),
                "Register test must run before valid login test"
        );

        HomePage homePage = new HomePage(page);
        homePage.open(baseUrl);
        homePage.signupLoginButton().click();

        LoginPage loginPage = new LoginPage(page);
        loginPage.login(registeredEmail, password);

        assertThat(loginPage.logoutLink()).isVisible();
    }

    @Test
    @Order(3)
    void shouldRejectInvalidLogin() {
        HomePage homePage = new HomePage(page);
        homePage.open(baseUrl);
        homePage.signupLoginButton().click();

        LoginPage loginPage = new LoginPage(page);
        loginPage.login("invalid@example.com", "badpassword");

        assertThat(loginPage.loginError()).isVisible();
    }

    private boolean registerUser(String name, String email, String password) {
        HomePage homePage = new HomePage(page);
        homePage.open(baseUrl);
        homePage.signupLoginButton().click();

        LoginPage loginPage = new LoginPage(page);
        loginPage.signup(name, email);
        if (loginPage.isSignupEmailExistsErrorVisible(2000)) {
            return false;
        }

        SignupPage signupPage = new SignupPage(page);
        assertThat(signupPage.accountInfoTitle()).isVisible();
        signupPage.fillRequiredDetails(
                password,
                FIRST_NAME,
                LAST_NAME,
                ADDRESS,
                STATE,
                CITY,
                ZIPCODE,
                MOBILE
        );
        signupPage.createAccount();
        assertThat(signupPage.accountCreatedTitle()).isVisible();
        signupPage.clickContinue();
        return true;
    }

    private String getSecret(String key) {
        String value = System.getProperty(key);
        if (value == null || value.isBlank()) {
            value = System.getenv(key);
        }
        return value;
    }

}