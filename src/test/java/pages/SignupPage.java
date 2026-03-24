package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class SignupPage {
    private final Page page;

    private static final String ACCOUNT_INFO_TITLE = "h2:has-text('ENTER ACCOUNT INFORMATION')";
    private static final String TITLE_MR = "input[id='id_gender1']";
    private static final String PASSWORD = "input[id='password']";
    private static final String DAYS = "select[id='days']";
    private static final String MONTHS = "select[id='months']";
    private static final String YEARS = "select[id='years']";
    private static final String FIRST_NAME = "input[id='first_name']";
    private static final String LAST_NAME = "input[id='last_name']";
    private static final String ADDRESS = "input[id='address1']";
    private static final String COUNTRY = "select[id='country']";
    private static final String STATE = "input[id='state']";
    private static final String CITY = "input[id='city']";
    private static final String ZIPCODE = "input[id='zipcode']";
    private static final String MOBILE = "input[id='mobile_number']";
    private static final String CREATE_ACCOUNT_BUTTON = "button[data-qa='create-account']";
    private static final String ACCOUNT_CREATED_TITLE = "h2[data-qa='account-created']";
    private static final String CONTINUE_BUTTON = "a[data-qa='continue-button']";

    public SignupPage(Page page) {
        this.page = page;
    }

    public Locator accountInfoTitle() {
        return page.locator(ACCOUNT_INFO_TITLE);
    }

    public void fillRequiredDetails(String password, String firstName, String lastName, String address,
                                    String state, String city, String zipcode, String mobile) {
        page.locator(TITLE_MR).check();
        page.locator(PASSWORD).fill(password);
        page.locator(DAYS).selectOption(new SelectOption().setValue("1"));
        page.locator(MONTHS).selectOption(new SelectOption().setValue("1"));
        page.locator(YEARS).selectOption(new SelectOption().setValue("1990"));

        page.locator(FIRST_NAME).fill(firstName);
        page.locator(LAST_NAME).fill(lastName);
        page.locator(ADDRESS).fill(address);
        page.locator(COUNTRY).selectOption(new SelectOption().setLabel("United States"));
        page.locator(STATE).fill(state);
        page.locator(CITY).fill(city);
        page.locator(ZIPCODE).fill(zipcode);
        page.locator(MOBILE).fill(mobile);
    }

    public void createAccount() {
        page.locator(CREATE_ACCOUNT_BUTTON).click();
    }

    public Locator accountCreatedTitle() {
        return page.locator(ACCOUNT_CREATED_TITLE);
    }

    public void clickContinue() {
        page.locator(CONTINUE_BUTTON).click();
    }
}