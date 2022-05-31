package com.example.petsfinder.api.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AnnouncementEntity> announcementEntities;

    @OneToMany
    private List<ChatEntity> chatEntities;

}
