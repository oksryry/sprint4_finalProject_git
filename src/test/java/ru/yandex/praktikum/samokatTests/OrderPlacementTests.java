package ru.yandex.praktikum.samokatTests;

import configs.browserRules;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import sprint4_project.pom.OrderPage;
import sprint4_project.pom.samokatMainPage;

@RunWith(Parameterized.class)
public class OrderPlacementTests {

    private WebDriver driver;
    private static final By TOP_ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Header_Header')]//button[text()='Заказать']");
    private static final By BOTTOM_ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");

    private String nameText;
    private String surnameText;
    private String deliveryAddressText;
    private String phoneText;
    private String subwayStationName;

    private String dateOfDelivery;

    private int numberOdRentDays;

    private String comment;

    private By orderButton;

    public OrderPlacementTests(By orderButton, String nameText, String surnameText, String deliveryAddressText, String phoneText, String subwayStationName, String dateOfDelivery, int numberOdRentDays, String comment) {
        this.orderButton = orderButton;
        this.nameText = nameText;
        this.surnameText = surnameText;
        this.deliveryAddressText = deliveryAddressText;
        this.phoneText = phoneText;
        this.subwayStationName = subwayStationName;
        this.dateOfDelivery = dateOfDelivery;
        this.numberOdRentDays = numberOdRentDays;
        this.comment = comment;

    }


    @Rule
    public final browserRules browserRules = new browserRules();



    @Parameterized.Parameters
    public static Object[][] userParametersStep1() {
        return new Object[][] {
                {TOP_ORDER_BUTTON, "Ивано", "Винокуров", "МОсква воздвиженка 2", "79998887766", "Черкизовская", "08.05.2025", 1, ""},
                {BOTTOM_ORDER_BUTTON, "тестовоеимя", "Тестоваяфамилия", "Владивосток, Светланская ул, 3435", "70008887766", "Митино", "07.01.2024", 6, "тестовый коммент для курьера"},
        };
    }


    @Test
    public void orderByTopButton() {

        browserRules.driver().get("https://qa-scooter.praktikum-services.ru/");

        //нажимаем на верхнюю кнопку "Заказать"
        samokatMainPage mainPage = new samokatMainPage(browserRules.driver());
        mainPage.acceptCookies();
//        mainPage.clickTopOrderButton();
        mainPage.clickOrderButton(orderButton);

        OrderPage orderPage = new OrderPage(browserRules.driver());

        //дальше все действия на странице заказа
        orderPage.goToOrderCreationPage();//перешли на старницу создания заказа

        orderPage.fillInOrderForm(nameText, surnameText, deliveryAddressText, phoneText, subwayStationName, dateOfDelivery, numberOdRentDays, comment);

        orderPage.checkIfOrderIsPlacedSuccess();

    }


}
