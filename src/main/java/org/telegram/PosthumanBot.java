package org.telegram;

import org.telegram.commands.AddToMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import java.util.ResourceBundle;

public class PosthumanBot extends TelegramLongPollingBot {


    private static final String LOGTAG = "PosthumanBot";
    private AddToMessage addToMessage = new AddToMessage();

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage());
        String agree = null;
        long chat_id = update.getMessage().getChatId();
        if (isForwarded(update)) {
            BotLogger.info(LOGTAG, "Ive got forward" + update.getMessage().getForwardFromChat());
            agree = "Забавный мем конечно , но иди нахуй";
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            BotLogger.info(LOGTAG, "Ive got massage : " + message_text);

            agree = addToMessage.addToMessage(message_text);

        }
        BotLogger.info(LOGTAG, agree);
        if (agree != null) {
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

    private boolean isForwarded(Update update) {
        System.out.println(update.getMessage().getPhoto());
        return update.getMessage().getForwardFromChat() != null || update.getMessage().getPhoto() != null;
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
