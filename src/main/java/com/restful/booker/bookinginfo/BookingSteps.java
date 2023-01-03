package com.restful.booker.bookinginfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;


public class BookingSteps {
    @Step("Creating booking with firstName : {0}, lastName: {1}, email: {2}, totalPrice: {3}, depositPaid: {4}, bookingDates: {5} and additionalNeeds: {6}")
    public ValidatableResponse createBooking(String firstName, String lastName, int totalPrice,
                                             String depositPaid, List<String> bookingDates,
                                            List<String> additionalNeeds ) {
        BookingPojo bookingPojo=BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid,
                bookingDates, additionalNeeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post(EndPoints.CREATE_BOOKING)
                .then();
    }

    @Step("Getting the booking information with bookingId: {0}")
    public ValidatableResponse getBookingInfoByBookingId(int bookingId) {

        return SerenityRest.given().log().ifValidationFails()
                .pathParam("bookingId", bookingId)
                .when()
                .get(EndPoints.GET_ALL_BOOKING)
                .then();

    }

    @Step("Updating booking information with bookingId: {0}, firstName : {1}, lastName: {2}, email: {3}, totalPrice: {4}, depositPaid: {5}, bookingDates: {6} and additionalNeeds: {7}")
    public ValidatableResponse updateBooking(int bookingId, String firstName, String lastName, int totalPrice,
                                             String depositPaid, List<String> bookingDates,
                                             List<String> additionalNeeds ) {
        BookingPojo bookingPojo=BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid,
                bookingDates, additionalNeeds);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("bookingid", bookingId)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Deleting booking information with bookingId: {0}")
    public ValidatableResponse deleteBooking(int bookingId) {
        return SerenityRest.given().log().all()
                .pathParam("bookingid", bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }

    @Step("Getting booking information with bookingId: {0}")
    public ValidatableResponse getBookingById(int bookingId){
        return SerenityRest.given().log().all()
                .pathParam("bookingid", bookingId)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }

}
