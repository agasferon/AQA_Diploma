package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataBaseHelper;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardPaymentPage;
import ru.netology.web.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTest {

    @BeforeEach
    void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Selenide.sleep(1000);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    private CardPaymentPage enterToPaymentPage() {
        open("http://localhost:8080");
        val dashboardPage = new DashboardPage();
        val cardPaymentPage = dashboardPage.goToPaymentPage();
        return cardPaymentPage;
    }

    @Test
    public void shouldPayByCard1() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithCard1Number());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.notificationOK.waitUntil(Condition.visible,15000);
        assertEquals("APPROVED", DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByCard2() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithCard2Number());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.notificationError.waitUntil(Condition.visible,15000);
        assertEquals("DECLINED", DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByNotProvidedCard() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithNotProvidedCardNumber());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.notificationError.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }
//
    @Test
    public void shouldNotPayByInvalidCard() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithInvalidCardNumber());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByExpiredCardThisYear() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithExpiredDateCardThisYear());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByExpiredCardLastYear() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithExpiredDateCardLastYear());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByInvalidMonthNumber() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithInvalidMonthNumber());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByZeroMonthNumber() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithZeroMonthNumber());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByInvalidYearNumber() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithInvalidYearNumber());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByCyrillicCardOwner() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithCyrillicCardOwner());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }

    @Test
    public void shouldNotPayByInvalidCVC() {
        val cardPaymentPage = enterToPaymentPage();
        cardPaymentPage.setAllValues(DataHelper.getDataWithInvalidCVC());
        cardPaymentPage.continueButton.click();
        cardPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
        assertEquals(null, DataBaseHelper.checkStatusInPayment());
    }

}