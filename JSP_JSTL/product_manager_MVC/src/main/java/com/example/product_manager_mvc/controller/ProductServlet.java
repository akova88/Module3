package com.example.product_manager_mvc.controller;

import com.example.product_manager_mvc.model.Product;
import com.example.product_manager_mvc.sercive.IProductService;
import com.example.product_manager_mvc.sercive.ProductServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private IProductService iProductService;

    @Override
    public void init() throws ServletException {
        iProductService = new ProductServiceImp();
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
        req.setAttribute("product", productEdit);
        req.getRequestDispatcher("/edit-Product.jsp").forward(req, resp);
    }


    private void showProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = iProductService.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/list-Product.jsp").forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        int idProduct = Integer.parseInt(req.getParameter("id"));
        Product product = iProductService.findById(idProduct);
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        String description = req.getParameter("decs");
        String company = req.getParameter("company");
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCompany(company);

        iProductService.update(idProduct, product);
        req.setAttribute("message", "updated");
        req.getRequestDispatcher("/edit-Product.jsp").forward(req, resp);
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id, String name, float price, String description, String company
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        String description = req.getParameter("desc");
        String company = req.getParameter("company");
        long id = System.currentTimeMillis()% 100000;

        Product product = new Product((int) id, name, price, description, company);
        iProductService.save(product);

        req.setAttribute("message", "THÊM SẢN PHẨM THÀNH CÔNG");
        req.getRequestDispatcher("/create-Product.jsp").forward(req, resp);
    }
}
