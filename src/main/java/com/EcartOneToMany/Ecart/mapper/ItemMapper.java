package com.EcartOneToMany.Ecart.mapper;


import com.EcartOneToMany.Ecart.entities.ItemEntity;
import com.EcartOneToMany.Ecart.model.ItemRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
    public interface ItemMapper {

        ItemRequest entityToModel (ItemEntity itemEntity);

        ItemEntity modelToEntity (ItemRequest itemRequest);

        List<ItemRequest> entitiesToModel (List<ItemEntity> itemEntities);
    }


