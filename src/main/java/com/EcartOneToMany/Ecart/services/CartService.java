package com.EcartOneToMany.Ecart.services;

import com.EcartOneToMany.Ecart.entities.ItemEntity;
import com.EcartOneToMany.Ecart.mapper.CartMapper;
import com.EcartOneToMany.Ecart.mapper.ItemMapper;
import com.EcartOneToMany.Ecart.model.CartRequest;
import com.EcartOneToMany.Ecart.model.CartResponse;
import com.EcartOneToMany.Ecart.repositories.CartRepository;
import com.EcartOneToMany.Ecart.entities.CartEntity;
import com.EcartOneToMany.Ecart.repositories.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CartService {


    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper, ItemMapper itemMapper, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public CartResponse createCart(CartRequest cartRequest) {
        CartEntity cartEntity = cartRepository.save(cartMapper.ModelToEntity(cartRequest));
//        cartRepository.save(cartEntity);
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cartEntity.getCartId());
        return cartResponse;
    }

    public List<CartRequest> getAllCarts() {
        List<CartEntity> cartEntity = cartRepository.findAll();
        List<CartRequest> cartRequest = cartMapper.EntitiesToModel(cartEntity);
        return cartRequest;
    }

    public CartRequest getCart(Long id) {
        CartEntity cartEntity= cartRepository.findById(id).get();
        log.info("Service is called");
        CartRequest cartRequest = cartMapper.entityToModel(cartEntity);
        return cartRequest;
        //return cartMapper.entityToModel(cartEntity);
    }

    public void deleteCart(Long id)
    {
        CartEntity cartEntity=cartRepository.findById(id).get();
        cartRepository.delete(cartEntity);
    }

    public CartRequest addItemToCart(Long cartId, Long itemId) {
        CartEntity cartEntity = cartRepository.findById(cartId).orElse(null);
        ItemEntity itemEntity = itemRepository.findById(itemId).orElse(null);
        List<ItemEntity> itemEntities = cartEntity.getItem();
        itemEntities.add(itemEntity);
        cartEntity.setCartId(cartId);
        cartRepository.save(cartEntity);

        return cartMapper.entityToModel(cartEntity);
    }

    public CartRequest removeItemFromCart(Long cartId, Long itemId) {
        CartEntity cartEntity = cartRepository.findById(cartId).orElse(null);
        List<ItemEntity> itemEntities = cartEntity.getItem();

        ItemEntity itemEntity = itemRepository.findById(itemId).get();
        itemEntities.remove(itemEntity);
        cartEntity.setCartId(cartId);
        cartRepository.save(cartEntity);

        return cartMapper.entityToModel(cartEntity);
    }


}
