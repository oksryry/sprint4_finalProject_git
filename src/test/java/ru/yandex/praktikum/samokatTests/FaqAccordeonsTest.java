package ru.yandex.praktikum.samokatTests;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sprint4_project.pom.samokatMainPage;

public class FaqAccordeonsTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkQuestionAndAnswerMatch() {

         samokatMainPage mainPage = new samokatMainPage(driver);

         mainPage.acceptCookies()
                 .scrollToElement()
                 .clickOnQuestion();
//         mainPage.scrollToElement();
//         mainPage.clickOnQuestion();

         Assert.assertEquals(mainPage.getExpectedAnswerFromDOM(), mainPage.getShownAnswerText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
