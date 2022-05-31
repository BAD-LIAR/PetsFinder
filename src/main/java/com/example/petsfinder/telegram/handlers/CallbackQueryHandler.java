package com.example.petsfinder.telegram.handlers;

import com.example.petsfinder.api.entity.MessageEntity;
import com.example.petsfinder.api.enums.MessageTypeEnum;
import com.example.petsfinder.api.enums.PetAttributeEnum;
import com.example.petsfinder.api.service.AnnouncementService;
import com.example.petsfinder.api.service.MessageService;
import com.example.petsfinder.api.service.UserService;
import com.example.petsfinder.constants.bot.BotMessageEnum;
import com.example.petsfinder.constants.bot.CreateAnnBts;
import com.example.petsfinder.telegram.keyboards.InlineKeyboardMaker;
import com.example.petsfinder.telegram.keyboards.ReplyKeyboardMaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.IOException;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CallbackQueryHandler {


    private final MessageService messageService;
    private final UserService userService;
    private final AnnouncementService announcementService;
    private final InlineKeyboardMaker inlineKeyboardMaker;
    private final ReplyKeyboardMaker replyKeyboardMaker;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        MessageEntity messageEntity = messageService.getLastMessage(chatId);
        SendMessage sendMessage = null;
        switch (messageEntity.getType()) {
            case CREATE_ANN:
                switch (CreateAnnBts.valueOf(data)) {
                    case YEARS:
                        sendMessage = getWriteAttrMessage(CreateAnnBts.YEARS, chatId);
                        break;
                    case COLOR:
                        sendMessage = getWriteAttrMessage(CreateAnnBts.COLOR, chatId);
                        break;
                    case SAVE:
                        sendMessage = getSaveMessage(chatId);
                }
                break;
        }


        return sendMessage;
    }

    private SendMessage getSaveMessage(String chatId) {
        announcementService.saveLastAnn(chatId);
        SendMessage sendMessage = new SendMessage(chatId, "Вы успешно сохранили объявление");
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getWriteAttrMessage(CreateAnnBts petAttribute, String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.WRITE_NEW_VALUE_MESSAGE.getMessage(), petAttribute.getText()));
        messageService.saveMessage(sendMessage.getText(), userService.getUserByChatId(sendMessage.getChatId()), MessageTypeEnum.PET_ATTRIBUTE, petAttribute.name());
        return sendMessage;
    }

}