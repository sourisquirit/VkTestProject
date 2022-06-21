package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private static final java.util.logging.Logger logger = Logger.getLogger(LoginPage.class.getName());

    // selectors
    private static final By emailInputField = By.xpath("//input[@name='email']");
    private static final By passwordInputField = By.xpath("//input[@name='pass']");
    private static final By installAllowButton = By.id("install_allow");
    private static final By allowButton = By.xpath("//button[contains(@onclick, 'allow')]");

    @Step("Init Selenide")
    public void init() {
        logger.info("Configuring Selenide");
        Configuration.headless = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
        );
        logger.info("Selenide was configured");
    }

    @Step("Close driver")
    public void quit() {
        WebDriverRunner.closeWebDriver();
        logger.info("Driver was closed");
    }

    @Step("Open {url}")
    public LoginPage openUrl(String url) {
        logger.info("Open page " + url);
        open(url);
        return page(LoginPage.class);
    }

    @Step("Allow access to application")
    public LoginPage allowAccess() {
        logger.info("Set login data");

        String user = System.getProperty("login");
        String pass = System.getProperty("pass");
        $(emailInputField).sendKeys(user);
        $(passwordInputField).sendKeys(pass);
        $(installAllowButton).click();
        if ($(allowButton).exists()) {
            $(allowButton).click();
        }
        logger.info("Access allowed");
        return page(LoginPage.class);
    }

    @Step("Get code for further token request")
    public String getCode() {
        logger.info("Get code for further token request");
        return WebDriverRunner.getWebDriver().getCurrentUrl().split("#code=")[1];
    }
}
