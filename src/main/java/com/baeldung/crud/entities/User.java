package com.baeldung.crud.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Name is mandatory")
    private String login;
    
    @NotBlank(message = "Email is mandatory")
    private String email;

    public void setId(int id) {
        this.id = id;
    }
}
