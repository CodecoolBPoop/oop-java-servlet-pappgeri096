package com.codecool.webshop;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ItemStore extends HttpServlet {

    public HttpSession session;

    public ItemStore(HttpServletRequest request) {
        session = request.getSession();
        if(session.isNew())
            session.setAttribute("items",new ArrayList<Item>());
    }

    public void add(Item item) {
       getItems().add(item);
    }

    public void remove(int id) {

        for(int i = 0; i < getItems().size(); i++){
            if(getItems().get(i).getId() == id){
                getItems().remove(i);
                break;
            }
        }
    }

    public List<Item> getItems() {
       return (List<Item>) session.getAttribute("items");
    }



}
