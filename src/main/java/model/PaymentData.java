package model;

import java.util.Map;

/**
 * Описание данных для Платежей
 *
 */
public class PaymentData {

    private Map<String, String> payerCode;
    private Map<String, String> period;
    private Map<String, String> insurance;
    private Map<String, String> amount;

    public Map<String, String> getPayerCode() {
        return payerCode;
    }

    public PaymentData withPayerCode(Map<String, String> payerCode) {
        this.payerCode = payerCode;
        return this;
    }

    public Map<String, String> getPeriod() {
        return period;
    }

    public PaymentData withPeriod(Map<String, String> period) {
        this.period = period;
        return this;
    }

    public Map<String, String> getInsurance() {
        return insurance;
    }

    public PaymentData withInsurance(Map<String, String> insurance) {
        this.insurance = insurance;
        return this;
    }

    public Map<String, String> getAmount() {
        return amount;
    }

    public PaymentData withAmount(Map<String, String> amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String toString() {
        return "PaymentData{" +
                "payerCode=" + payerCode +
                ", period=" + period +
                ", insurance=" + insurance +
                ", amount=" + amount +
                '}';
    }
}
