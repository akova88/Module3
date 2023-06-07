package com.example.calculator;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "calculatorServlet", value = "/calculator")
public class calculatorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/calculator.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float firstOperand = (float) Double.parseDouble(req.getParameter("first-operand"));
        float secondOperand = (float) Double.parseDouble(req.getParameter("second-operand"));

        char operator = req.getParameter("operator").charAt(0);
        try {
            float result = Calculator.calculator(firstOperand, secondOperand, operator);
            req.setAttribute("result", result);
        } catch (RuntimeException ex) {
            req.setAttribute("result", "Phép chia không hợp lệ");
        }
        req.getRequestDispatcher("/calculator.jsp").forward(req, resp);
    }

    public void destroy() {
    }
}