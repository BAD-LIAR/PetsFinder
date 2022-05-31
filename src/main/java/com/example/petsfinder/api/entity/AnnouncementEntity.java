package com.example.petsfinder.api.entity;


import com.example.petsfinder.api.enums.AnnStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private UserEntity owner;


    private String years;
    private String color;

    private String phone;

    @Enumerated(EnumType.STRING)
    private AnnStatus status;

}
