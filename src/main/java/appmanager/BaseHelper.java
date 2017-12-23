package appmanager;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Базовые действия над элементами, ожидания.
 *
 */
public class BaseHelper {

  private WebDriverWait webDriverWait;
  protected WebDriver wd;


  public BaseHelper(WebDriver wd) {
    this.wd = wd;
    this.webDriverWait = new WebDriverWait(wd, 10);
  }

  protected WebElement waitElementIsVisible(WebElement element) {
    return (webDriverWait.until(ExpectedConditions.visibilityOf(element)));
  }

  protected void waitPageLoad(String title) {
    webDriverWait.until(ExpectedConditions.titleIs(title));
  }

  protected void waitText(WebElement element, String text) {
    webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
  }

  protected void waitAttributeValue(WebElement element, String text) {
    webDriverWait.until(ExpectedConditions.attributeToBe(element, "value", text));
  }

  protected void click(WebElement element) {
    waitElementIsVisible(element).click();
  }

  protected void nextElement(WebElement element) {
    element.sendKeys(Keys.TAB);
  }

  protected void type(WebElement element, String value) {
    if (value != null) {
      click(element);
      element.sendKeys("");
      element.sendKeys(value);
    }
  }

  protected void typeWithLostFocus(WebElement element, String value) {
    type(element, value);
    nextElement(element);
  }

  protected List<String> getElementsText(List<WebElement> elements) {
    if(elements.size() != 0) {
      List<String> elementsText = elements.stream().map((p) -> p.getText()).collect(Collectors.toList());
      return elementsText;
    } else {
      Assert.fail("Массив не содержит элементов");
      return null;
    }
  }

}
