package com.example.petsfinder.telegram.keyboards;

import com.example.petsfinder.constants.bot.ButtonNameEnum;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Основная клавиатура, расположенная под строкой ввода текста в Telegram
 */
@Component
public class ReplyKeyboardMaker {

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnum.CREATE_ANN.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnum.CLOSE_ANN.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnum.MY_ANN.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonNameEnum.SUB_ANN.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameEnum.UNSUB_ANN.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getCreate() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(ButtonNameEnum.CREATE_ANN.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnum.CLOSE_ANN.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(ButtonNameEnum.SUB_ANN.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameEnum.UNSUB_ANN.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}