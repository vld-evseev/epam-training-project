package com.epam.training.lawAndSocial.model.education;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class University {

    long id;
    long userId;
    String name;
    String country;
    String city;
    int yearsFrom;
    int yearsTo;

}
