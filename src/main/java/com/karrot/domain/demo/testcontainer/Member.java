package com.karrot.domain.demo.testcontainer;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    String email;

    String password;

    public Member() {
    }

    public Member(String name) {
        this.name = name;
    }
}
