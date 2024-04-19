package com.example.finalBookProject.services;

import com.example.finalBookProject.entities.Cart;
import com.example.finalBookProject.entities.ShopItems;
import com.example.finalBookProject.entities.Users;
import com.example.finalBookProject.repositories.CartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartsService {
    private final CartsRepository cartsRepository;

    @Autowired
    public CartsService(CartsRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }

    @Transactional
    public void saveOrder(Cart cart){
        cartsRepository.save(cart);
    }

    public List<Cart> findByUsers(Users users){
        List<Cart> carts = cartsRepository.findByUsers(users);
        List<ShopItems> shopItems = new ArrayList<>();

        for (Cart b: carts) {
            shopItems.add(b.getShopItems());
        }

        return carts;
    }

    @Transactional
    public void delete(Users users, ShopItems shopItems){
        Cart cart = cartsRepository.findByUsersAndShopItems(users, shopItems);
        cartsRepository.deleteById(cart.getId());
    }

    public Cart findByUsersAndShopItems(Users users, ShopItems shopItems){
        return cartsRepository.findByUsersAndShopItems(users, shopItems);
    }

    @Transactional
    public void update(Long id, Cart updatedBasket){
        updatedBasket.setId(id);
        cartsRepository.save(updatedBasket);
    }

    /*
    @Transactional
    public void delete(Long id){
        cartsRepository.deleteById(id);
    }*/

    @Transactional
    public void delete(Long cartId) {
            cartsRepository.deleteById(cartId);
    }
}
