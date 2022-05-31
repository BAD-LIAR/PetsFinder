package com.example.petsfinder.constants.bot;

import java.util.Arrays;

/**
 * Элементы ответов кнопок инлайн-клавиатур
 */
public enum CreateAnnBts {
    YEARS("Возраст"),
    COLOR("Цвет"),
    SAVE("Сохранить");

    private final String text;

    CreateAnnBts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static CreateAnnBts valueWithText(String buttonName){
        return Arrays.stream(CreateAnnBts.values()).filter(btn -> btn.getText().equals(buttonName)).findFirst().get();
    }
}