package com.example.petsfinder.telegram.keyboards;

import com.example.petsfinder.constants.bot.CreateAnnBts;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Клавиатуры, формируемые в ленте Telegram для получения файлов
 */
@Component
public class InlineKeyboardMaker {



    public InlineKeyboardMarkup getCreateAnnBts() {
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();

        Arrays.stream(CreateAnnBts.values()).forEach(btn -> inlineKeyboardButtons.add(List.of(InlineKeyboardButton.builder()
                .text(btn.getText())
                .callbackData(btn.name())
                .build())));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);
        return inlineKeyboardMarkup;    }
}