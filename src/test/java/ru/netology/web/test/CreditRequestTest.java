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
import ru.netology.web.page.CreditRequestPaymentPage;
import ru.netology.web.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditRequestTest {

    @BeforeEach
    void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Selenide.sleep(1000);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    private CreditRequestPaymentPage enterToCreditRequestPage() {
        open("http://localhost:8080");
        val dashboardPage = new DashboardPage();
        val creditRequestPaymentPage = dashboardPage.goToCreditRequestPage();
        return creditRequestPaymentPage;
    }

//    @Test
//    public void shouldPayInCreditByCard1() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithCard1Number());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.notificationOK.waitUntil(Condition.visible,15000);
//        assertEquals("APPROVED", DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByCard2() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithCard2Number());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.notificationError.waitUntil(Condition.visible,15000);
//        assertEquals("DECLINED", DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByNotProvidedCard() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithNotProvidedCardNumber());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.notificationError.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByInvalidCard() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithInvalidCardNumber());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByExpiredCardThisYear() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithExpiredDateCardThisYear());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByExpiredCardLastYear() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithExpiredDateCardLastYear());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByInvalidMonthNumber() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithInvalidMonthNumber());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByZeroMonthNumber() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithZeroMonthNumber());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByInvalidYearNumber() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithInvalidYearNumber());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByCyrillicCardOwner() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithCyrillicCardOwner());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }
//
//    @Test
//    public void shouldNotPayInCreditByInvalidCVC() {
//        val creditRequestPaymentPage = enterToCreditRequestPage();
//        creditRequestPaymentPage.setAllValues(DataHelper.getDataWithInvalidCVC());
//        creditRequestPaymentPage.continueButton.click();
//        creditRequestPaymentPage.inputInvalid.waitUntil(Condition.visible,15000);
//        assertEquals(null, DataBaseHelper.checkStatusInCredit());
//    }

}