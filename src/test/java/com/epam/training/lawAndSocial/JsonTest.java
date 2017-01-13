package com.epam.training.lawAndSocial;

import com.epam.training.lawAndSocial.model.Message;
import com.google.gson.Gson;
import org.junit.Test;

public class JsonTest {

    @Test
    public void jsonTest() {
        String sampleMessage = "{\"fromUserId\":2,\"toUserId\":1,\"text\":\"33434\",\"date\":1484168731528}";
        Gson g = new Gson();

        Message msg = g.fromJson(sampleMessage, Message.class);

        System.out.println(msg.toString()); // {"name":"John"}
    }
}
