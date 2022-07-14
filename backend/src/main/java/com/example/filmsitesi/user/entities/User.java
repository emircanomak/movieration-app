package com.example.filmsitesi.user.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userr")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "username", length = 255)
    String userName;
    @Column(name = "fullname", length = 255)
    String fullname;
    @Column(name = "password", length = 255)
    String password;

}


