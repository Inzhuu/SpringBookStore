package com.example.finalBookProject.repositories;

import com.example.finalBookProject.entities.Cart;
import com.example.finalBookProject.entities.ShopItems;
import com.example.finalBookProject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartsRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUsers(Users users);

    Cart findByUsersAndShopItems(Users users, ShopItems shopItems);

}