package com.example.finalBookProject.services.impl;

import com.example.finalBookProject.entities.Cart;
import com.example.finalBookProject.entities.Categories;
import com.example.finalBookProject.entities.Countries;
import com.example.finalBookProject.entities.ShopItems;
import com.example.finalBookProject.repositories.CategoryRepository;
import com.example.finalBookProject.repositories.CountryRepository;
import com.example.finalBookProject.repositories.ShopItemRepository;
import com.example.finalBookProject.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ShopItems addItem(ShopItems item) {
        return shopItemRepository.save(item);
    }

    @Override
    public List<ShopItems> getAllItems() {
        return shopItemRepository.findAllByAmountGreaterThanOrderByPriceDesc(0);
    }

    @Override
    public ShopItems getItem(Long id) {
        return shopItemRepository.findByIdAndAmountGreaterThan(id,0);
    }

    public ShopItems findById(Long id){
        Optional<ShopItems> foundProduct =  shopItemRepository.findById(id);
        return foundProduct.orElse(null);

    }

    @Override
    public void deleteItem(ShopItems item) {
        shopItemRepository.delete(item);
    }

    @Override
    public ShopItems saveItem(ShopItems item) {
        return shopItemRepository.save(item);
    }

    @Override
    public List<Countries> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Countries addCountry(Countries country) {
        return countryRepository.save(country);
    }

    @Override
    public Countries saveCountry(Countries country) {
        return countryRepository.save(country);
    }

    @Override
    public Countries getCountry(Long id) {
        return countryRepository.getOne(id);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Categories saveCategory(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public Categories addCategory(Categories category) {
        return categoryRepository.save(category);
    }


    @Transactional
    public void update(Long id, ShopItems updatedItem) {
        updatedItem.setId(id);
        shopItemRepository.save(updatedItem);
    }
}
