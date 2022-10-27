package ru.netology.test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import ru.netology.data.DBHelper;
import ru.netology.data.Helper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.PaymentCard;

import static com.codeborne.selenide.Selenide.*;

public class TestUiPayment {
    private PaymentCard order = new PaymentCard();

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
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("2.Positive - DeclinedNumber")
    void shouldPayCardDeclinedNumber() {
        order.fillPayForm(Helper.getDeclinedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::payDeclined, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("3.Negative - RandomNumber")
    void shouldPayCardRandomNumber() {
        order.fillPayForm(Helper.getRandomNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("4.Negative - EmptyNumber")
    void shouldPayCardEmptyNumber() {
        order.fillPayForm(Helper.getEmptyNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("5.Negative - ZeroNumber")
    void shouldPayCardZeroNumber() {
        order.fillPayForm(Helper.getZeroNumber(), Helper.getZeroMonth(), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("6.Negative - SpacesNumber")
    void shouldPayCardSpacesNumber() {
        order.fillPayForm(Helper.getSpacesNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("7.Negative - RandomNumber1")
    void shouldPayCardRandomNumber1() {
        order.fillPayForm(Helper.getRandomOneNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("8.Negative - RandomNumber15")
    void shouldPayCardRandomNumber15() {
        order.fillPayForm(Helper.getRandomFifteenNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("9.Negative - RandomNumber17")
    void shouldPayCardRandomNumber17() {
        order.fillPayForm(Helper.getRandomSeventeenNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("10.Negative - RandomNumberWithLetter")
    void shouldPayCardRandomNumberWithLetter() {
        order.fillPayForm(Helper.getRandomNumberWithLetter(), Helper.getZeroMonth(), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("11.Negative - RandomSymbolNumber")
    void shouldPayCardRandomSymbolNumber() {
        order.fillPayForm(Helper.getRandomSymbolNumber(), Helper.getZeroMonth(), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatNumber, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("12.Positive - FirstMonth")
    void shouldPayCardFirstMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getFirstMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("13.Positive - SecondMonth")
    void shouldPayCardSecondMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getSecondMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("14.Positive - ElevenMonth")
    void shouldPayCardElevenMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getElevenMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("15.Positive - TwelveMonth")
    void shouldPayCardTwelveMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getTwelveMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("16.Negative - EmptyMonth")
    void shouldPayCardEmptyMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getEmptyMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::payAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("17.Negative - ZeroMonth")
    void shouldPayCardZeroMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getZeroMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorPeriodMonth, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("18.Negative - SpacesMonth")
    void shouldPayCardSpacesMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getSpacesMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorPeriodMonth, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("19.Negative - ThirteenMonth")
    void shouldPayCardThirteenMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getThirteenMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("20.Negative - RandomOneNumberMonth")
    void shouldPayCardRandomOneNumberMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getRandomOneNumberMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("21.Negative - RandomLetterMonth")
    void shouldPayCardRandomLetterMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getRandomLetterMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("22.Negative - RandomSymbolMonth")
    void shouldPayCardRandomSymbolMonth() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getRandomSymbolMonth(), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatMonth, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("23.Positive - CurrentYear")
    void shouldPayCardCurrentYear() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("24.Positive - CurrentYearPlus1")
    void shouldPayCardCurrentYearPlus1() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("25.Positive - CurrentYearPlus2")
    void shouldPayCardCurrentYearPlus2() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(2),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("26.Positive - CurrentYearPlus5")
    void shouldPayCardCurrentYearPlus5() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(5),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("27.Negative - CurrentYearPlus6")
    void shouldPayCardCurrentYearPlus6() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(6),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorSpecifiedPeriodCard, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("28.Negative - EmptyYear")
    void shouldPayCardEmptyYear() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getEmptyYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::payDenialCount,
                order::orderDenialCount);
    }


    @Test
    @DisplayName("29.Negative - RandomOneNumberYear")
    void shouldPayCardRandomOneNumberYear() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getRandomOneNumberYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("30.Negative - CurrentYearMinus1")
    void shouldPayCardCurrentYearMinus1() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(-1),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorPeriodCard, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("31.Negative - ZeroYear")
    void shouldPayCardZeroYear() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getZeroYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("32.Negative - SpacesYear")
    void shouldPayCardSpacesYear() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getSpacesYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("33.Negative - RandomLetterYear")
    void shouldPayCardRandomLetterYear() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getRandomLetterYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("34.Negative - RandomSymbolsYear")
    void shouldPayCardRandomSymbolsYear() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getRandomSymbolsYear(),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatYear, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("35.Positive - FirstNameAndLastNameOneSymbol")
    void shouldFirstNameAndLastNameOneSymbol() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("???"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("36.Positive - FirstNameAndLastNameTwoSymbols")
    void shouldFirstNameAndLastNameTwoSymbols() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("?????"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("37.Positive - EnOwner19Symbols")
    void shouldEnOwner19Symbols() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("???????????????????"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("38.Positive - EnOwner20Symbols")
    void shouldEnOwner20Symbols() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("????????????????????"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("39.Negative - EnOwner2Symbols")
    void shouldEnOwner2Symbols() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("??"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::payAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("40.Negative - EnOwner21Symbols")
    void shouldEnOwner21Symbols() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getQuantitySymbolOwner("?????????????????????"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::payAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("41.Negative - CyrillicOwner")
    void shouldCyrillicOwner() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getCyrillicOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("42.Negative - EmptyOwner")
    void shouldEmptyOwner() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEmptyOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorEmptyField, order::payAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("43.Negative - NumbersOwner")
    void shouldNumbersOwner() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getNumbersOwner("########"), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::payAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("44.Negative - SymbolOwner")
    void shouldSymbolOwner() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getSymbolOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::payAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("45.Negative - SpacesOwner")
    void shouldSpacesOwner() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getSpacesOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatOwner, order::payAcceptCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("46.Positive - ThreeCVCSymbols")
    void shouldThreeCVCSymbols() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getCVC());
        order.send();
        Assertions.assertAll(order::positiveMessage, order::payApproved, order::payAcceptCount,
                order::orderAcceptCount);
    }

    @Test
    @DisplayName("47.Negative - OneSymbolCVC")
    void shouldOneSymbolCVC() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getOneSymbolCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("48.Negative - TwoSymbolsCVC")
    void shouldTwoSymbolsCVC() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getTwoSymbolsCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("49.Negative - FourSymbolsCVC")
    void shouldFourSymbolsCVC() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getFourSymbolsCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("50.Negative - EmptyCVC")
    void shouldEmptyCVC() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getEmptyCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("51.Negative - ZeroCVC")
    void shouldZeroCVC() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getZeroCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("52.Negative - SpacesCVC")
    void shouldSpacesCVC() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getSpacesCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("53.Negative - RandomLetterCVC")
    void shouldRandomLetterCVC() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getRandomLetterCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::payDenialCount,
                order::orderDenialCount);
    }

    @Test
    @DisplayName("54.Negative - SymbolsCVC")
    void shouldSymbolsCVC() {
        order.fillPayForm(Helper.getApprovedNumber(), Helper.getMonth(0), Helper.getYear(0),
                Helper.getEnOwner(), Helper.getSymbolsCVC());
        order.send();
        Assertions.assertAll(order::denialMessage, order::errorFormatCVC, order::payDenialCount,
                order::orderDenialCount);
    }
}
