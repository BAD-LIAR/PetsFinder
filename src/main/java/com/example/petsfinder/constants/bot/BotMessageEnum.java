package com.example.petsfinder.constants.bot;

/**
 * Текстовые сообщения, посылаемые ботом
 */
public enum BotMessageEnum {
    //ответы на команды с клавиатуры
    HELLO_MESSAGE("Добро пожаловать"),
    NEW_ANN_MESSAGE("Вы создаете объявление, заполните поля. \n" +
            "Текущее состояние объявления: %s"),
    WRITE_NEW_VALUE_MESSAGE("Введите атрибут %s. \n" +
            "Если вы не хотите этого делать, введите '-'");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}