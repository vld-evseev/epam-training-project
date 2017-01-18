package com.epam.training.lawAndSocial.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Credentials {

    String username;
    String password;

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                '}';
    }
}
