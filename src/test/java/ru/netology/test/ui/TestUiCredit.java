package ru.netology.test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.Helper;
import ru.netology.page.PaymentCreditCard;

import static com.codeborne.selenide.Selenide.open;

public class TestUiCredit {
    private PaymentCreditCard order = new PaymentCreditCard();

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clear() {
        DBHelper.clear();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("1.Positive - ApprovedNumber")
    void shouldPayCardApprovedNumber() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("2.Positive - DeclinedNumber")
    void shouldPayCardDeclinedNumber() {
        order.fillCreditForm(Helper.getDeclinedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::creditDeclined, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("3.Negative - RandomNumber")
    void shouldPayCardRandomNumber() {
        order.fillCreditForm(Helper.getRandomNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("4.Negative - EmptyNumber")
    void shouldPayCardEmptyNumber() {
        order.fillCreditForm(Helper.getEmptyNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("5.Negative - ZeroNumber")
    void shouldPayCardZeroNumber() {
        order.fillCreditForm(Helper.getZeroNumber(), Helper.getZeroMonth(), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("6.Negative - SpacesNumber")
    void shouldPayCardSpacesNumber() {
        order.fillCreditForm(Helper.getSpacesNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("7.Negative - RandomNumber1")
    void shouldPayCardRandomNumber1() {
        order.fillCreditForm(Helper.getRandomOneNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("8.Negative - RandomNumber15")
    void shouldPayCardRandomNumber15() {
        order.fillCreditForm(Helper.getRandomFifteenNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("9.Negative - RandomNumber17")
    void shouldPayCardRandomNumber17() {
        order.fillCreditForm(Helper.getRandomSeventeenNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("10.Negative - RandomNumberWithLetter")
    void shouldPayCardRandomNumberWithLetter() {
        order.fillCreditForm(Helper.getRandomNumberWithLetter(), Helper.getZeroMonth(), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("11.Negative - RandomSymbolNumber")
    void shouldPayCardRandomSymbolNumber() {
        order.fillCreditForm(Helper.getRandomSymbolNumber(), Helper.getZeroMonth(), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("12.Positive - FirstMonth")
    void shouldPayCardFirstMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getFirstMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("13.Positive - SecondMonth")
    void shouldPayCardSecondMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getSecondMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("14.Positive - ElevenMonth")
    void shouldPayCardElevenMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getElevenMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("15.Positive - TwelveMonth")
    void shouldPayCardTwelveMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getTwelveMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("16.Negative - EmptyMonth")
    void shouldPayCardEmptyMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getEmptyMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::creditAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("17.Negative - ZeroMonth")
    void shouldPayCardZeroMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getZeroMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorPeriodMonth, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("18.Negative - SpacesMonth")
    void shouldPayCardSpacesMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getSpacesMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorPeriodMonth, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("19.Negative - ThirteenMonth")
    void shouldPayCardThirteenMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getThirteenMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("20.Negative - RandomOneNumberMonth")
    void shouldPayCardRandomOneNumberMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getRandomOneNumberMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("21.Negative - RandomLetterMonth")
    void shouldPayCardRandomLetterMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getRandomLetterMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("22.Negative - RandomSymbolMonth")
    void shouldPayCardRandomSymbolMonth() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getRandomSymbolMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("23.Positive - CurrentYear")
    void shouldPayCardCurrentYear() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("24.Positive - CurrentYearPlus1")
    void shouldPayCardCurrentYearPlus1() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("25.Positive - CurrentYearPlus2")
    void shouldPayCardCurrentYearPlus2() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(2),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("26.Positive - CurrentYearPlus5")
    void shouldPayCardCurrentYearPlus5() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(5),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("27.Negative - CurrentYearPlus6")
    void shouldPayCardCurrentYearPlus6() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(6),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorSpecifiedPeriodCard, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("28.Negative - EmptyYear")
    void shouldPayCardEmptyYear() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getEmptyYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::creditDenialCount,
                order::orderDenialCount);
    }


    @Test
    @DisplayName("29.Negative - RandomOneNumberYear")
    void shouldPayCardRandomOneNumberYear() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getRandomOneNumberYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("30.Negative - CurrentYearMinus1")
    void shouldPayCardCurrentYearMinus1() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(-1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorPeriodCard, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("31.Negative - ZeroYear")
    void shouldPayCardZeroYear() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getZeroYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("32.Negative - SpacesYear")
    void shouldPayCardSpacesYear() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getSpacesYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("33.Negative - RandomLetterYear")
    void shouldPayCardRandomLetterYear() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getRandomLetterYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("34.Negative - RandomSymbolsYear")
    void shouldPayCardRandomSymbolsYear() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getRandomSymbolsYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("35.Positive - FirstNameAndLastNameOneSymbol")
    void shouldFirstNameAndLastNameOneSymbol() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("???"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("36.Positive - FirstNameAndLastNameTwoSymbols")
    void shouldFirstNameAndLastNameTwoSymbols() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("?????"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("37.Positive - EnOwner19Symbols")
    void shouldEnOwner19Symbols() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("???????????????????"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("38.Positive - EnOwner20Symbols")
    void shouldEnOwner20Symbols() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("????????????????????"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("39.Negative - EnOwner2Symbols")
    void shouldEnOwner2Symbols() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("??"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::creditAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("40.Negative - EnOwner21Symbols")
    void shouldEnOwner21Symbols() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("?????????????????????"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::creditAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("41.Negative - CyrillicOwner")
    void shouldCyrillicOwner() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getCyrillicOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("42.Negative - EmptyOwner")
    void shouldEmptyOwner() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEmptyOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorEmptyField, order::creditAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("43.Negative - NumbersOwner")
    void shouldNumbersOwner() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getNumbersOwner("########"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::creditAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("44.Negative - SymbolOwner")
    void shouldSymbolOwner() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getSymbolOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::creditAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("45.Negative - SpacesOwner")
    void shouldSpacesOwner() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getSpacesOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::creditAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("46.Positive - ThreeCVCSymbols")
    void shouldThreeCVCSymbols() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::creditApproved, order::creditAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("47.Negative - OneSymbolCVC")
    void shouldOneSymbolCVC() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getOneSymbolCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("48.Negative - TwoSymbolsCVC")
    void shouldTwoSymbolsCVC() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getTwoSymbolsCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("49.Negative - FourSymbolsCVC")
    void shouldFourSymbolsCVC() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getFourSymbolsCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("50.Negative - EmptyCVC")
    void shouldEmptyCVC() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getEmptyCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("51.Negative - ZeroCVC")
    void shouldZeroCVC() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getZeroCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("52.Negative - SpacesCVC")
    void shouldSpacesCVC() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getSpacesCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("53.Negative - RandomLetterCVC")
    void shouldRandomLetterCVC() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getRandomLetterCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::creditDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("54.Negative - SymbolsCVC")
    void shouldSymbolsCVC() {
        order.fillCreditForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getSymbolsCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::creditDenialCount,
                order::orderDenialCount);
    }
}
