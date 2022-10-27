package ru.netology.test.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.Helper;
import ru.netology.data.DBHelper;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class TestApiPayment {
    private static final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://185.119.57.197")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static final String url = "/payment";

    @BeforeAll
    public static void setup() {
        DBHelper.clear();
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
    }

    @AfterAll
    public static void setDownMethod() {
        DBHelper.clear();
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldApprovedCardStatus200() {
        String status = given()
                .spec(spec)
                .body(Helper.getValidApprovedCard())
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .path("status");

        assertEquals("APPROVED", status);
        assertEquals(1, DBHelper.getPayments().size());
        assertEquals(0, DBHelper.getCreditsRequest().size());
        assertEquals(1, DBHelper.getOrders().size());

        assertTrue(DBHelper.getPayments().get(0).getStatus().equalsIgnoreCase("approved"));
        assertEquals(DBHelper.getPayments().get(0).getTransaction_id(), DBHelper.getOrders().get(0).getPayment_id());
        assertNull(DBHelper.getOrders().get(0).getCredit_id());
    }

    @Test
    public void shouldDeclinedCardStatus200() {
        String status = given()
                .spec(spec)
                .body(Helper.getValidDeclinedCard())
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .path("status");

        assertEquals("DECLINED", status);
        assertEquals(1, DBHelper.getPayments().size());
        assertEquals(0, DBHelper.getCreditsRequest().size());
        assertEquals(1, DBHelper.getOrders().size());

        assertTrue(DBHelper.getPayments().get(0).getStatus().equalsIgnoreCase("declined"));
        assertEquals(DBHelper.getPayments().get(0).getTransaction_id(), DBHelper.getOrders().get(0).getPayment_id());
        assertNull(DBHelper.getOrders().get(0).getCredit_id());
    }

    @Test
    public void shouldEmptyBodyStatus400() {
        given()
                .spec(spec)
                .body(Helper.getCardEmptyBody())
                .when()
                .post(url)
                .then()
                .statusCode(400);

        assertEquals(0, DBHelper.getPayments().size());
        assertEquals(0, DBHelper.getCreditsRequest().size());
        assertEquals(0, DBHelper.getOrders().size());
    }

    @Test
    public void shouldEmptyNumberStatus400() {
        given()
                .spec(spec)
                .body(Helper.getCardEmptyNumber())
                .when()
                .post(url)
                .then()
                .statusCode(400);

        assertEquals(0, DBHelper.getPayments().size());
        assertEquals(0, DBHelper.getCreditsRequest().size());
        assertEquals(0, DBHelper.getOrders().size());
    }

    @Test
    public void shouldEmptyMonthStatus400() {
        given()
                .spec(spec)
                .body(Helper.getCardEmptyMonth())
                .when()
                .post(url)
                .then()
                .statusCode(400);

        assertEquals(0, DBHelper.getPayments().size());
        assertEquals(0, DBHelper.getCreditsRequest().size());
        assertEquals(0, DBHelper.getOrders().size());
    }

    @Test
    public void shouldEmptyYearStatus400() {
        given()
                .spec(spec)
                .body(Helper.getCardEmptyYear())
                .when()
                .post(url)
                .then()
                .statusCode(400);

        assertEquals(0, DBHelper.getPayments().size());
        assertEquals(0, DBHelper.getCreditsRequest().size());
        assertEquals(0, DBHelper.getOrders().size());
    }

    @Test
    public void shouldEmptyHolderStatus400() {
        given()
                .spec(spec)
                .body(Helper.getCardEmptyOwner())
                .when()
                .post(url)
                .then()
                .statusCode(400);

        assertEquals(0, DBHelper.getPayments().size());
        assertEquals(0, DBHelper.getCreditsRequest().size());
        assertEquals(0, DBHelper.getOrders().size());
    }

    @Test
    public void shouldEmptyCvcStatus400() {
        given()
                .spec(spec)
                .body(Helper.getCardEmptyCVC())
                .when()
                .post(url)
                .then()
                .statusCode(400);

        assertEquals(0, DBHelper.getPayments().size());
        assertEquals(0, DBHelper.getCreditsRequest().size());
        assertEquals(0, DBHelper.getOrders().size());
    }
}
