package com.example.finalBookProject.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "author", length = 200)
    private String author;

    @Column(name = "description", length = 600)
    private String description;
    @Column(name = "price")
    private int price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "img_path", length = 200)
    private String imgPath;

    @ManyToOne(fetch = FetchType.EAGER)
    private Countries country;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Categories> categories;
}
