package payments;

import appmanager.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Работа со страницей Платежи и Переводы https://www.tinkoff.ru/payments/
 *
 */
public class PaymentsHelper extends BaseHelper {

  public PaymentsHelper(WebDriver wd) {
    super(wd);
  }

  @Step("Выбрать Коммунальные платежи")
  public void selectCommunalPayment() {
    click(wd.findElement(By.linkText("ЖКХ")));
    waitPageLoad("Оплата ЖКХ без комиссии. Коммунальные платежи онлайн");
  }

}
