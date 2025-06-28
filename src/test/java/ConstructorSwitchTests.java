import helper.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorSwitchTests {
    private MainPage mainPage;
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver(WebDriverFactory.BrowserType.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver);
        driver.get(MainPage.MAIN_URL);
    }
    @Test
    @DisplayName("Проверка отображения раздела Начинки в Конструкторе после выбора вкладки Начинки")
    public void constructorSwitchToFillingsTest() {
        mainPage.clickFillingsButton();
        assertEquals("Начинки", mainPage.checkSelectedSection());
    }
    @Test
    @DisplayName("Проверка отображения раздела Соусы в Конструкторе после выбора вкладки Начинки")
    public void constructorSwitchToSaucesTest() {
        mainPage.clickSaucesButton();
        assertEquals("Соусы", mainPage.checkSelectedSection());
    }
    @Test
    @DisplayName("Проверка отображения раздела Булки в Конструкторе после выбора вкладки Начинки")
    public void constructorSwitchToBunsTest() {
        mainPage.clickSaucesButton();
        mainPage.clickBunsButton();
        assertEquals("Булки", mainPage.checkSelectedSection());
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
