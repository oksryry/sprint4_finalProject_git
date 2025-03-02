package ru.yandex.praktikum.samokatTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import sprint4_project.pom.OrderPage;
import sprint4_project.pom.samokatMainPage;

@RunWith(Parameterized.class)
public class OrderPlacementTests {

    private WebDriver driver;
    private String nameText;
    private String surnameText;
    private String deliveryAddressText;
    private String phoneText;
    private String subwayStationName;

    private String dateOfDelivery;

    private int numberOdRentDays;

    private String comment;

    public OrderPlacementTests(String nameText, String surnameText, String deliveryAddressText, String phoneText, String subwayStationName, String dateOfDelivery, int numberOdRentDays, String comment) {
        this.nameText = nameText;
        this.surnameText = surnameText;
        this.deliveryAddressText = deliveryAddressText;
        this.phoneText = phoneText;
        this.subwayStationName = subwayStationName;
        this.dateOfDelivery = dateOfDelivery;
        this.numberOdRentDays = numberOdRentDays;
        this.comment = comment;

    }



    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
  //      driver = new SafariDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }



    @Parameterized.Parameters
    public static Object[][] userParametersStep1() {
        return new Object[][] {
                {"Ивано", "Винокуров", "МОсква воздвиженка 2", "79998887766", "Черкизовская", "08.05.2025", 1, ""},
                {"тестовоеимя", "Тестоваяфамилия", "Владивосток, Светланская ул, 3435", "70008887766", "Митино", "07.01.2024", 6, "тестовый коммент для курьера"},
        };
    }


    @Test
    public void orderByTopButton() {

        //нажимаем на верхнюю кнопку "Заказать"
        samokatMainPage mainPage = new samokatMainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);

        //дальше все действия на странице заказа
        orderPage.goToOrderCreationPage();//перешли на старницу создания заказа

        orderPage.fillInOrderForm(nameText, surnameText, deliveryAddressText, phoneText, subwayStationName, dateOfDelivery, numberOdRentDays, comment);

        orderPage.checkIfOrderIsPlacedSuccess();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
