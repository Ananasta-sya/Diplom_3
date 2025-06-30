package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;
    private final By enterButton = By.xpath(".//a[text()='Войти']");
    public static final String FORGOTPASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("Нажатие на кнопку Войти")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}
