package com.example.javawebcurrencyconverter;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CurrencyConverter", value = "/convert")
public class CurrencyConverter extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/convert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float rate = Float.parseFloat(req.getParameter("rate"));
        float usd = Float.parseFloat(req.getParameter("usd"));

        long vnd = (long) (rate * usd);
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<h1>Rate: " + rate + "</h1>");
        out.println("<h1>USD: " + usd + "</h1>");
        out.println("<h1>VND: " + vnd + "</h1>");
        out.println("</html>");
    }

    public void destroy() {
    }
}