package com.example.petsfinder.api.repository;

import com.example.petsfinder.api.entity.ChatEntity;
import com.example.petsfinder.api.entity.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    ChatEntity getChatEntityByChatId(@NonNull String chatId);
    ChatEntity getChatEntityByUser(UserEntity user);

}
