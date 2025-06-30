import helper.CreateUser;
import helper.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LogInPage;
import pageobject.RegisterPage;
import userbuilder.CreateUserAPI;
import userbuilder.User;

import static org.junit.Assert.assertTrue;

public class RegistrationPageTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private String name;
    private String email;
    private LogInPage logInPage;
    private String password;
    private String accessToken;
    CreateUserAPI createUserAPI = new CreateUserAPI();
    private User user;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver(WebDriverFactory.BrowserType.CHROME);
        driver.get(RegisterPage.REGISTRATION_URL);
        name = CreateUser.generateName();
        email = CreateUser.generateEmail();
        registerPage = new RegisterPage(driver);
        logInPage = new LogInPage(driver);
        user = new User(name, email, password);
    }

    @Test
    @DisplayName("Регистрация нового пользователя с валидными данными")
    public void registrationTest() {
        password = CreateUser.generatePassword();
        registerPage.registerNewUser(name, email,password);
        assertTrue(logInPage.visibleEnterButton());
    }
    @Test
    @DisplayName("Регистрация нового пользователя с невалидным паролем")
    public void registrationInvalidPasswordTest() {
        password = CreateUser.generateInvalidPassword();
        registerPage.registerNewUser(name, email,password);
        assertTrue(registerPage.checkMessageInvalidPassword());
    }
    @After
    public void tearDown() {
        if (accessToken != null) {
            createUserAPI.logInUserAndGetToken(user);
            createUserAPI.deleteUser(accessToken);
        }
        driver.quit();
    }
}
