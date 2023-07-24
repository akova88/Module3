package com.example.customers_manager_mvc.controller;

import com.example.customers_manager_mvc.model.Config;
import com.example.customers_manager_mvc.model.Customer;
import com.example.customers_manager_mvc.model.CustomerType;
import com.example.customers_manager_mvc.service.*;
import com.example.customers_manager_mvc.utils.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Utilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private ICustomerService iCustomerService;
    private ICustomerType iCustomerType;

    @Override
    public void init() throws ServletException {
        iCustomerService = new CustomerServiceImplMySql();
        iCustomerType = new CustomerTypeImplMySql();
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
        List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();

        req.setAttribute("customer", customerEdit);
        req.setAttribute("customerTypes", customerTypes);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "edit.jsp").forward(req, resp);
    }

    private void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = iCustomerService.findAll2();

        req.setAttribute("customers", customers);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "list.jsp").forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();
        req.setAttribute("customerTypes", customerTypes);
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
        List<String> errors = new ArrayList<>();
        int idCustomer = Integer.parseInt(req.getParameter("id"));
        Customer customer = iCustomerService.findById(idCustomer);

        validateInputName(req, errors, customer);
        validateInputEmail(req, errors, customer);
        validateInputAddress(req, errors, customer);
        validateInputCustomerType(req, errors, customer);
        if (errors.isEmpty()) {
            iCustomerService.update(idCustomer, customer);
            req.setAttribute("message", "Sửa thành công");
//            resp.sendRedirect("/customers");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("customer", customer);
        }
        List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();
        req.setAttribute("customerTypes", customerTypes);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "edit.jsp").forward(req, resp);
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Customer customer = new Customer();

        validateInputName(req, errors, customer);
        validateInputEmail(req, errors, customer);
        validateInputCustomerType(req, errors, customer);
        validateInputAddress(req, errors, customer);

//        long id = System.currentTimeMillis() % 100000;

        if (errors.isEmpty()) {
            iCustomerService.save(customer);
            req.setAttribute("message", "Thêm thành công!");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("customer", customer);
        }
        List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();
        req.setAttribute("customerTypes", customerTypes);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "create.jsp").forward(req, resp);
    }

    private static void validateInputAddress(HttpServletRequest req, List<String> errors, Customer customer) {
        String address = req.getParameter("address");
        if (!ValidateUtils.isAddressValid(address)) {
            errors.add("Địa chỉ không hợp lệ");
        } else {
            customer.setAddress(address);
        }
    }

    private void validateInputCustomerType(HttpServletRequest req, List<String> errors, Customer customer) {
        try {
            int idType = Integer.parseInt(req.getParameter("customertype"));
            CustomerType ct = iCustomerType.findById(idType);
            if (ct == null) {
                errors.add("Không tìm thấy loại khách hàng");
                customer.setIdType(1);
            } else {
                customer.setIdType(idType);
            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("Loại khách hàng không hợp lệ");
        }

    }

    private static void validateInputEmail(HttpServletRequest req, List<String> errors, Customer customer) {
        String email = req.getParameter("email");
        if (!ValidateUtils.isEmailValid(email)) {
            errors.add("Email không hợp lệ! ");
        } else {
            customer.setEmail(email);
        }
    }

    private static void validateInputName(HttpServletRequest req, List<String> errors, Customer customer) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isNameValid(name)) {
            errors.add("Tên không hợp lệ. Tên phải từ 8-20 ký tự và bđ là chữ cái ");
        } else {
            customer.setName(name);
        }
    }
}
