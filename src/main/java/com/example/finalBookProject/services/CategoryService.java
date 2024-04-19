package com.example.finalBookProject.services;

import com.example.finalBookProject.entities.Categories;
import com.example.finalBookProject.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Categories> findAll() {
        return categoryRepository.findAll();
    }

    public Categories getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }


}