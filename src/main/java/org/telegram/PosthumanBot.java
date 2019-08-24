package org.telegram;

import org.telegram.commands.AddToMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import java.util.ResourceBundle;

public class PosthumanBot extends TelegramLongPollingBot{


    private static final String LOGTAG = "PosthumanBot";
    private AddToMessage addToMessage = new AddToMessage();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasSticker()){
            BotLogger.info(LOGTAG, "Ive got massage : " + update.getMessage().getSticker());
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            BotLogger.info(LOGTAG, "Ive got massage : " + message_text);
            long chat_id = update.getMessage().getChatId();
            String agree = addToMessage.addToMessage(message_text);

            if (agree!=null) {
                SendMessage message = new SendMessage() // Create a message object
                        .setChatId(chat_id)
                        .setText(agree);
                try {

                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return ResourceBundle.getBundle("configs").getString("bot.username");
    }

    @Override
    public String getBotToken() {

        return ResourceBundle.getBundle("configs").getString("bot.token");
    }
}
