import userbuilder.CreateUserAPI;
import userbuilder.User;
import helper.CreateUser;
import helper.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.ForgotPasswordPage;
import pageobject.LogInPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;
import static pageobject.MainPage.MAIN_URL;

public class LogInTests {
    private WebDriver driver;
    private RegisterPage registerPage;
    private String name;
    private String email;
    private LogInPage logInPage;
    private MainPage mainPage;
    private String password;
    private ForgotPasswordPage forgotPasswordPage;
    private String accessToken;
    CreateUserAPI createUserAPI = new CreateUserAPI();

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver(WebDriverFactory.BrowserType.CHROME);
        name = CreateUser.generateName();
        email = CreateUser.generateEmail();
        password = CreateUser.generatePassword();
        registerPage = new RegisterPage(driver);
        logInPage = new LogInPage(driver);
        mainPage = new MainPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        driver.get(MAIN_URL);
        User user = new User(name, email, password);
        accessToken = createUserAPI.createUserAndGetToken(user);
    }
    @Test
    @DisplayName("Вход в аккаунт по кнопке Войти на главной странице")
    public void logInFromMainPageTest() {
        mainPage.clickAccountEnterButton();
        logInPage.logIn(email, password);
        assertTrue(mainPage.createOrderButtonVisible());
    }
    @Test
    @DisplayName("Вход в аккаунт через Личный кабинет")
    public void logInFromAccountButtonTest() {
        mainPage.clickAccountButton();
        logInPage.logIn(email, password);
        assertTrue(mainPage.createOrderButtonVisible());
    }
    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме регистрации")
    public void logInFromRegisterPageTest() {
        driver.get(RegisterPage.REGISTRATION_URL);
        registerPage.clickLogInButton();
        logInPage.logIn(email, password);
        assertTrue(mainPage.createOrderButtonVisible());
    }
    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме восстановления пароля")
    public void logInFromForgotPasswordPageTest() {
        driver.get(ForgotPasswordPage.FORGOTPASSWORD_URL);
        forgotPasswordPage.clickEnterButton();
        logInPage.logIn(email, password);
        assertTrue(mainPage.createOrderButtonVisible());
    }

    @After
    public void tearDown() {
        createUserAPI.deleteUser(accessToken);
        driver.quit();
    }
}
