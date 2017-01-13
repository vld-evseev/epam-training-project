package com.epam.training.lawAndSocial.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Message {

    String sessionId;
    long fromUserId;
    long toUserId;
    String text;
    String type;
    long date;

}
