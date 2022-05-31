package com.example.petsfinder.api.service;

import com.example.petsfinder.api.entity.AnnouncementEntity;
import com.example.petsfinder.api.entity.MessageEntity;
import com.example.petsfinder.api.entity.UserEntity;
import com.example.petsfinder.api.enums.AnnStatus;
import com.example.petsfinder.api.repository.AnnouncementRepository;
import com.example.petsfinder.constants.bot.CreateAnnBts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.petsfinder.api.enums.PetAttributeEnum.COLOR;
import static com.example.petsfinder.api.enums.PetAttributeEnum.YEARS;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final MessageService messageService;
    private final UserService userService;

    private final String ANN_TEMPLATE = "Объявление\n" +
            "Цвет: %s,\n" +
            "Возраст: %s";

    public AnnouncementEntity createAnnouncement(String chatId) {
        UserEntity userEntity = userService.getUserByChatId(chatId);
        return announcementRepository.save(AnnouncementEntity.builder()
                .owner(userEntity)
                .status(AnnStatus.CREATING_STARTED)
                .build());
    }

    public AnnouncementEntity saveAnswer(String answer, String chatId){
        MessageEntity messageEntity = messageService.getLastMessage(chatId);
        UserEntity userEntity = userService.getUserByChatId(chatId);
        AnnouncementEntity announcementEntity = announcementRepository.getAnnouncementEntityByStatusAndOwner(AnnStatus.CREATING_STARTED, userEntity);
        switch (messageEntity.getSubType()){
            case YEARS:
                announcementEntity.setYears(answer);
                break;
            case COLOR:
                announcementEntity.setColor(answer);
                break;
        }
        return announcementRepository.save(announcementEntity);
    }

    public String generateAnnMes(AnnouncementEntity announcementEntity) {
        return "Возраст: " + Optional.ofNullable(announcementEntity.getYears()).orElse("отсутсвует") + ", \n " +
                "Цвет:" + Optional.ofNullable(announcementEntity.getColor()).orElse("отсутсвует");
    }

    public AnnouncementEntity getLastAnn(String chatId){
        UserEntity userEntity = userService.getUserByChatId(chatId);
        AnnouncementEntity announcementEntity = announcementRepository.getAnnouncementEntityByStatusAndOwner(AnnStatus.CREATING_STARTED, userEntity);
        return announcementEntity;
    }

    public AnnouncementEntity saveLastAnn(String chatId){
        AnnouncementEntity announcementEntity = getLastAnn(chatId);
        announcementEntity.setStatus(AnnStatus.CREATING_FINISHED);
        return announcementRepository.save(announcementEntity);
    }

    public String getAllAnn(String chatId) {
        List<AnnouncementEntity> announcementEntityList = announcementRepository.getAnnouncementEntitiesByOwner(userService.getUserByChatId(chatId));
        StringBuilder s = new StringBuilder();
        announcementEntityList.forEach(ann -> s.append(String.format(ANN_TEMPLATE, ann.getColor(), ann.getYears())).append("\n--------------\n"));
        return s.toString().equals("") ? "Пусто": s.toString();
    }
}
