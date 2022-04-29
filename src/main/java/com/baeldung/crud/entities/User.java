package com.baeldung.crud.entities;

import com.baeldung.crud.entities.converters.DateToLongConverter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    private String clan;

    private int man;

    private String email;

    private int exp;

    private int hp;

    private int pro;

    private double propwr;

    private int rankPoints;

    private int psy;

    private int str;

    private int dex;

    private int pow;

    private int acc;

    private int intel;

    private int cup_0;

    private int silv;

    private int gold;

    private int p78money;

    private int bot;

    private String dismiss;

    @DateTimeFormat (pattern="yyyy-MM-dd")
    @Convert(converter = DateToLongConverter.class)
    private LocalDate chatblock;

    @DateTimeFormat (pattern="yyyy-MM-dd")
    @Convert(converter = DateToLongConverter.class)
    private LocalDate forumblock;
}

