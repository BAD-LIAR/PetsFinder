package com.example.petsfinder.telegram.handlers;

import com.example.petsfinder.api.dto.AnnouncementDto;
import com.example.petsfinder.api.entity.AnnouncementEntity;
import com.example.petsfinder.api.entity.UserEntity;
import com.example.petsfinder.api.enums.MessageTypeEnum;
import com.example.petsfinder.api.enums.PetAttributeEnum;
import com.example.petsfinder.api.service.AnnouncementService;
import com.example.petsfinder.api.service.MessageService;
import com.example.petsfinder.api.service.UserService;
import com.example.petsfinder.constants.bot.BotMessageEnum;
import com.example.petsfinder.constants.bot.ButtonNameEnum;
import com.example.petsfinder.telegram.keyboards.InlineKeyboardMaker;
import com.example.petsfinder.telegram.keyboards.ReplyKeyboardMaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageHandler {

    private ReplyKeyboardMaker replyKeyboardMaker;
    private InlineKeyboardMaker inlineKeyboardMaker;
    private MessageService messageService;
    private UserService userService;
    private AnnouncementService announcementService;

    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();

        SendMessage sendMessage;
        String inputText = message.getText();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return getStartMessage(message, chatId);
        } else {
            switch (ButtonNameEnum.valueWithButtonName(inputText)) {
                case CREATE_ANN:
                    sendMessage = getCreateAnn(chatId);
                    break;
                case ANSWER:
                    sendMessage = saveAnswerAndGenerageMessage(inputText, chatId);
                    break;
                case MY_ANN:
                    sendMessage = getAnnForUser(chatId);
                    break;
                default:
                    sendMessage = getEmptyMessage(message, chatId);
            }
        }

        return sendMessage;
    }

    private SendMessage getAnnForUser(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, announcementService.getAllAnn(chatId));
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    private SendMessage getEmptyMessage(Message message, String chatId) {
        SendMessage sendMessage = new SendMessage(chatId,
                "");
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getCreateAnnBts());
        return sendMessage;
    }

    private SendMessage saveAnswerAndGenerageMessage(String inputText, String chatId) {
        AnnouncementEntity announcementEntity = announcementService.saveAnswer(inputText, chatId);
        SendMessage sendMessage = new SendMessage(chatId,
                String.format(BotMessageEnum.NEW_ANN_MESSAGE.getMessage(), announcementService.generateAnnMes(announcementEntity)));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getCreateAnnBts());
        messageService.saveMessage(sendMessage.getText(), userService.getUserByChatId(sendMessage.getChatId()), MessageTypeEnum.CREATE_ANN, String.valueOf(PetAttributeEnum.valueOfString(inputText)));
        return sendMessage;
    }

    private SendMessage getCreateAnn(String chatId) {
        AnnouncementEntity announcementEntity = announcementService.createAnnouncement(chatId);
        SendMessage sendMessage = new SendMessage(chatId,
                String.format(BotMessageEnum.NEW_ANN_MESSAGE.getMessage(), announcementService.generateAnnMes(announcementEntity)));
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getCreateAnnBts());
        messageService.saveMessage(sendMessage.getText(), userService.getUserByChatId(sendMessage.getChatId()), MessageTypeEnum.CREATE_ANN, "");
        return sendMessage;
    }

    private SendMessage getStartMessage(Message message, String chatId) {
        UserEntity userEntity = UserEntity.builder()
                .firstName(message.getFrom().getFirstName())
                .lastName(message.getFrom().getLastName())
                .build();
        if (userService.saveUserWithChatIdIfNotExist(userEntity, chatId)) {
            SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELLO_MESSAGE.getMessage());
            sendMessage.enableMarkdown(true);
            sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
            return sendMessage;
        } else {
            return new SendMessage(chatId, "Команда не может быть применена");
        }
    }


}