package com.epam.training.lawAndSocial.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class News {

    long id;
    User user;
    long date;
    String heading;
    String content;

}
