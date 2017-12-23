package communalpayments;

import appmanager.BaseHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Работа с поставщиками https://www.tinkoff.ru/payments/categories/kommunalnie-platezhi/
 *
 */
public class ProviderHelper extends BaseHelper{

  public ProviderHelper(WebDriver wd) {
    super(wd);
  }

  @Step("Проверить, что {0} в списке поставщиков {1}")
  public void findAndCheckIndex(String expectedProvider, int expectedIndex) {
    Assert.assertEquals(expectedIndex, find(expectedProvider));
  }

  @Step("Выбрать поставщика {0}")
  public void select(String name) {
    click(wd.findElement(By.linkText(name)));
  }

  public int find(String provider) {
    List<String> elementsText = getElementsText(wd.findElements(By.xpath("//li[contains(@class, 'ui-menu')]//span[2]//span")));
    return elementsText.indexOf(provider);
  }

}
