package com.baeldung.crud.entities.wrapper;

import com.baeldung.crud.entities.ItemTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ItemTemplateWrapper {

    ItemTemplate item;
}
