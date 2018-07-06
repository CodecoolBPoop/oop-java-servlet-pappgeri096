package com.codecool.webshop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "simpleServlet", urlPatterns = {"/","/add"}, loadOnStartup = 1)
public class WebShopServlet extends HttpServlet {

    private List<Item> items = new ArrayList<>();
    public static ItemStore store;

    public WebShopServlet() {
        initItems();
    }

    private void initItems() {
       items.add(new Item("Asus Laptop", 1800));
       items.add(new Item("Harry Potter Ebook", 50));
       items.add(new Item("Lego", 80));
    }

    private String showItems(){
        StringBuilder html = new StringBuilder();

        for(Item item : items){
            html.append("\n<tr>\n<td>").append(item.getName()).append(" </td>\n<td> ").append(item.getPrice()).append("</td>\n");
            html.append("<td><form action='/add' method='post' ><input type='hidden' name='itemID' value='"+item.getId()+"' /><button type='submit' class='btn btn-info'>Add</button></form></td>\n");
            html.append("<td><form action='/delete' method='post'><input type='hidden' name='itemID' value='"+item.getId()+"' /><button type='submit' class='btn btn-danger'>Remove</button></form></td>\n</tr>");
        }

        return html.toString();
    }

    private Item getItemFromListById(int id){
        for(Item item : items){
            if(item.getId() == id)
                return item;
        }

        return new Item("nope",33);
    }

    private Item getItemFromCartById(int id){
        for(Item item : store.getItems()){
            if(item.getId() == id)
                return item;
        }

        return new Item("nope",33);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          PrintWriter out = response.getWriter();
        if(request.getRequestURI().equals("/add") && request.getMethod().equals("POST")){
            Integer id = Integer.parseInt(request.getParameter("itemID"));
            Item item = getItemFromListById(id);
            store.add(item);
            response.sendRedirect("/");
        }

        if(request.getRequestURI().equals("/delete") && request.getMethod().equals("POST")){
            Integer id = Integer.parseInt(request.getParameter("itemID"));
            store.remove(id);
            response.sendRedirect("/");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        store = new ItemStore(request);

        response.setIntHeader("Refresh", 60);

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

                out.println(
                "<html>\n" +
                        "<head>\n<title>Webshop</title>\n<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css' rel='stylesheet'></head>\n" +
                        "<body>\n<div class='container mt-3'>\n<table class='table table-bordered table-dark'>\n" + showItems() + "\n</table>\n"+
                        "<a href='/cart' class='btn btn-warning'>Check Shopping Cart</a>'"+"</div>\n</body>"+"\n</html>"
        );



    }
}
