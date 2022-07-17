package com.example.librarycrud.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="member")

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String mail;

    private String major;

    private LocalDate expired;

    private LocalDate birthday;

    @OneToMany(mappedBy = "member")
    private List<BorrowBook> memberBookList;

    private String avatar;

}
