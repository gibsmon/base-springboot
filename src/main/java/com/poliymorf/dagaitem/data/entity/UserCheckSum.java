package com.poliymorf.dagaitem.data.entity;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "m_user_check_sum")
public class UserCheckSum {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "account")
    private String acc;

    @Column(name = "biodata")
    private String bio;

    @Column(name = "address")
    private String add;

    @Column(name = "profile")
    private String profile;
}
