package com.example.finalBookProject.services.impl;

import com.example.finalBookProject.entities.Cart;
import com.example.finalBookProject.entities.ShopItems;
import com.example.finalBookProject.entities.Users;
import com.example.finalBookProject.repositories.CartsRepository;
import com.example.finalBookProject.repositories.ShopItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl {
    private final CartsRepository cartsRepository;
    private final ShopItemRepository shopItemRepository;

    @Autowired
    public CartServiceImpl(CartsRepository cartsRepository, ShopItemRepository shopItemRepository) {
        this.cartsRepository = cartsRepository;
        this.shopItemRepository = shopItemRepository;
    }

    public List<Cart> findByUsers(Users users) {
        List<Cart> carts = cartsRepository.findByUsers(users);

        // You might want to return the list of products instead of carts
        List<ShopItems> products = new ArrayList<>();
        for (Cart cart : carts) {
            products.add(cart.getShopItems());
        }

        return carts;
    }

    public ShopItems findById(Long id) {
        Optional<ShopItems> foundProduct = shopItemRepository.findById(id);

        return foundProduct.orElse(null);
    }

    public Cart findByUsersAndShopItems(Users users, ShopItems shopItems) {
        return cartsRepository.findByUsersAndShopItems(users, shopItems);
    }

    @Transactional
    public void saveOrder(Cart cart) {
        cartsRepository.save(cart);
    }

    @Transactional
    public void update(Long id, Cart updatedCart) {
        updatedCart.setId(id);
        cartsRepository.save(updatedCart);
    }
}
