package ru.netology.web.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataBaseHelper;
import ru.netology.web.data.DataHelper;
import ru.netology.web.pages.CardPaymentPage;
import ru.netology.web.pages.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentTest {

    @BeforeEach
    void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DataBaseHelper.cleanTables();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    private CardPaymentPage enterToPaymentPage() {
        open(System.getProperty("app.url"));
        val dashboardPage = new DashboardPage();
        val cardPaymentPage = dashboardPage.goToPaymentPage();
        return cardPaymentPage;
    }

    @Test
    public void shouldPayByCard1() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithCard1Number());
        cardPaymentPage.makeClick();
        cardPaymentPage.notificationOkVisible();
        assertEquals("APPROVED", DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByCard2() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithCard2Number());
        cardPaymentPage.makeClick();
        cardPaymentPage.notificationErrorVisible();
        assertEquals("DECLINED", DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByNotProvidedCard() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithNotProvidedCardNumber());
        cardPaymentPage.makeClick();
        cardPaymentPage.notificationErrorVisible();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByInvalidCard() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithInvalidCardNumber());
        cardPaymentPage.makeClick();
        cardPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByExpiredCardThisYear() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithExpiredDateCardThisYear());
        cardPaymentPage.makeClick();
        cardPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByExpiredCardLastYear() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithExpiredDateCardLastYear());
        cardPaymentPage.makeClick();
        cardPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByInvalidMonthNumber() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithInvalidMonthNumber());
        cardPaymentPage.makeClick();
        cardPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByZeroMonthNumber() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithZeroMonthNumber());
        cardPaymentPage.makeClick();
        cardPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByInvalidYearNumber() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithInvalidYearNumber());
        cardPaymentPage.makeClick();
        cardPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByCyrillicCardOwner() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithCyrillicCardOwner());
        cardPaymentPage.makeClick();
        cardPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByInvalidCVC() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithInvalidCVC());
        cardPaymentPage.makeClick();
        cardPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInPayment());
    }

}