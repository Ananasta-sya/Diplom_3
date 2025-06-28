package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    public static final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/register";
    private final By nameField = By.xpath(".//label[text()='Имя']//following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']//following-sibling::input");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By logInButton = By.xpath(".//a[text()='Войти']");
    private final By messageInvalidPassword = By.xpath(".//*[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step ("Ввод данных в поле Имя")
    public void setName (String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    @Step ("Ввод данных в поле Email")
    public void setEmail (String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    @Step ("Ввод данных в поле Пароль")
    public void setPassword (String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step ("Нажатие на кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    @Step ("Нажатие на кнопку Войти")
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }
    @Step ("Регистрация нового пользователя")
    public void registerNewUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
    @Step ("Проверка получения ошибки при вводе невалидного пароля")
    public boolean checkMessageInvalidPassword() {
        return driver.findElement(messageInvalidPassword).isDisplayed();
    }
}
