package com.example.petsfinder.api.service;

import com.example.petsfinder.api.entity.MessageEntity;
import com.example.petsfinder.api.entity.UserEntity;
import com.example.petsfinder.api.enums.MessageTypeEnum;
import com.example.petsfinder.api.enums.PetAttributeEnum;
import com.example.petsfinder.api.repository.ChatRepository;
import com.example.petsfinder.api.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public MessageEntity saveMessage(String message, UserEntity userEntity, MessageTypeEnum messageTypeEnum, String subType){
        MessageEntity messageEntity = MessageEntity.builder()
                .message(message)
                .userEntity(userEntity)
                .dateTime(LocalDateTime.now())
                .type(messageTypeEnum)
                .subType(PetAttributeEnum.valueOfString(subType))
                .chatEntity(chatRepository.getChatEntityByUser(userEntity))
                .build();
        return messageRepository.save(messageEntity);
    }

    public MessageEntity getLastMessage(String chatId){
        List<MessageEntity> messageEntityList = messageRepository.getMessageEntityByChatEntity(chatRepository.getChatEntityByChatId(chatId));
        return messageEntityList.get(messageEntityList.size() - 1);
    }
}
