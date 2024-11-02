package com.billycychan.order_service.web.controllers;

import com.billycychan.order_service.AbstractIT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.billycychan.order_service.testdata.TestDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;


@Sql("/test-orders.sql")
class OrderControllerTests extends AbstractIT {

    @Nested
    class CreateOrderTests {
        @Test
        void shouldCreateOrderSuccessfully() {
            mockGetProductByCode("P100", "Product 1", new BigDecimal("25.50"));
            var payload =
                    """
                        {
                            "customer" : {
                                "name": "Siva",
                                "email": "siva@gmail.com",
                                "phone": "999999999"
                            },
                            "deliveryAddress" : {
                                "addressLine1": "HNO 123",
                                "addressLine2": "Kukatpally",
                                "city": "Hyderabad",
                                "state": "Telangana",
                                "zipCode": "500072",
                                "country": "India"
                            },
                            "items": [
                                {
                                    "code": "P100",
                                    "name": "Product 1",
                                    "price": 25.50,
                                    "quantity": 1
                                }
                            ]
                        }
                    """;
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}