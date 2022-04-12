package com.baeldung.crud.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "users_id_seq", allocationSize = 1)
    private int id;

    @NotBlank(message = "Login is mandatory")
    private String login;
    
    @NotBlank(message = "Password is mandatory")
    private String password;

    private String god;

    private int exp;

    private String clan;

    private String email;

}
