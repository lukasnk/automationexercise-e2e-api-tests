package pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;

    private static final String URL = "https://automationexercise.com/";
    private static final String LOGO = "img[alt='Website for automation practice']";
    private static final String SIGNUP_LOGIN_BUTTON = "a[href='/login']";

    public HomePage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(URL);
    }

    public String getTitle() {
        return page.title();
    }

    public boolean isLogoVisible() {
        return page.locator(LOGO).isVisible();
    }

    public boolean isSignupLoginButtonVisible() {
        return page.locator(SIGNUP_LOGIN_BUTTON).isVisible();
    }
}