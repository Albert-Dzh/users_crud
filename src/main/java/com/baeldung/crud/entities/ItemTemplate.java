package com.baeldung.crud.entities;

import com.baeldung.crud.entities.converters.DateToStringConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "items_template")
public class ItemTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_template_sequence")
    @SequenceGenerator(name = "items_template_sequence", sequenceName = "items_template_id_seq", allocationSize = 1)
    private int id;

    String od;
    String priceType;
    String X;
    String Y;
    String buildIn;
    String c;
    String calibre;
    String cost;
    String count;
    String damage;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Convert(converter = DateToStringConverter.class)
    LocalDate dt;
    String gc;
    String grouping;
    String hint;
    String hz;
    String lb;
    String ln;
    String lvl;
    String made;
    String massa;
    String maxCount;
    String maxquality;
    String min;
    String name;
    String nskill;
    String nt;
    String owner;
    String piercing;
    String price;
    String protect;
    String quality;
    String rOD;
    String radius;
    String range;
    String res;
    String s1;
    String s2;
    String s3;
    String s4;
    String section;
    String shot;
    String st;
    String tm;
    String txt;
    String txt2;
    double type;
    String up;
    long pid;
    String cost2;

    @Override
    public String toString() {
        return String.format("#%d | %s | sprite: %s | type: %s", id, txt, name, type);
    }
}
