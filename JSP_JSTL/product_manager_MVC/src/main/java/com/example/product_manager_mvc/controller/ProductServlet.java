package com.example.product_manager_mvc.controller;

import com.example.product_manager_mvc.model.Category;
import com.example.product_manager_mvc.model.Product;
import com.example.product_manager_mvc.sercive.*;
import com.example.product_manager_mvc.utils.Validate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private IProductService iProductService;
    private ICategory iCategory;

    @Override
    public void init() throws ServletException {
        iProductService = new ProductServiceImplMySql();
        iCategory = new CategoryImplMySql();
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
                showProducts(req, resp);

        }
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product productEdit = iProductService.findById(id);
        List<Category> categories = iCategory.getAllCategory();

        req.setAttribute("product", productEdit);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/edit-Product.jsp").forward(req, resp);
    }


    private void showProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = iProductService.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/list-Product.jsp").forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = iCategory.getAllCategory();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/create-Product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            default:
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        int idProduct = Integer.parseInt(req.getParameter("id"));
        Product product = iProductService.findById(idProduct);

        validateInputName(req, errors, product);
        validateInputPrice(req, errors, product);
        validateDescription(req, errors, product);
        validateInputCompany(req, errors, product);
        validateInputCategory(req, errors, product);

        if (errors.isEmpty()) {
            iProductService.update(idProduct, product);
            req.setAttribute("message", "updated");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
        }
        List<Category> categories = iCategory.getAllCategory();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/edit-Product.jsp").forward(req, resp);
    }

    private void validateInputName(HttpServletRequest req, List<String> errors, Product product) {
        String name = req.getParameter("name");
        if (!Validate.isNameValid(name)) {
            errors.add("Tên không hợp lệ. Tên phải từ 8-20 ký tự và bđ là chữ cái ");
        }
        product.setName(name);

    }
    private void validateInputPrice(HttpServletRequest req, List<String> errors, Product product) {
        try {
            String price = req.getParameter("price");
            if (!Validate.isPriceValid(price)) {
                errors.add("Giá không hợp lệ");
            } else {
                product.setPrice(Float.parseFloat(price));
            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("Định dạng số không đúng. Nhập lại!");
        }
    }
    private void validateDescription(HttpServletRequest req, List<String> errors, Product product) {
        String description = req.getParameter("desc");
        if (!Validate.isNameValid(description)) {
            errors.add("Mô tả không hợp lệ. Mô tả phải từ 8-20 ký tự và bđ là chữ cái");
        } else {
            product.setDescription(description);
        }
    }
    private void validateInputCompany(HttpServletRequest req, List<String> errors, Product product) {
        String company = req.getParameter("company");
        if (!Validate.isNameValid(company)) {
            errors.add("Tên công ty không hợp lệ. Tên công ty phải từ 8-20 ký tự và bđ là chữ cái");
        } else {
            product.setCompany(company);
        }
    }
    private void validateInputCategory(HttpServletRequest req, List<String> errors, Product product) {
        try {
            int idCategory = Integer.parseInt(req.getParameter("category"));
            Category category = iCategory.findById(idCategory);
            if (category == null) {
                errors.add("Không tìm thấy danh mục sp");
                product.setIdCategory(1);
            } else {
                product.setIdCategory(idCategory);
            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("Danh mục sản phẩm không hợp lệ");
        }
    }
    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id, String name, float price, String description, String company
        List<String> errors = new ArrayList<>();
        Product product = new Product();
        validateInputName(req, errors, product);
        validateInputPrice(req, errors, product);
        validateDescription(req, errors, product);
        validateInputCompany(req, errors, product);
        validateInputCategory(req, errors, product);

        if (errors.isEmpty()) {
            iProductService.save(product);
            req.setAttribute("message", "THÊM SẢN PHẨM THÀNH CÔNG");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
        }
        List<Category> categories = iCategory.getAllCategory();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/create-Product.jsp").forward(req, resp);
    }
}
