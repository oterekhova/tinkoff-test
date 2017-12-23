package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Работа с главным меню на стартовой странице https://tinkoff.ru/
 *
 */
public class MainMenuHelper extends BaseHelper {

    public MainMenuHelper(WebDriver wd) {
        super(wd);
    }

    @Step("Перейти в меню Платежи")
    public void payments() {
        WebElement element = wd.findElement(By.xpath("//li[contains(@class,'HeaderMenuItem')]//a[@href='/payments/']/span"));
        click(element);
        waitPageLoad("Tinkoff.ru: платежи и переводы денег");
    }

}
