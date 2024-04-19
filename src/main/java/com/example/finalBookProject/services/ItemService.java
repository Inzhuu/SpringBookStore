package com.example.finalBookProject.services;

import com.example.finalBookProject.entities.Categories;
import com.example.finalBookProject.entities.Countries;
import com.example.finalBookProject.entities.ShopItems;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemService {


    ShopItems addItem(ShopItems shopItems);

    List<ShopItems> getAllItems();

    ShopItems getItem(Long id);

    ShopItems findById(Long id);

    void deleteItem(ShopItems shopItems);

    ShopItems saveItem(ShopItems shopItems);


    List<Countries> getAllCountries();

    Countries addCountry(Countries countries);

    Countries saveCountry(Countries countries);

    Countries getCountry(Long id);


    List<Categories> getAllCategories();
    Categories getCategory(Long id);
    Categories saveCategory(Categories category);
    Categories addCategory(Categories category);

}
