package com.koblev.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
