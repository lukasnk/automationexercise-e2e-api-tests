package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;

    private static final String LOGO = "img[alt='Website for automation practice']";
    private static final String SIGNUP_LOGIN_BUTTON = "a[href='/login']";
    private static final String[] COOKIE_BUTTON_SELECTORS = {
            "button:has-text('Zgadzam się')",
            "button.fc-cta-consent.fc-primary-button"
    };

    public HomePage(Page page) {
        this.page = page;
    }

    public void open(String baseUrl) {
        page.navigate(baseUrl);
        acceptCookiesIfPresent();
    }

    public Locator logo() {
        return page.locator(LOGO);
    }

    public Locator signupLoginButton() {
        return page.locator(SIGNUP_LOGIN_BUTTON);
    }

    private void acceptCookiesIfPresent() {
        page.waitForTimeout(300);
        for (String selector : COOKIE_BUTTON_SELECTORS) {
            Locator button = page.locator(selector).first();
            if (button.isVisible()) {
                button.click();
                break;
            }
        }
    }
}