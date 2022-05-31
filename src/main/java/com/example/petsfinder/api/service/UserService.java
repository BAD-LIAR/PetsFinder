package com.example.petsfinder.api.service;

import com.example.petsfinder.api.dto.UserDto;
import com.example.petsfinder.api.entity.ChatEntity;
import com.example.petsfinder.api.entity.UserEntity;
import com.example.petsfinder.api.mapper.DefaultMapper;
import com.example.petsfinder.api.repository.ChatRepository;
import com.example.petsfinder.api.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public UserEntity saveUser(UserDto userDto) {
        return userRepository.save(DefaultMapper.userDtoToUserEntity(userDto));
    }

    public UserEntity getUserByChatId(@NonNull String chatId) {
        return chatRepository.getChatEntityByChatId(chatId).getUser();
    }

    public boolean saveUserWithChatIdIfNotExist(UserEntity userEntity, String chatId) {
        if (chatRepository.getChatEntityByChatId(chatId) == null) {
            userEntity = userRepository.save(userEntity);
            chatRepository.save(ChatEntity.builder()
                    .chatId(chatId)
                    .user(userEntity)
                    .build());
            return true;
        } else {
            return false;
        }
    }
}
