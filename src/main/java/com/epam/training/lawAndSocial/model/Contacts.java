package com.epam.training.lawAndSocial.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Contacts {

    long id;
    long userId;
    String email;
    String phone;
    String website;

}
