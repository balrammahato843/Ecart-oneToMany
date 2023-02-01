package com.EcartOneToMany.Ecart.controllers;

import com.EcartOneToMany.Ecart.model.CartRequest;
import com.EcartOneToMany.Ecart.model.CartResponse;
import com.EcartOneToMany.Ecart.services.CartService;
import com.EcartOneToMany.Ecart.services.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Log4j2
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;

    @Autowired
    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @PostMapping( value = "/carts" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest cartRequest)
    {
        CartResponse cartResponse= cartService.createCart(cartRequest);
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @GetMapping( value = "/carts" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<CartRequest>> getAllCarts()
    {
        List<CartRequest> cartRequest= cartService.getAllCarts();
        return new ResponseEntity<>(cartRequest , HttpStatus.OK);
    }

    @GetMapping(value = "/carts/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartRequest> getCart(@PathVariable Long id)
    {
        log.info("controller is called....");
        return new ResponseEntity<>(cartService.getCart(id),HttpStatus.OK);
    }

    @DeleteMapping(value = "/carts/{id}")
    public String deleteCart(@PathVariable Long id)
    {
        log.info("controller is callled....");
        cartService.deleteCart(id);
        return "Cart is deleted with id: "+id;
    }

    @PostMapping( value = "/carts/{cartsId}/items/{itemId}" , consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<CartRequest> addItemToCart(@PathVariable("cartsId") Long cartId,
                                                     @PathVariable Long itemId)
    {
        return   new ResponseEntity<>(cartService.addItemToCart(cartId,itemId),HttpStatus.OK);
    }

    @DeleteMapping( value = "/carts/{cartId}/items/{itemId}" , consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<CartRequest> removeItemFromCart(@PathVariable Long cartId,
                                                          @PathVariable Long itemId)
    {
        return   new ResponseEntity<>(cartService.removeItemFromCart(cartId,itemId),HttpStatus.OK);
    }





    }



