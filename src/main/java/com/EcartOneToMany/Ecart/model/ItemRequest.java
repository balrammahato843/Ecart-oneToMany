package com.EcartOneToMany.Ecart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest
{

    private Integer quantity;
    private Double price;
    private  String itemName;


}
