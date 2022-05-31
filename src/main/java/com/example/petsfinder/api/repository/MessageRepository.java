package com.example.petsfinder.api.repository;

import com.example.petsfinder.api.entity.ChatEntity;
import com.example.petsfinder.api.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> getMessageEntityByChatEntity(ChatEntity chatEntity);
}
