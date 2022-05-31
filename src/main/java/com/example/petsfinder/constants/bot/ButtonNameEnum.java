package com.example.petsfinder.constants.bot;

import java.util.Arrays;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonNameEnum {
    CREATE_ANN("Создать объявление"),
    CLOSE_ANN("Закрыть объявление"),
    MY_ANN("Мои объявления"),
    SUB_ANN("Подписаться"),
    UNSUB_ANN("Отписаться"),
    ANSWER("");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public static ButtonNameEnum valueWithButtonName(String buttonName){
        return Arrays.stream(ButtonNameEnum.values()).filter(btn -> btn.getButtonName().equals(buttonName)).findFirst().orElse(ANSWER);
    }

}