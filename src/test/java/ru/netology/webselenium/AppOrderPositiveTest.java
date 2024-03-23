package ru.netology.webselenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderPositiveTest {
    private WebDriver driver;

/*
    @BeforeAll
    public static void setupAll() {
             WebDriverManager.chromedriver().setup();
    }
*/

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldBeSuccessfulForm() {
        driver.findElement(By.cssSelector("[data-test-id=name]input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone]input")).sendKeys("+79123456789");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        var actualTextElement = driver.findElement(By.cssSelector("[data-test-id=order-success]"));
        var actualText = actualTextElement.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
        assertTrue(actualTextElement.isDisplayed());

    }


    /*
    @Test
    public void shouldBeSuccessfulForm() {
        driver.findElement(By.xpath("@//span[@data-test-id='name']input")).sendKeys("Иванов Иван");
        driver.findElement(By.xpath("@//span[data-test-id='phone']input")).sendKeys("+79123456789");
        driver.findElement(By.xpath("@//label[data-test-id='agreement']")).click();
        driver.findElement(By.xpath("@//button[contains(@class,'button')]")).click();
        // var actualTextElement = driver.findElement(By.cssSelector("[data-test-id=order-success]"));
        // var actualText = actualTextElement.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
                driver.findElement(By.xpath("@//span[data-test-id='name'][contains (@class,'input_invalid')]")).getText().trim());
        //assertTrue(actualTextElement.isDisplayed());

    }*/

}
