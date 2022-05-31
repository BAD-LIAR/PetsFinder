package com.example.petsfinder.api.mapper;

import com.example.petsfinder.api.dto.AnnouncementDto;
import com.example.petsfinder.api.dto.UserDto;
import com.example.petsfinder.api.entity.AnnouncementEntity;
import com.example.petsfinder.api.entity.UserEntity;

public class DefaultMapper {

    public static UserEntity userDtoToUserEntity(UserDto userDto){
        return UserEntity.builder()
                .phone(userDto.getPhone())
                .build();
    }

    public static AnnouncementEntity announcementDtoToAnnouncementEntity(AnnouncementDto announcementDto){
        return AnnouncementEntity.builder()
                .build();
    }
}
