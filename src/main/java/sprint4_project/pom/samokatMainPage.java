package sprint4_project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class samokatMainPage {
    private WebDriver driver;

    //кнопка принятия кук
    private By acceptCookiesButton = By.id("rcc-confirm-button");

    //кнопки-аккордеоны (списком этих кнопок)
    private By accordeonsButtons = By.className("accordion__button");

    private int i;

    //конкретный случайный аккордеон с вопросом
    private By particularAccordeon = By.xpath(".//div[@id='accordion__heading-" + i + "']");


    //открывшийся ответ
    private By shownAnswer = By.xpath(".//div[@class='accordion__panel' and not(@hidden)]");

    //ответ, который есть в аккордеоне для этого вопроса
    private By expectedAnswer = By.xpath("(.//div[@class='accordion__item']//div[@class='accordion__panel'])[" + (i+1) + "]");

    //верхняя кнопка "Заказать"
    private By topOrderButton = By.xpath(".//div[contains(@class, 'Header_Header')]//button[text()='Заказать']");

    //нижняя кнопка "Заказать"
    private By bottomOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");


    public samokatMainPage(WebDriver driver) {
        this.driver = driver;
        this.i = numberOfElements(); //именно в конструкторе инициализирую её, т.к.
        // не может быть инициализирована ДО установки драйвера
    }

    //принимаем куки
    public samokatMainPage acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
        return this;
    }

    //находим количество элементов аккордеонов
    public Integer numberOfElements() {
        List<WebElement> questionsList = driver.findElements(accordeonsButtons);
        int numberOfQuestions = questionsList.size();
        int index = new Random().nextInt(numberOfQuestions);
        return index;
    }

    //скроллим до этого случайного конкретного аккордеона
    public samokatMainPage scrollToElement() {
        WebElement element = driver.findElement(particularAccordeon);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    //кликаем на вопрос в этом аккордеоне
    public samokatMainPage clickOnQuestion() {
        driver.findElement(particularAccordeon).click();
        return this;
    }

    //получаем текст ОТОБРАЗИВШЕГОСЯ ответа, т.е. который НЕ СКРЫТ
    public String getShownAnswerText() {
        return driver.findElement(shownAnswer).getText();
    }

    //получаем текст, который находится в ЭТОМ ЖЕ РАНДОМНОМ аккордеоне в DOMе
    public String getExpectedAnswerFromDOM() {
        return driver.findElement(expectedAnswer).getText();
    }

    //кликаем на кнопку заказа, которая перекинет на страницу /order/
    public OrderPage clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
        return new OrderPage(driver);//вернули новую страницу - создали новый объект класса OrderPage,
        // передали driver в его конструктор и вернули этот объект
    }


}
