package com.epam.training.lawAndSocial.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Job {

    long id;
    long userId;
    String organization;
    String position;
    String industry;
    String website;
    int yearFrom;
    int yearTo;

}
