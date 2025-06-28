package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Paths;

public class WebDriverFactory {
    public enum BrowserType {
        CHROME,
        YANDEX
    }

    public static WebDriver createDriver(BrowserType browser) {
        switch (browser) {
            case YANDEX:
                return createYandexDriver();
            case CHROME:
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        String driverPath = "C:\\Users\\anas9\\Desktop\\Diplom\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        String yandexBrowserPath = getYandexBrowserPath();
        File binary = new File(yandexBrowserPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(binary);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    private static String getYandexBrowserPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return Paths.get(System.getenv("LOCALAPPDATA"),
                    "Yandex", "YandexBrowser", "Application", "browser.exe").toString();
        } else if (os.contains("mac")) {
            return "/Applications/Yandex.app/Contents/MacOS/Yandex";
        } else {
            return "/usr/bin/yandex-browser";
        }
    }
}