package com.baeldung.crud.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private String od;
    private String priceType;
    private String X;
    private String Y;
    private String buildIn;
    private String c;
    private String calibre;
    private String cost;
    private String count;
    private String damage;
    private String dt;
    private String gc;
    private String grouping;
    private String hint;
    private String hz;
    private String lb;
    private String ln;
    private String lvl;
    private String made;
    private String massa;
    private String maxCount;
    private String maxquality;
    private String min;
    private String name;
    private String nskill;
    private String nt;
    private String owner;
    private String piercing;
    private String price;
    private String protect;
    private String quality;
    private String rOD;
    private String radius;
    private String range;
    private String res;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String section;
    private String shot;
    private String st;
    private String tm;
    private String txt;
    private String txt2;
    private double type;
    private String up;
    private long pid;
    private String cost2;

    @Override
    public String toString() {
        return String.format("#%d | %s (sprite: %s)", id, txt, name);
    }
}
