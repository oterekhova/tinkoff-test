package communalpayments;

import appmanager.BaseHelper;
import model.PaymentData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Работа со формой оплаты поставщика ЖКУ-Москва https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay
 *
 */
public class ZhkuMoskvaPayForm extends BaseHelper {

  private WebElement payerCodeElement;
  private WebElement periodElement;
  private WebElement insuranceElement;
  private WebElement amountElement;
  private WebElement payBtn;
  private PaymentData paymentData;

  private String errorXpathTemplate = "//div[contains(@class,'%s')]//div[@data-qa-file='UIFormRowError']";

  public ZhkuMoskvaPayForm(WebDriver wd) {
    super(wd);
    payerCodeElement = wd.findElement(By.id("payerCode"));
    periodElement = wd.findElement(By.id("period"));
    insuranceElement = wd.findElement(By.id("insurance"));
    amountElement = wd.findElement(By.xpath("//*[contains(@class,'row_combination')]//input[1]"));
    payBtn = wd.findElement(By.xpath("//*[@class='ui-form__row']//button"));
  }

  @Step("Перейти в Оплатить ЖКУ в Москве")
  public void payActivate() {
    click(wd.findElement(By.xpath("//*[text()='Оплатить ЖКУ в Москве']")));
  }

  @Step("Ожидаем загрузку страницы")
  public void waitPayForm() {
    waitPageLoad("ЖКУ-Москва | Онлайн-оплата ЖКУ-Москва без комиссии");
    Assert.assertTrue(wd.getCurrentUrl().equals("https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay"));
  }

  @Step("Нажать на Оплатить ЖКУ в Москве")
  public void pay() {
    click(payBtn);
  }

  @Step("Заполнить код плательщика")
  public ZhkuMoskvaPayForm fillPayerCode() {
    if (paymentData.getPayerCode() != null) {
      String value = paymentData.getPayerCode().get("value");
      typeWithLostFocus(payerCodeElement, value);
      waitAttributeValue(payerCodeElement, getExpectedPayerCode(value));
    }
    return this;
  }

  @Step("Заполнить период оплаты")
  public ZhkuMoskvaPayForm fillPeriod() {
    if (paymentData.getPeriod() != null) {
      String value = paymentData.getPeriod().get("value");
      typeWithLostFocus(periodElement, value);
      waitAttributeValue(periodElement, getExpectedPeriod(value));
    }
    return this;
  }

  @Step("Заполнить сумму добровольного страхования")
  public ZhkuMoskvaPayForm fillInsurance() {
    if (paymentData.getInsurance() != null) {
      String value = paymentData.getInsurance().get("value");
      typeWithLostFocus(insuranceElement, value);
      waitAttributeValue(insuranceElement, value);
    }
    return this;
  }

  @Step("Заполнить сумму платежа")
  public ZhkuMoskvaPayForm fillAmount() {
    if (paymentData.getAmount() != null) {
      String value = paymentData.getAmount().get("value");
      typeWithLostFocus(amountElement, paymentData.getAmount().get("value"));
      waitAttributeValue(amountElement, value);
    }
    return this;
  }

  @Step("Если есть, проверить текст ошибки для кода плательщика")
  public ZhkuMoskvaPayForm checkPayerCodeError() {
    String expectedError = paymentData.getPayerCode().get("error");
    if(expectedError != null) {
      String actualError = wd.findElement(By.xpath(String.format(errorXpathTemplate, "row_text"))).getText();
      Assert.assertEquals(expectedError, actualError);
    }
    return this;
  }

  @Step("Если есть, проверить текст ошибки для периода")
  public ZhkuMoskvaPayForm checkPeriodError() {
    String expectedError = paymentData.getPeriod().get("error");
    if(expectedError != null) {
      String actualError = wd.findElement(By.xpath(String.format(errorXpathTemplate, "row_date"))).getText();
      Assert.assertEquals(expectedError, actualError);
    }
    return this;
  }

  @Step("Если есть, проверить текст ошибки для суммы платежа")
  public ZhkuMoskvaPayForm checkAmountError() {
    String expectedError = paymentData.getAmount().get("error");
    if(expectedError != null) {
      String actualError = wd.findElement(By.xpath(String.format(errorXpathTemplate, "row_combination"))).getText();
      Assert.assertEquals(expectedError, actualError);
    }
    return this;
  }

  public void setPaymentData(PaymentData paymentData) {
    this.paymentData = paymentData;
  }

  private String getExpectedPeriod(String value) {
    return value.replaceAll("[^0-9.]", "");
  }

  private String getExpectedPayerCode(String value) {
    value = value.replaceAll("[^0-9]", "");
    if(value.length() > 10) {
      value = value.substring(0, 10);
    }
    return value;
  }

}
