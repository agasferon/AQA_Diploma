package ru.netology.web.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.Data;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataBaseHelper;
import ru.netology.web.data.DataHelper;
import ru.netology.web.pages.CreditRequestPaymentPage;
import ru.netology.web.pages.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreditRequestTest {

    @BeforeEach
    void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DataBaseHelper.cleanTables();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    private CreditRequestPaymentPage enterToCreditRequestPage() {
        open(System.getProperty("app.url"));
        val dashboardPage = new DashboardPage();
        val creditRequestPaymentPage = dashboardPage.goToCreditRequestPage();
        return creditRequestPaymentPage;
    }

    @Test
    public void shouldPayInCreditByCard1() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithCard1Number());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.notificationOkVisible();
        assertEquals("APPROVED", DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByCard2() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithCard2Number());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.notificationErrorVisible();
        assertEquals("DECLINED", DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByNotProvidedCard() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithNotProvidedCardNumber());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.notificationErrorVisible();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByInvalidCard() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithInvalidCardNumber());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByExpiredCardThisYear() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithExpiredDateCardThisYear());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByExpiredCardLastYear() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithExpiredDateCardLastYear());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByInvalidMonthNumber() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithInvalidMonthNumber());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByZeroMonthNumber() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithZeroMonthNumber());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByInvalidYearNumber() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithInvalidYearNumber());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByCyrillicCardOwner() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithCyrillicCardOwner());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

    @Test
    public void shouldNotPayInCreditByInvalidCVC() {
        val creditRequestPaymentPage = enterToCreditRequestPage();
        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithInvalidCVC());
        creditRequestPaymentPage.makeClick();
        creditRequestPaymentPage.inputInvalidMessage();
        assertNull(DataBaseHelper.checkStatusInCredit());
    }

}