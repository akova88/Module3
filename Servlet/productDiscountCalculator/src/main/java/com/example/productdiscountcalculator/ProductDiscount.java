package com.example.productdiscountcalculator;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ProductDiscount", value = "/discount")
public class ProductDiscount extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        float price = Float.parseFloat(req.getParameter("price"));
        float discount = Float.parseFloat(req.getParameter("discount"));
        float dcPrice = (float) (price * discount * 0.01);

        req.setAttribute("des", description);
        req.setAttribute("price", price);
        req.setAttribute("disc", discount);
        req.setAttribute("dcP", dcPrice);

        req.getRequestDispatcher("/display-discount.jsp").forward(req, resp);
    }

    public void destroy() {
    }
}