package ru.yandex.praktikum.personalDraftsExamples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Praktikum {
    //тест скопирован из тренажера, проверяла на нём работу хрома - работает

    private WebDriver driver;

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();

        // Создаём драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.get("https://qa-mesto.praktikum-services.ru/");
        // Выполни авторизацию
        driver.findElement(By.id("email")).sendKeys("ryzhova_44@qwqw.ru");
        driver.findElement(By.id("password")).sendKeys("234234ERTYU");
        driver.findElement(By.className("auth-form__button")).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("places__list")));
        // Найди карточку контента и сделай скролл до неё
        WebElement element = driver.findElement(By.xpath(".//ul[@class='places__list']/li[@class='places__item card'][1]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}