package com.codecool.webshop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemStore {

    private List<Item> items;

    public ItemStore() {
        items = new ArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public void remove(int id) {
        for(int i = 0; i < getItems().size(); i++){
            if(getItems().get(i).getId() == id){
                items.remove(i);
                break;
            }
        }
    }

    public List<Item> getItems() {
       return items;
    }



}
