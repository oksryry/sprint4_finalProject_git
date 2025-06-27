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

    //конкретный случайный аккордеон с вопросом, ниже в методе делаем ему динамический локатор
    private By particularAccordeon;


    //открывшийся ответ
    private By shownAnswer = By.xpath(".//div[@class='accordion__panel' and not(@hidden)]");;

    //ответ, который есть в аккордеоне для этого вопроса, , ниже в методе делаем ему динамический локатор
    private By expectedAnswer;

    //верхняя кнопка "Заказать", перенесла как константу в класс теста
//    private By topOrderButton = By.xpath(".//div[contains(@class, 'Header_Header')]//button[text()='Заказать']");

    //нижняя кнопка "Заказать", перенесла как константу в класс теста
//    private By bottomOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");


    public samokatMainPage(WebDriver driver) {
        this.driver = driver;
//        this.i = numberOfElements(); //именно в конструкторе инициализирую её, т.к.
        // не может быть инициализирована ДО установки драйвера
    }

    //принимаем куки
    public samokatMainPage acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
        return this;
    }

    //метод, который будет формировать локатор динамически. Иначе, если написать локатор с переменной в полях класса - там
    //локатор создасться при создании объекта класса и больше непоменяется, другое значение меу не присвоить
    public By getParticularAccordeon(int index) {
        particularAccordeon =  By.xpath(".//div[@id='accordion__heading-" + index + "']");
        return particularAccordeon;
    }


    public By getExpectedAnswer(int index) {
        expectedAnswer = By.xpath("(.//div[@class='accordion__item']//div[@class='accordion__panel'])[" + (index+1) + "]");
        return expectedAnswer;
    }


//    //задаем динамический индекс, что потом пробежать по всем эелементам в аккордеоне
//    public Integer returnIndex() {
////        List<WebElement> questionsList = driver.findElements(accordeonsButtons);
////        int numberOfQuestions = questionsList.size();
//        int index = 0;
// //       int index = new Random().nextInt(numberOfQuestions);
//        for (int k = 0; k <= numberOfAccordeons(); k++) {
//            index++;
//        }
//        return index;
//    }


    //находим количество элементов аккордеонов
    public Integer numberOfAccordeons() {
        List<WebElement> questionsList = driver.findElements(accordeonsButtons);
        return(questionsList.size());
    }

    //скроллим до этого случайного конкретного аккордеона
    public samokatMainPage scrollToElement(int index) {
        WebElement element = driver.findElement(getParticularAccordeon(index));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    //кликаем на вопрос в этом аккордеоне
    public samokatMainPage clickOnQuestion(int index) {
        driver.findElement(getParticularAccordeon(index)).click();
        return this;
    }

    //получаем текст ОТОБРАЗИВШЕГОСЯ ответа, т.е. который НЕ СКРЫТ
    public String getShownAnswerText() {
        return driver.findElement(shownAnswer).getText();
    }

    //получаем текст, который находится в ЭТОМ ЖЕ РАНДОМНОМ аккордеоне в DOMе
    public String getExpectedAnswerFromDOM(int index) {
        return driver.findElement(getExpectedAnswer(index)).getText();
    }

    //кликаем на кнопку заказа, которая перекинет на страницу /order/
    public OrderPage clickOrderButton(By orderButton) {
        driver.findElement(orderButton).click();
        return new OrderPage(driver); //вернули новую страницу - создали новый объект класса OrderPage,
        // передали driver в его конструктор и вернули этот объект
    }


}
