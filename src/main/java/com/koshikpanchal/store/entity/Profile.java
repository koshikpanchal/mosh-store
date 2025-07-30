package com.koshikpanchal.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "bio")
    private String bio;

    @Column(nullable = false, name = "phone_number")
    private String phone_number;

    @Column(nullable = false, name = "date_of_birth")
    private String date_of_birth;

    @Column(nullable = false, name = "loyalty_points")
    private String loyalty_points;

}
