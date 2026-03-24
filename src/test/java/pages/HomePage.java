package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;

    private static final String LOGO = "img[alt='Website for automation practice']";
    private static final String SIGNUP_LOGIN_BUTTON = "a[href='/login']";

    public HomePage(Page page) {
        this.page = page;
    }

    public void open(String baseUrl) {
        page.navigate(baseUrl);
    }

    public Locator logo() {
        return page.locator(LOGO);
    }

    public Locator signupLoginButton() {
        return page.locator(SIGNUP_LOGIN_BUTTON);
    }
}