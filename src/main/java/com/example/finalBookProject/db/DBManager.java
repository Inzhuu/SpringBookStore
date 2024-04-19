package com.example.finalBookProject.db;

import java.util.ArrayList;

public class DBManager {

    private static ArrayList<Items> items = new ArrayList<>();
    private static Long id = 5L;

    public static ArrayList<Items> getItems() {
        return items;
    }

    public static void addItem(Items item) {
        item.setId(id);
        items.add(item);
        id++;
    }

    public static Items getItem(Long id) {
        for (Items it : items) {
            if (it.getId() == id) return it;
        }
        return null;
    }
}
