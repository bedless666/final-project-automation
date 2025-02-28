package web.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import java.time.Duration;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setUp() {
        if (driver == null) {
            try {
                System.out.println("🔧 Setting up ChromeDriver...");
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized"); // Maksimalkan jendela
                options.addArguments("--disable-infobars"); // Hilangkan pop-up info bar
                options.addArguments("--remote-allow-origins=*"); // Izinkan akses dari sumber eksternal

                // Menjalankan mode headless jika di environment CI
                if (System.getenv("CI") != null) {
                    System.out.println("🌐 Running in CI mode, enabling headless mode...");
                    options.addArguments("--headless"); // Mode headless agar bisa berjalan tanpa UI
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage"); // Menghindari error memori di Linux
                }

                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

                System.out.println("✅ ChromeDriver successfully initialized.");

            } catch (Exception e) {
                System.err.println("❌ Error initializing WebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to initialize WebDriver", e);
            }
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println("🛑 Closing WebDriver...");
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("❌ WebDriver not initialized. Ensure @Before in Hooks.java is executed.");
        }
        return driver;
    }
}
