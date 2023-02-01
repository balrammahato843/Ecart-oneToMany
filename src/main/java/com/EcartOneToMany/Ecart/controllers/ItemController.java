package com.EcartOneToMany.Ecart.controllers;

import com.EcartOneToMany.Ecart.model.ItemRequest;
import com.EcartOneToMany.Ecart.model.ItemResponse;
import com.EcartOneToMany.Ecart.services.CartService;
import com.EcartOneToMany.Ecart.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;
    private final CartService cartService;

    public ItemController(ItemService itemService, CartService cartService) {
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @PostMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest itemRequest)
    {
        return new ResponseEntity<>(itemService.addItem(itemRequest), HttpStatus.CREATED);
    }


    @GetMapping(value = "/items",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemRequest>> getItems()
    {
        return  new ResponseEntity<>(itemService.getAllItems(),HttpStatus.OK);
    }

    @GetMapping(value = "/items/{itemId}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemRequest> getItemById(@PathVariable Long itemId){

        return  new ResponseEntity<>(itemService.getItem(itemId),HttpStatus.OK);
    }

    @DeleteMapping("items/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId)
    {
        return  new ResponseEntity<>(itemService.deleteItem(itemId),HttpStatus.OK);
    }




}