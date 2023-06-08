package com.example.customers_manager_mvc.controller;

import com.example.customers_manager_mvc.model.Config;
import com.example.customers_manager_mvc.model.Customer;
import com.example.customers_manager_mvc.service.CustomerServiceImpl;
import com.example.customers_manager_mvc.service.ICustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private ICustomerService iCustomerService;

    @Override
    public void init() throws ServletException {
        iCustomerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

            switch (action) {
                case "create":
                    showFormCreate(req, resp);
                    break;
                case "edit":
                    showFormEdit(req, resp);
                    break;
                default:
                    showCustomers(req, resp);
                    break;
            }
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customerEdit = iCustomerService.findById(id);
        req.setAttribute("customer", customerEdit);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "edit.jsp").forward(req, resp);
    }

    private void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = iCustomerService.findAll();

        req.setAttribute("customers", customers);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "list.jsp").forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "create.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(req, resp);
                break;
            case "edit":
                editCustomer(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;
            default:
                break;
        }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idCustomer = Integer.parseInt(req.getParameter("idEdit"));
        iCustomerService.remove(idCustomer);
        resp.sendRedirect("/customers");
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCustomer = Integer.parseInt(req.getParameter("id"));
        Customer customer = iCustomerService.findById(idCustomer);
        String nameNew= req.getParameter("name");
        String emailNew = req.getParameter("email");
        String addressNew = req.getParameter("address");
        customer.setName(nameNew);
        customer.setEmail(emailNew);
        customer.setAddress(addressNew);

        iCustomerService.update(idCustomer, customer);
        req.setAttribute("message", "Sửa thành công");
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "edit.jsp").forward(req, resp);
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        long id = System.currentTimeMillis() % 100000;
        Customer customer = new Customer((int)id, name, email, address);
        iCustomerService.save(customer);

        req.setAttribute("message","Thêm thành công!");
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "create.jsp").forward(req, resp);
    }
}
