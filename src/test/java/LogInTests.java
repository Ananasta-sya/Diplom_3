import helper.CreateUser;
import helper.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.ForgotPasswordPage;
import pageobject.LogInPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LogInTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private RegisterPage registerPage;
    private String name;
    private String email;
    private LogInPage logInPage;
    private MainPage mainPage;
    private String password;
    private ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver(WebDriverFactory.BrowserType.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(RegisterPage.REGISTRATION_URL);
        name = CreateUser.generateName();
        email = CreateUser.generateEmail();
        password = CreateUser.generatePassword();
        registerPage = new RegisterPage(driver);
        logInPage = new LogInPage(driver);
        mainPage = new MainPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        registerPage.registerNewUser(name, email,password);
        driver.get(MainPage.MAIN_URL);
    }
    @Test
    @DisplayName("Вход в аккаунт по кнопке Войти на главной странице")
    public void logInFromMainPageTest() {
        mainPage.clickAccountEnterButton();
        logInPage.logIn(email, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.createOrderButton)).isDisplayed();
        assertTrue(mainPage.createOrderButtonVisible());
    }
    @Test
    @DisplayName("Вход в аккаунт через Личный кабинет")
    public void logInFromAccountButtonTest() {
        mainPage.clickAccountButton();
        logInPage.logIn(email, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.createOrderButton)).isDisplayed();
        assertTrue(mainPage.createOrderButtonVisible());
    }
    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме регистрации")
    public void logInFromRegisterPageTest() {
        driver.get(RegisterPage.REGISTRATION_URL);
        registerPage.clickLogInButton();
        logInPage.logIn(email, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.createOrderButton)).isDisplayed();
        assertTrue(mainPage.createOrderButtonVisible());
    }
    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме восстановления пароля")
    public void logInFromForgotPasswordPageTest() {
        driver.get(ForgotPasswordPage.FORGOTPASSWORD_URL);
        forgotPasswordPage.clickEnterButton();
        logInPage.logIn(email, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.createOrderButton)).isDisplayed();
        assertTrue(mainPage.createOrderButtonVisible());
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
