package com.EcartOneToMany.Ecart.mapper;

import com.EcartOneToMany.Ecart.model.CartRequest;
import com.EcartOneToMany.Ecart.entities.CartEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CartMapper
{
    CartRequest entityToModel (CartEntity cartEntity);

    CartEntity ModelToEntity (CartRequest cartRequest);

    List<CartRequest> EntitiesToModel (List<CartEntity> cartEntity);
}
