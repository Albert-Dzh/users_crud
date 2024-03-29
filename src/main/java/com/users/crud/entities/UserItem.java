package com.users.crud.entities;


import com.users.crud.entities.converters.DateToStringConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_item")
@DynamicInsert
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_item_sequence")
    @SequenceGenerator(name = "user_item_sequence", sequenceName = "user_item_id_seq", allocationSize = 1)
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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Convert(converter = DateToStringConverter.class)
    private LocalDate dt;
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
    private int userId;

    public UserItem(ItemTemplate it, int userId) {
        try {
            for (Field templateField : it.getClass().getDeclaredFields())
                if (!templateField.getName().equals("id"))
                    this.getClass().getDeclaredField(templateField.getName()).set(this, templateField.get(it));
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        this.userId = userId;
    }

    public UserItem mergedWith(UserItem dummy) {
        try {
            for (Field dummyField : dummy.getClass().getDeclaredFields()) {
                Object var = dummyField.get(dummy);
                Type t = dummyField.getType();
                if (t.equals(LocalDate.class) || t.equals(String.class) && var != null)
                    dummyField.set(this, var);
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s (sprite: %s) (type: %s) (owner: %d)", txt, name, type, userId);
    }
}
