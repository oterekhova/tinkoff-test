package communalpayments;

import appmanager.BaseHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Работа со страницей поставщика ЖКУ-Москва https://www.tinkoff.ru/zhku-moskva/
 *
 */
public class ZhkuMoskvaForm extends BaseHelper {

    public ZhkuMoskvaForm(WebDriver wd) {
        super(wd);
    }

    @Step("Перейти в Оплатить ЖКУ в Москве")
    public ZhkuMoskvaPayForm payActivate() {
        click(wd.findElement(By.xpath("//*[text()='Оплатить ЖКУ в Москве']")));
        waitPayForm();
        return new ZhkuMoskvaPayForm(wd);
    }

    @Step("Ожидаем загрузку страницы")
    private void waitPayForm() {
        waitPageLoad("ЖКУ-Москва | Онлайн-оплата ЖКУ-Москва без комиссии");
        Assert.assertTrue(wd.getCurrentUrl().equals("https://www.tinkoff.ru/zhku-moskva/oplata/?tab=pay"));
    }

}
