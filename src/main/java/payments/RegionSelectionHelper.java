package payments;

import appmanager.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Работа с регионами https://www.tinkoff.ru/payments/categories/kommunalnie-platezhi/
 *
 */
public class RegionSelectionHelper extends BaseHelper {

  public RegionSelectionHelper(WebDriver wd) {
    super(wd);
  }

  @Step("Выбрать регион {0}. Проверить заголовок ЖКХ в {1}")
  public void select(String region, String validateText) {;
    WebElement catalogLink = wd.findElement(By.xpath("//div[@data-qa-file='PaymentsCatalogHeader']/span[2]"));
    if(!catalogLink.getText().equals(validateText)) {
      click(catalogLink);
      click(wd.findElement(By.xpath("//*[text()='" + region + "']")));
      waitText(catalogLink, validateText);
    }
  }

}
