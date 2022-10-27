package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Helper {

    private static Faker faker = new Faker();
    private static Faker fakerEn = new Faker(new Locale("En"));
    private static Faker fakerRu = new Faker(new Locale("Ru"));

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String owner;
        String cvc;
    }

    public static String getApprovedNumber() {
        return "1111 2222 3333 4444";
    }

    public static String getDeclinedNumber() {
        return "5555 6666 7777 8888";
    }

    public static String getRandomNumber() {
        return faker.numerify("#### #### #### ####");
    }

    public static String getEmptyNumber() {
        return "";
    }

    public static String getZeroNumber() {
        return "0000 0000 0000 0000";
    }

    public static String getSpacesNumber() {
        return "                   ";
    }

    public static String getRandomOneNumber() {
        return faker.numerify("#");
    }

    public static String getRandomFifteenNumber() {
        return faker.numerify("#### #### #### ###");
    }

    public static String getRandomSeventeenNumber() {
        return faker.numerify("#### #### #### #### #");
    }

    public static String getRandomNumberWithLetter() {
        return faker.bothify("#### #### #### ###?");
    }

    public static String getRandomSymbolNumber() {
        return ("!@#$ %^&* ()_+ ~:><");
    }

    public static String getMonth(int plus) {
        return LocalDate.now().plusMonths(plus).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getSpacesMonth() {
        return "  ";
    }

    public static String getFirstMonth() {
        return "01";
    }

    public static String getSecondMonth() {
        return "02";
    }

    public static String getElevenMonth() {
        return "11";
    }

    public static String getTwelveMonth() {
        return "12";
    }

    public static String getThirteenMonth() {
        return "13";
    }

    public static String getRandomOneNumberMonth() {
        return faker.numerify("#");
    }

    public static String getRandomLetterMonth() {
        return faker.bothify("?#");
    }

    public static String getRandomSymbolMonth() {
        return ("+/");
    }

    public static String getYear(int plus) {
        return LocalDate.now().plusYears(plus).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getZeroYear() {
        return "00";
    }

    public static String getSpacesYear() {
        return "  ";
    }

    public static String getRandomOneNumberYear() {
        return faker.numerify("#");
    }

    public static String getRandomLetterYear() {
        return faker.bothify("?#");
    }

    public static String getRandomSymbolsYear() {
        return ("*-");
    }

    public static String getEnOwner() {
        return fakerEn.name().name().toUpperCase();
    }

    public static String getQuantitySymbolOwner(String count) {
        return fakerEn.letterify(count).toUpperCase();
    }

    public static String getEmptyOwner() {
        return "";
    }

    public static String getSpacesOwner() {
        return "                ";
    }

    public static String getSymbolOwner() {
        return "!@#$%^&*()_+ |}{:?><+;";
    }

    public static String getNumbersOwner(String count) {
        return faker.numerify(count);
    }

    public static String getCyrillicOwner() {
        return fakerRu.name().name().toUpperCase();
    }

    public static String getCVC() {
        return faker.numerify("###");
    }

    public static String getEmptyCVC() {
        return "";
    }

    public static String getSpacesCVC() {
        return "   ";
    }

    public static String getZeroCVC() {
        return "000";
    }

    public static String getOneSymbolCVC() {
        return faker.numerify("#");
    }

    public static String getTwoSymbolsCVC() {
        return faker.numerify("##");
    }

    public static String getFourSymbolsCVC() {
        return faker.numerify("##");
    }

    public static String getRandomLetterCVC() {
        return faker.bothify("?#?");
    }

    public static String getSymbolsCVC() {
        return ("*-+");
    }

    public static CardInfo getValidApprovedCard() {
        return new CardInfo(getApprovedNumber(), getMonth(1), getYear(1), getEnOwner(), getCVC());
    }

    public static CardInfo getValidDeclinedCard() {
        return new CardInfo(getDeclinedNumber(), getMonth(1), getYear(1), getEnOwner(), getCVC());
    }

    public static CardInfo getCardEmptyBody() {
        return new CardInfo(getEmptyNumber(), getEmptyMonth(), getEmptyYear(), getEmptyOwner(), getEmptyCVC());
    }

    public static CardInfo getCardEmptyNumber() {
        return new CardInfo(getEmptyNumber(), getMonth(1), getYear(1), getEnOwner(), getCVC());
    }

    public static CardInfo getCardEmptyMonth() {
        return new CardInfo(getNumberByStatus("APPROVED"), getEmptyMonth(), getYear(1), getEnOwner(), getCVC());
    }

    public static CardInfo getCardEmptyYear() {
        return new CardInfo(getNumberByStatus("APPROVED"), getMonth(1), getEmptyYear(), getEnOwner(), getCVC());
    }

    public static CardInfo getCardEmptyOwner() {
        return new CardInfo(getNumberByStatus("APPROVED"), getMonth(1), getYear(1), getEmptyOwner(), getCVC());
    }

    public static CardInfo getCardEmptyCVC() {
        return new CardInfo(getNumberByStatus("APPROVED"), getMonth(1), getYear(1), getEnOwner(), getEmptyCVC());
    }

    public static String getNumberByStatus(String status) {
        if (status.equalsIgnoreCase("APPROVED")) {
            return "1111 2222 3333 4444";
        } else if (status.equalsIgnoreCase("DECLINED")) {
            return "5555 6666 7777 8888";
        }
        return null;
    }

}
