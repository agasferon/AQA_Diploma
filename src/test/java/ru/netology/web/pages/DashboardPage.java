package ru.netology.web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private SelenideElement heading = $$("h2").find(text("Путешествие дня"));
  private SelenideElement buyButton = $$("button").find(exactText("Купить"));
  private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public CardPaymentPage goToPaymentPage() {
    buyButton.click();
    return new CardPaymentPage();
  }

  public CreditRequestPaymentPage goToCreditRequestPage() {
    creditButton.click();
    return new CreditRequestPaymentPage();
  }

}