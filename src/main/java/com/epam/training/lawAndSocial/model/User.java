package com.epam.training.lawAndSocial.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    long id;
    String userName;
    String firstName;
    String lastName;
    String patronymic;
    Gender gender;
    String date;
    String avatar;
    String passwordHash;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", gender=" + gender +
                ", date='" + date + '\'' +
                '}';
    }
}
