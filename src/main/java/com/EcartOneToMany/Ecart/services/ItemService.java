package com.EcartOneToMany.Ecart.services;

import com.EcartOneToMany.Ecart.entities.ItemEntity;
import com.EcartOneToMany.Ecart.mapper.CartMapper;
import com.EcartOneToMany.Ecart.mapper.ItemMapper;
import com.EcartOneToMany.Ecart.model.ItemRequest;
import com.EcartOneToMany.Ecart.model.ItemResponse;
import com.EcartOneToMany.Ecart.repositories.CartRepository;
import com.EcartOneToMany.Ecart.repositories.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ItemService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private  final CartMapper cartMapper;
    private final ItemMapper itemMapper;


    @Autowired
    public ItemService(CartRepository cartRepository, ItemRepository itemRepository, CartMapper cartMapper, ItemMapper itemMapper) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.cartMapper = cartMapper;
        this.itemMapper = itemMapper;
    }


    public ItemResponse addItem(ItemRequest itemRequest)
    {
        ItemEntity itemEntity = itemMapper.modelToEntity(itemRequest);
        itemRepository.save(itemEntity);
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setItemId(itemEntity.getItemId());
        return itemResponse;
    }

    public List<ItemRequest> getAllItems() {
        List<ItemEntity> itemEntities=itemRepository.findAll();

        return itemMapper.entitiesToModel(itemEntities);


    }

    public ItemRequest getItem(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId).orElse(null);
        log.info("inside service:" + itemId);

        return itemMapper.entityToModel(itemEntity);

    }

    public String deleteItem(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId).orElse(null);

        itemRepository.delete(itemEntity);

        return  "item delete with id:"+itemId;

    }

    public ItemRequest updateItem(Long id,ItemRequest itemRequest)
    {
        //  ItemEntity itemEntity=itemRepository.findById(id).orElse(null);
        ItemEntity itemEntity= itemMapper.modelToEntity(itemRequest);
        itemEntity.setItemId(id);
        itemRepository.save(itemEntity);

        return itemMapper.entityToModel(itemEntity);

    }
/*
    public List<ItemRequest> getItemsByCartId(Long cartId) {
        CartEntity cartEntity = cartRepository.findById(cartId).orElse(null);
        List<ItemEntity> itemEntities = cartEntity.getItem();
        List<ItemRequest> itemRequests = itemMapper.entitiesToModel(itemEntities);
       return itemRequests;
    }
*/



}