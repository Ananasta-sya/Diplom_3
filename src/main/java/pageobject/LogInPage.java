package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    private final By emailField = By.xpath(".//input[@name='name']");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    private final By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By forgotPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    private final By enterTitle = By.xpath("//h2[text() = 'Вход']");

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step ("Ввод значения в поле email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    @Step ("Ввод значения в поле Пароль")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step ("Нажатие на кнопку Войти")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
    @Step ("Нажатие на кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).isDisplayed();
        driver.findElement(registerButton).click();
    }
    @Step ("Нажатие на кнопку Восстановить пароль")
    public void clickForgotPasswordButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordButton)).isDisplayed();
        driver.findElement(forgotPasswordButton).click();
    }
    @Step("Проверка наличия кнопки Войти")
    public boolean visibleEnterButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(enterTitle)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    @Step("Заполнение всех полей и вход в аккаунт")
    public void logIn(String email, String password){
        setEmail(email);
        setPassword(password);
        wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        clickEnterButton();
    }

}
