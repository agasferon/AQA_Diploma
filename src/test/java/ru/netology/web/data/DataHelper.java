package ru.netology.web.data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
  private DataHelper() {}

  static String getRandomName(){
    Faker faker = new Faker(new Locale("EN"));
    return faker.name().firstName() + " " + faker.name().lastName();
  }

  static String getRandomCyrillicName(){
    Faker faker = new Faker(new Locale("RU"));
    return faker.name().firstName() + " " + faker.name().lastName();
  }

  static String getRandomMonth(){
    String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    Random random = new Random();
    return month[random.nextInt(month.length)];
  }

  static String getLastMonth(){
    LocalDate date = LocalDate.now().minusMonths(1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
    return formatter.format(date);
  }

  static String getRandomYear(){
    Random random = new Random();
    LocalDate date = LocalDate.now().plusYears(random.nextInt(3) + 1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
    return formatter.format(date);
  }

  static String getThisYear(){
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
    return formatter.format(date);
  }

  static String getLastYear(){
    LocalDate date = LocalDate.now().minusYears(1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
    return formatter.format(date);
  }

  static String getRandomCVC(){
    Random random = new Random();
    return String.valueOf(random.nextInt(899) + 100);
  }

  static String getInvalidCVC(){
    Random random = new Random();
    return String.valueOf(random.nextInt(89) + 10);
  }

  static String getRandomCardNumber(){
    Faker faker = new Faker();
    return faker.finance().creditCard(CreditCardType.MASTERCARD).replace("-","");
  }

  public static Card getDataWithCard1Number(){
    return new Card("4444 4444 4444 4441", getRandomMonth(), getRandomYear(), getRandomName(), getRandomCVC());
  }

  public static Card getDataWithCard2Number(){
    return new Card("4444 4444 4444 4442", getRandomMonth(), getRandomYear(), getRandomName(), getRandomCVC());
  }

  public static Card getDataWithNotProvidedCardNumber(){
    return new Card(getRandomCardNumber(), getRandomMonth(), getRandomYear(), getRandomName(), getRandomCVC());
  }

  public static Card getDataWithInvalidCardNumber(){
    return new Card(getRandomCardNumber().substring(0,14), getRandomMonth(), getRandomYear(), getRandomName(), getRandomCVC());
  }

  public static Card getDataWithExpiredDateCardThisYear(){
    if (getLastMonth().intern() == "12") {
      return new Card("4444 4444 4444 4441", getLastMonth(), getLastYear(), getRandomName(), getRandomCVC());
    } else {
      return new Card("4444 4444 4444 4441", getLastMonth(), getThisYear(), getRandomName(), getRandomCVC());
    }
  }

  public static Card getDataWithExpiredDateCardLastYear(){
    return new Card("4444 4444 4444 4441", getRandomMonth(), getLastYear(), getRandomName(), getRandomCVC());
  }

  public static Card getDataWithInvalidMonthNumber(){
    return new Card("4444 4444 4444 4441", "15", getRandomYear(), getRandomName(), getRandomCVC());
  }

  public static Card getDataWithZeroMonthNumber(){
    return new Card("4444 4444 4444 4441", "00", getRandomYear(), getRandomName(), getRandomCVC());
  }

  public static Card getDataWithInvalidYearNumber(){
    return new Card("4444 4444 4444 4441", getRandomMonth(), "66", getRandomName(), getRandomCVC());
  }

  public static Card getDataWithCyrillicCardOwner(){
    return new Card("4444 4444 4444 4441", getRandomMonth(), getRandomYear(), getRandomCyrillicName(), getRandomCVC());
  }

  public static Card getDataWithInvalidCVC(){
    return new Card("4444 4444 4444 4441", getRandomMonth(), getRandomYear(), getRandomName(), getInvalidCVC());
  }

}