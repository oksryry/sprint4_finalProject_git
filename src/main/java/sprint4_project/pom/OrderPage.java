package sprint4_project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private WebDriver driver;

 //ДЛЯ КОГО САМОКАТ
    private String orderPageUrl = "https://qa-scooter.praktikum-services.ru/order";

    //имя, инпут
    private By name = By.xpath(".//input[@placeholder='* Имя']");

    //фамилия, инпут
    private By surname = By.xpath(".//input[@placeholder='* Фамилия']");

    //адрес, инпут
    private By deliveryAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //станция метро, дропдаун
    private By subwayStation = By.xpath(".//input[@placeholder='* Станция метро']");


    //сюда в методе ниже положим значение, которое будем передавать в тот метод и использовать, чтобы найти нужную динамичную станцию
    //public String enteredStation = "тест";

    //подходящая станция в списке - фигня, не получилось реализовать динамический локатор
    //private By stationInList = By.xpath(".//button/div[contains(text(), '" + enteredStation + "')]");



    //телефон
    private By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка перехода на шаг 2 - Далее
    private By goFurther = By.xpath(".//button[text()='Далее']");


    //ДАННЫЕ ПРО САМОКАТ

    //когда привезти самокат - инпут
    private By deliveryDateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //когда привезти самокат - подсвеченная дата в календаре
    private By deliveryDateCalendar = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");

    //срок аренды - поле
    private By rentPeriodField = By.xpath(".//div[text()='* Срок аренды']");

    private int rentDays;

    //срок аренды - опция в дропдауне, сделать переменной
    //private By rentPeriodOption = By.xpath(".//div[text()='четверо суток']");
    //private By rentPeriodOption = By.xpath(".//div[@class='Dropdown-option'][" + rentDays + "]");

    //цвет черный
    private By blackScooter = By.id("black");

    //цвет серый
    private By greyScooter = By.id("grey");

    //комментарий для курьера
    private By commentForCourierField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

//нкнопка заказа
    private By placeOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //кнопка подтверждения размещения заказа
    private By orderConfirmationButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");

//сообщение об успешном оформлении заказа
    private By orderPlacedSuccess = By.xpath(".//div[text()='Заказ оформлен']");



    public OrderPage(WebDriver driver) {
       this.driver = driver;
    }



    public void goToOrderCreationPage() {
        driver.get(orderPageUrl);
    }

    //заполнить простые инпуты в шаге 1 формы - тут будет параметризованный тест
    public void fillInOrderForm(String nameText, String surnameText, String deliveryAddressText, String phoneText, String subwayStationName, String dateOfDelivery, int numberOfRentDays, String comment) {

        //Заполняем шаг 1
        driver.findElement(name).sendKeys(nameText);
        driver.findElement(surname).sendKeys(surnameText);
        driver.findElement(deliveryAddress).sendKeys(deliveryAddressText);
        driver.findElement(phone).sendKeys(phoneText);
        driver.findElement(subwayStation).click();
        driver.findElement(subwayStation).sendKeys(subwayStationName);
        //this.enteredStation = subwayStationName;
        driver.findElement(By.xpath(".//button/div[contains(text(), '" + subwayStationName + "')]")).click();
        driver.findElement(goFurther).click();

        //Заполняем шаг 2
        driver.findElement(deliveryDateInput).sendKeys(dateOfDelivery);
        driver.findElement(deliveryDateCalendar).click();
        driver.findElement(rentPeriodField).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option'][" + numberOfRentDays + "]")).click();
        driver.findElement(blackScooter).click();
        driver.findElement(commentForCourierField).sendKeys(comment);
        driver.findElement(placeOrderButton).click();
        driver.findElement(orderConfirmationButton).click();
    }


    //поверяем, что сообщение об успешной отпарвке заказа вывелось
    public boolean checkIfOrderIsPlacedSuccess() {
        driver.findElement(orderPlacedSuccess);
        return true;
    }


}
