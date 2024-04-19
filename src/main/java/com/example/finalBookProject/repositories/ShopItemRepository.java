package com.example.finalBookProject.repositories;

import com.example.finalBookProject.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ShopItemRepository extends JpaRepository<ShopItems,Long> {

    List<ShopItems> findAllByAmountGreaterThanOrderByPriceDesc(int amount);
    ShopItems findByIdAndAmountGreaterThan(Long id,int amount);
}
