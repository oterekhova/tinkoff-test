package paymentsearch;

import appmanager.BaseHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Результат поиска платежй https://www.tinkoff.ru/payments/
 *
 */
public class SearchResultForm extends BaseHelper {

  private List<WebElement> listResult;

  public SearchResultForm(WebDriver wd) {
    super(wd);
    listResult = wd.findElements(By.xpath("//*[contains(@class, 'SearchSuggest__entryText')]/div[1]"));
  }

  @Step("Проверить {0} в списке {1}")
  public SearchResultForm findAndCheckIndex(String text, int index){
    List<String> textResult = getElementsText(listResult);
    Assert.assertEquals(index, textResult.indexOf(text));
    return this;
  }

  @Step("Выбрать {0} из списка")
  public SearchResultForm select(String text){
    List<String> textResult = getElementsText(listResult);
    listResult.get(textResult.indexOf(text)).click();
    return this;
  }

}
