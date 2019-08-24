package org.telegram.commands;

import java.util.HashMap;
import java.util.Map;

public class AddToMessage {


    private Map<String , String> replyMessages = new HashMap<>();

    {
        replyMessages.put("рот твой ебал","И я ебала");
    }

    public String addToMessage(String message) {
        if (message.toLowerCase().contains("рот твой ебал")) {
       return  "И я ебала";
        }
        return null;
    }
}
