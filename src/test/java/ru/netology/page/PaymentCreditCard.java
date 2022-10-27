package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DBHelper;

import java.time.Duration;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCreditCard {
    private final Main main = new Main();

    private SelenideElement buttonContinue = $(".form-field button");
    private SelenideElement cardTitle = $("h3.heading")
            .find(String.valueOf(exactText("Оплата по редитной карте")));
    private SelenideElement cardNumber = $(".input__control").find(String.valueOf(exactText("Номер карты")));
    private SelenideElement cardMonth = $(".input__control").find(String.valueOf(exactText("Месяц")));
    private SelenideElement cardYear = $(".input__control").find(String.valueOf(exactText("Год")));
    private SelenideElement cardOwner = $(".input__control").find(String.valueOf(exactText("Владелец")));
    private SelenideElement cardCVC = $(".input__control").find(String.valueOf(exactText("CVC/CVV")));

    private SelenideElement errorNumber = $("//span[contains(text(),'Номер карты')]")
            .parent().$(".input__sub");
    private SelenideElement errorMonth = $("//span[contains(text(),'Месяц')]")
            .parent().$(".input__sub");
    private SelenideElement errorYear = $("//span[contains(text(),'Год')]")
            .parent().$(".input__sub");
    private SelenideElement errorOwner = $("//span[contains(text(),'Владелец')]")
            .parent().$(".input__sub");
    private SelenideElement errorCVC = $("//span[contains(text(),'CVC/CVV')]")
            .parent().$(".input__sub");

    private SelenideElement messageSuccess  = $(".notification_status_ok")
            .find(String.valueOf(exactText("Успешно")));
    private SelenideElement messageApprove  = $(".notification_status_ok")
            .find(String.valueOf(exactText("Операция одобрена Банком.")));
    private SelenideElement messageError  = $(".notification_status_error")
            .find(String.valueOf(exactText("Ошибка")));
    private SelenideElement messageDecline  = $(".notification_status_error")
            .find(String.valueOf(exactText("Ошибка! Банк отказал в проведении операции.")));

    public void fillCreditForm(String number, String month, String year, String holder, String cvc) {
        main.clickButtonCredit();
        cardTitle.shouldBe(Condition.text("Кредит по данным карты"));
        cardNumber.setValue(number);
        cardMonth.setValue(month);
        cardYear.setValue(year);
        cardOwner.setValue(holder);
        cardCVC.setValue(cvc);
    }

    public void send() {
        buttonContinue.click();
    }

    public void positiveMessage() {
        messageSuccess.shouldBe(Condition.text("Успешно"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        messageApprove.shouldBe(Condition.text("Операция одобрена Банком."),
                Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }
    public void denialMessage() {
        messageError.shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(10)).shouldBe(Condition.visible);
        messageDecline.shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."),
                Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public void errorFormatNumber() {
        errorNumber.shouldBe(visible, Condition.text("Неверный формат"));
    }

    public void errorFormatMonth() {
        errorMonth.shouldBe(visible, Condition.text("Неверный формат"));
    }

    public void errorPeriodMonth() {
        errorMonth.shouldBe(visible, Condition.text("Неверно указан срок действия карты"));
    }

    public void errorFormatYear() {
        errorYear.shouldBe(visible, Condition.text("Неверный формат"));
    }

    public void errorPeriodCard() {
        errorYear.shouldBe(visible, Condition.text("Истёк срок действия карты"));
    }

    public void errorSpecifiedPeriodCard() {
        errorYear.shouldBe(visible, Condition.text("Неверно указан срок действия карты"));
    }

    public void errorEmptyField() {
        errorOwner.shouldBe(visible, Condition.text("Поле обязательно для заполнения"));
    }

    public void errorFormatOwner() {
        errorOwner.shouldBe(visible, Condition.text("Неверный формат"));
    }

    public void errorFormatCVC() {
        errorCVC.shouldBe(visible, Condition.text("Неверный формат"));
    }

    public void creditApproved() {
        assertEquals("APPROVED", DBHelper.getCreditStatus());
    }

    public void creditDeclined() {
        assertEquals("DECLINED", DBHelper.getPaymentStatus());
    }

    public void creditAcceptCount() {
        assertEquals(1, DBHelper.getCreditCount());
    }

    public void creditDenialCount() {
        assertEquals(0, DBHelper.getPaymentCount());
    }

    public void orderAcceptCount() {
        assertEquals(1, DBHelper.getOrderCount());
    }

    public void orderDenialCount() {
        assertEquals(0, DBHelper.getOrderCount());
    }
}
