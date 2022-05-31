package com.example.petsfinder.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramConfig {
    String webhookPath = "https://4612-85-249-27-77.eu.ngrok.io";
    String botName = "PlsFindMyPetBot";
    String botToken = "5339125247:AAEF-sCgUFwCZiiIsZY17UchbW9Vciv_YiQ";
}