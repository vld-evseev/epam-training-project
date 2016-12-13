package com.epam.training.lawAndSocial.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class User {

    String userName;
    String firstName;
    String lastName;
    String email;
    LocalDate date;
    String passwordHash;

}
