package appmanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import communalpayments.ProviderHelper;
import payments.RegionSelectionHelper;
import payments.PaymentsHelper;
import paymentsearch.SearchForm;
import communalpayments.ZhkuMoskvaForm;

/**
 * Управление браузером. Инициализация классов, необходимых для работы с элементами на странице.
 *
 */
public class ApplicationManager {

    WebDriver wd;
    private String baseurl = "https://tinkoff.ru/";
    private MainMenuHelper mainMenuHelper;
    private PaymentsHelper paymentsHelper;
    private ProviderHelper providerHelper;
    private RegionSelectionHelper regionSelectionHelper;

    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        wd = new ChromeDriver(options);
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get(baseurl);

        mainMenuHelper = new MainMenuHelper(wd);
        paymentsHelper = new PaymentsHelper(wd);
        providerHelper = new ProviderHelper(wd);
        regionSelectionHelper = new RegionSelectionHelper(wd);
    }

    public void stop() {
        wd.quit();
    }

    public MainMenuHelper goTo() {
        return mainMenuHelper;
    }

    public PaymentsHelper payments() { return paymentsHelper; }

    public ZhkuMoskvaForm zhkuMoskva() { return new ZhkuMoskvaForm(wd); }

    public SearchForm searchForm() { return new SearchForm(wd); }

    public ProviderHelper providers() { return providerHelper; }

    public RegionSelectionHelper regions() { return regionSelectionHelper; }

}
