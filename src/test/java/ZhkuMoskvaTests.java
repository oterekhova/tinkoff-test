import model.PaymentData;
import org.testng.annotations.*;
import communalpayments.ZhkuMoskvaPayForm;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.HashMap;
import java.util.Map;

@Features("Коммунальные платежи")
@Stories("ЖКУ-Москва")
public class ZhkuMoskvaTests extends TestBase {

    public static final String FILLED_INCORRECT = "Поле заполнено некорректно";
    public static final String FILLED_WRONG = "Поле неправильно заполнено";
    public static final String MIN_AMOUNT_ERROR = "Минимальная сумма перевода — 10 \u20BD";
    public static final String MAX_AMOUNT_ERROR = "Максимальная сумма перевода — 15 000 \u20BD";
    public static final String REQUIRED_FIELD = "Поле обязательное";
    public static final String MOSCOW_REGION = "г. Москва";
    public static final String SP_REGION = "г. Санкт-Петербург";
    public static final String SP_REGION_VALIDATE = "Санкт-Петербурге";
    public static final String MOSCOW_REGION_VALIDATE = "Москве";
    public static final String MOSCOW_ZHKU = "ЖКУ-Москва";
    public ZhkuMoskvaPayForm zhkuMoskvaPayForm;

    @BeforeMethod
    public void preconditions() {
        app.goTo().payments();
        app.payments().selectCommunalPayment();
        app.regions().select(MOSCOW_REGION, MOSCOW_REGION_VALIDATE);
        app.providers().findAndCheckIndex(MOSCOW_ZHKU, 0);
        app.providers().select(MOSCOW_ZHKU);
    }

    @Test(dataProvider = "invalidGroup")
    public void negativePaymentTest(PaymentData paymentData) {
        zhkuMoskvaPayForm = app.zhkuMoskva().payActivate();
        zhkuMoskvaPayForm.setPaymentData(paymentData);
        zhkuMoskvaPayForm.fillPayerCode()
                .fillPeriod()
                .fillAmount();
        zhkuMoskvaPayForm.pay();
        zhkuMoskvaPayForm.checkPayerCodeError()
                .checkPeriodError()
                .checkAmountError();
    }

    @Test
    public void searchSuggestedTest() {
        app.goTo().payments();
        app.searchForm().search(MOSCOW_ZHKU)
                .findAndCheckIndex(MOSCOW_ZHKU, 0)
                .select(MOSCOW_ZHKU);
        app.zhkuMoskva().payActivate();
    }

    @Test
    public void providersTest() {
        app.goTo().payments();
        app.payments().selectCommunalPayment();
        app.regions().select(SP_REGION, SP_REGION_VALIDATE);
        app.providers().findAndCheckIndex(MOSCOW_ZHKU, -1);
    }

    @DataProvider
    public static Object[][] invalidGroup() {
        return new Object[][] { { new PaymentData().withPayerCode(genMap("", REQUIRED_FIELD))
                                        .withPeriod(genMap("", REQUIRED_FIELD))
                                        .withAmount(genMap("", REQUIRED_FIELD))},
                                { new PaymentData().withPayerCode(genMap("0", FILLED_WRONG))
                                        .withPeriod(genMap("0", FILLED_INCORRECT))
                                        .withAmount(genMap("0", MIN_AMOUNT_ERROR))},
                                { new PaymentData().withPayerCode(genMap("12445678910", null))
                                        .withPeriod(genMap("00.0000", FILLED_INCORRECT))
                                        .withAmount(genMap("20 000", MAX_AMOUNT_ERROR))}
        };
    }

    private static Map<String, String> genMap(String value, String errorMessage) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("value", value);
        map.put("error", errorMessage);
        return map;
    }
}
