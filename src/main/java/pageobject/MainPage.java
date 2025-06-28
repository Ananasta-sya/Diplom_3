package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final By bunsSwitchButton = By.xpath("//span[contains(text(), 'Булки')]");
    private final By saucesSwitchButton = By.xpath("//span[text() = 'Соусы']");
    private final By fillingsSwitchButton = By.xpath("//span[text() = 'Начинки']");
    private final By accountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By accountEnterButton = By.xpath(".//button[text()='Войти в аккаунт']");
    public final By createOrderButton = By.xpath("//button[text() = 'Оформить заказ']");
    public final By currentSectionConstructor = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Нажатие на раздел Булки в форме Соберите бургер")
    public void clickBunsButton() {
        driver.findElement(bunsSwitchButton).click();
    }
    @Step("Нажатие на раздел Соусы в форме Соберите бургер")
    public void clickSaucesButton() {
        driver.findElement(saucesSwitchButton).click();
    }
    @Step("Нажатие на раздел Начинки в форме Соберите бургер")
    public void clickFillingsButton() {
        driver.findElement(fillingsSwitchButton).click();
    }
    @Step("Нажатие на кнопку Личный кабинет")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }
    @Step("Нажатие на кнопку Войти в аккаунт")
    public void clickAccountEnterButton() {
        driver.findElement(accountEnterButton).click();
    }
    @Step("Проверка наличия кнопки Оформить заказ")
    public boolean createOrderButtonVisible() {
        return driver.findElement(createOrderButton).isDisplayed();
    }
    @Step("Проверка отображения выбранного раздела в Конструкторе")
    public String checkSelectedSection() {
        WebElement activeSection = wait.until(ExpectedConditions.visibilityOfElementLocated(currentSectionConstructor));
        return activeSection.findElement(By.xpath(".//span")).getText();
    }
}
