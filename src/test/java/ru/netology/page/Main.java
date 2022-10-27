package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.exactText;

import static com.codeborne.selenide.Selenide.$x;

public class Main {
    private SelenideElement buttonPay = $x("[type='button']").find(String.valueOf(exactText("Купить")));
    private SelenideElement buttonCredit = $x("[type='button']").find(String.valueOf(exactText("Купить в кредит")));

    public void clickButtonPay() {
        buttonPay.click();
    }

    public void clickButtonCredit() {
        buttonCredit.click();
    }
}
