package com.example.petsfinder.api.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String chatId;
    @ManyToOne
    private UserEntity user;

}
