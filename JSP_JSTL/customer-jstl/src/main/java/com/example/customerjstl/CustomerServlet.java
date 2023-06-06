package com.example.customerjstl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {
    private String message;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Customer> customers = new ArrayList<>();
        customers.add( new Customer("Mai Văn Hoàn", "20-08-1983", "Hà Nội","https://bizweb.dktcdn.net/100/409/603/files/bao-gia-in-anh-the-lay-ngay.jpg?v=1631007146881"));
        customers.add( new Customer("John Doe", "20-08-1983", "Huế","https://bizweb.dktcdn.net/100/409/603/files/bao-gia-in-anh-the-lay-ngay.jpg?v=1631007146881"));
        customers.add( new Customer("David Beckham", "20-08-1983", "England","https://bizweb.dktcdn.net/100/409/603/files/bao-gia-in-anh-the-lay-ngay.jpg?v=1631007146881"));
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/customer.jsp").forward(request, response);
    }

    public void destroy() {
    }
}