package paymentsearch;

import appmanager.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Поиск платежй https://www.tinkoff.ru/payments/
 *
 */
public class SearchForm extends BaseHelper {

  WebElement searchField;

  public SearchForm(WebDriver wd) {
    super(wd);
    searchField = wd.findElement(By.xpath("//*[contains(@class, 'SearchSuggested__container')]//input"));
  }

  @Step("Поиск {0}")
  public SearchResultForm search(String text) {
    type(searchField, text);
    return new SearchResultForm(wd);
  }

}
