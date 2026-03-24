package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private static final String LOGIN_EMAIL = "input[data-qa='login-email']";
    private static final String LOGIN_PASSWORD = "input[data-qa='login-password']";
    private static final String LOGIN_BUTTON = "button[data-qa='login-button']";
    private static final String LOGIN_ERROR = "p:has-text('Your email or password is incorrect!')";
    private static final String SIGNUP_NAME = "input[data-qa='signup-name']";
    private static final String SIGNUP_EMAIL = "input[data-qa='signup-email']";
    private static final String SIGNUP_BUTTON = "button[data-qa='signup-button']";
    private static final String SIGNUP_EMAIL_EXISTS_ERROR = "p:has-text('Email Address already exist')";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String email, String password) {
        page.locator(LOGIN_EMAIL).fill(email);
        page.locator(LOGIN_PASSWORD).fill(password);
        page.locator(LOGIN_BUTTON).click();
    }

    public Locator loginError() {
        return page.locator(LOGIN_ERROR);
    }

    public void signup(String name, String email) {
        page.locator(SIGNUP_NAME).fill(name);
        page.locator(SIGNUP_EMAIL).fill(email);
        page.locator(SIGNUP_BUTTON).click();
    }

    public boolean isSignupEmailExistsErrorVisible(int timeoutMs) {
        Locator error = page.locator(SIGNUP_EMAIL_EXISTS_ERROR).first();
        try {
            error.waitFor(new Locator.WaitForOptions().setTimeout(timeoutMs));
            return error.isVisible();
        } catch (com.microsoft.playwright.PlaywrightException e) {
            return false;
        }
    }
}
