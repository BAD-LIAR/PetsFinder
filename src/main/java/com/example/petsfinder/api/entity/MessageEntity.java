package com.example.petsfinder.api.entity;

import com.example.petsfinder.api.enums.MessageTypeEnum;
import com.example.petsfinder.api.enums.PetAttributeEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private ChatEntity chatEntity;

    private LocalDateTime dateTime;

    private String message;

    @Enumerated(EnumType.STRING)
    private MessageTypeEnum type;

    @Enumerated(EnumType.STRING)
    private PetAttributeEnum subType;

}
