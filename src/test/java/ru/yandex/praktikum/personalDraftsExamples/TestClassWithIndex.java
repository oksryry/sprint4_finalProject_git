package ru.yandex.praktikum.personalDraftsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;

public class TestClassWithIndex {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testFaqWithoutPom () {

        driver.findElement(By.id("rcc-confirm-button")).click();

        //ищем рандомный вопрос и скроллим до него
        List<WebElement> questionsList = driver.findElements(By.className("accordion__button"));
        int numberOfQuestions = questionsList.size();
        int index = new Random().nextInt(numberOfQuestions);
        WebElement element = driver.findElement(By.xpath(".//div[@id='accordion__heading-" + index + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        //кликаем на этот рандомный вопрос
        driver.findElement(By.xpath(".//div[@id='accordion__heading-" + index + "']")).click();

        //получаем текст ОТОБРАЗИВШЕГОСЯ ответа, т.е. который НЕ СКРЫТ
        String shownAnswer = driver.findElement(By.xpath(".//div[@class='accordion__panel' and not(@hidden)]")).getText();

        //получаем текст, который находится в ЭТОМ ЖЕ РАНДОМНОМ аккордеоне в DOMе
        String expectedAnswer = driver.findElement(By.xpath("(.//div[@class='accordion__item']//div[@class='accordion__panel'])[" + (index+1) + "]")).getText();

        //сравниваем отображаемый текст с аккордеоном
        Assert.assertEquals(expectedAnswer, shownAnswer);

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

}
