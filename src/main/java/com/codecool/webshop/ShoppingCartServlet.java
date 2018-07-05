package com.codecool.webshop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cart", urlPatterns = {"/cart"}, loadOnStartup = 2)
public class ShoppingCartServlet extends HttpServlet {

    private String showCart() {
        StringBuilder html = new StringBuilder();

        for(Item item : WebShopServlet.store.getItems()){
            html.append("\n<tr>\n<td>").append(item.getName()).append(" </td>\n<td> ").append(item.getPrice()).append("</td>\n");
        }

        return html.toString();
    }

    private double amountToPay(){
        double amount = 0;

        for(Item item : WebShopServlet.store.getItems())
            amount += item.getPrice();

        return amount;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setIntHeader("Refresh", 60);

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();


        out.println(
                "<html>\n" +
                        "<head>\n<title>Webshop</title>\n<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css' rel='stylesheet'></head>\n" +
                        "<body>\n<div class='container mt-3'>\n<table class='table table-bordered table-dark'>\n" + showCart() + "\n</table>\n"+
                        "Sum of Price:"+amountToPay()+" USD"+"</div>\n</body>"+"\n</html>"
        );





    }
}
