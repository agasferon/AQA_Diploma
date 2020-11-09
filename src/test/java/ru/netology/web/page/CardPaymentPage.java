package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.Card;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardPaymentPage {
    public SelenideElement heading = $$("h3").find(text("Оплата по карте"));
    public SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    public SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    public SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    public SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    public SelenideElement cvcField = $(byText("CVC/CVV")).parent().$(".input__control");
    public SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    public SelenideElement notificationOK = $(".notification_status_ok");
    public SelenideElement notificationError = $(".notification_status_error");
    public SelenideElement inputInvalid = $(".input__sub");

    public CardPaymentPage() {
        heading.shouldBe(visible);
    }

    public void setAllValues(Card card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
    }

}