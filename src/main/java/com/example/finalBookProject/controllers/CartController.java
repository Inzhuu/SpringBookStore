package com.example.finalBookProject.controllers;

import com.example.finalBookProject.entities.*;
import com.example.finalBookProject.services.CartsService;
import com.example.finalBookProject.services.ItemService;
import com.example.finalBookProject.services.ShopItemsService;
import com.example.finalBookProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    private final UserService userService;
    private final CartsService cartsService;
    private final ShopItemsService shopItemsService;

    @Autowired
    private ItemService itemService;

    @Autowired
    public CartController(UserService userService, CartsService cartsService, ShopItemsService shopItemsService) {
        this.userService = userService;
        this.cartsService = cartsService;
        this.shopItemsService = shopItemsService;
    }

    @GetMapping(value = "/cardDescription/{id}")
    public String cardDescription(Model model, @PathVariable(name = "id") Long id) {
        ShopItems shopItems = itemService.getItem(id);
        model.addAttribute("shopItems", shopItems);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);
        return "User/cardDescription";
    }


    @PostMapping(value = "/cardDescription/{id}")
    public String addBasket(@PathVariable(name = "id") Long id, @RequestParam(name = "count") int count) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = userService.getUserByEmail(authentication.getName());

        ShopItems shopItems = shopItemsService.findById(id);
        Cart cart = cartsService.findByUsersAndShopItems(users, shopItems);

        if (users != null && shopItems != null && cart == null) {
            Cart newCart = new Cart();
            newCart.setUsers(users);
            newCart.setShopItems(shopItems);
            newCart.setCount(count);

            cartsService.saveOrder(newCart);
            shopItems.setAmount(shopItems.getAmount() - count);
            shopItemsService.update(shopItems.getId(), shopItems);

        } else if (users != null && shopItems != null) {
            shopItems.setAmount(cart.getCount() + shopItems.getAmount());
            if (count > 0) {
                cart.setCount(count);
            }

            shopItems.setAmount(shopItems.getAmount() - count);
            cartsService.update(cart.getId(), cart);
            shopItemsService.update(shopItems.getId(), shopItems);
        }

        return "redirect:/";
    }

    @GetMapping(value = "/cart")
    public String cart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Users users = userService.findByUsername(currentPrincipalName);
        List<Cart> carts = cartsService.findByUsers(users);

        if (carts != null) {
            int total = 0;
            int price = 0;
            for (Cart cart : carts) {
                total += cart.getCount();
                price += cart.getShopItems().getPrice() * cart.getCount();
            }

            model.addAttribute("total", total);
            model.addAttribute("price", price);
            model.addAttribute("cartList", carts);
        }

        return "User/cart";
    }

    @PostMapping(value = "/deleteCart/{id}")
    public String deleteCart(@PathVariable("id") Long id) {

        cartsService.delete(id);
        return "redirect:/cart";
    }
}
