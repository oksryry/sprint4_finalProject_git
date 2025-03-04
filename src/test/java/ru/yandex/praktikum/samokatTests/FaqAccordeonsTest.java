package ru.yandex.praktikum.samokatTests;

import configs.browserRules;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sprint4_project.pom.samokatMainPage;

public class FaqAccordeonsTest {

    @Rule
    public final browserRules browserRules = new browserRules();

    @Test
    public void checkQuestionAndAnswerMatch() {

        browserRules.driver().get("https://qa-scooter.praktikum-services.ru/");

         samokatMainPage mainPage = new samokatMainPage(browserRules.driver());

         mainPage.acceptCookies();

        for (int i = 0; i < mainPage.numberOfAccordeons(); i++) {
            mainPage.scrollToElement(i)
                    .clickOnQuestion(i);
            Assert.assertEquals(mainPage.getExpectedAnswerFromDOM(i), mainPage.getShownAnswerText());
            System.out.println(mainPage.getExpectedAnswerFromDOM(i));
        }

    }

}
