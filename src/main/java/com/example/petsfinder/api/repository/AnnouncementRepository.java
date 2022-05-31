package com.example.petsfinder.api.repository;

import com.example.petsfinder.api.entity.AnnouncementEntity;
import com.example.petsfinder.api.entity.UserEntity;
import com.example.petsfinder.api.enums.AnnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {

    List<AnnouncementEntity> getAnnouncementEntitiesByOwner(UserEntity owner);

    AnnouncementEntity getAnnouncementEntityByStatusAndOwner(AnnStatus status, UserEntity owner);
}
