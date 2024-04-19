package com.example.finalBookProject.services;

import com.example.finalBookProject.entities.ShopItems;
import com.example.finalBookProject.repositories.ShopItemRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ShopItemsService {

    private final ShopItemRepository shopItemRepository;

    @Autowired
    public ShopItemsService( ShopItemRepository shopItemRepository){
        this.shopItemRepository=shopItemRepository;
    }

    @Transactional
    public void save(ShopItems shopItems){
        shopItemRepository.save(shopItems);
    }
    @Transactional
    public void delete(Long id){
        shopItemRepository.deleteById(id);
    }
    @Transactional
    public void update(Long id, ShopItems updatedItem){
        updatedItem.setId(id);
        shopItemRepository.save(updatedItem);
    }
    public ShopItems findOne(Long id){
        Optional<ShopItems> foundUser = shopItemRepository.findById(id);

        return foundUser.orElse(null);
    }
    public ShopItems findById(Long id) {
        Optional<ShopItems> foundItem = shopItemRepository.findById(id);

        return foundItem.orElse(null);
    }

}
