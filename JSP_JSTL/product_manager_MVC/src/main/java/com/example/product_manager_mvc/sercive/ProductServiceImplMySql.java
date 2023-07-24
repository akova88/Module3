package com.example.product_manager_mvc.sercive;

import com.example.product_manager_mvc.model.Category;
import com.example.product_manager_mvc.model.Product;
import com.example.product_manager_mvc.utils.DBContext;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.product_manager_mvc.sercive.CategoryImplMySql.getCategoryFromRs;

public class ProductServiceImplMySql extends DBContext implements IProductService{
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT p.*, c.namecategory, c.delete_at FROM products_jdbc.products p join category c on p.idCategory = c.idcategory;");
            System.out.println("Function findAll" + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = getProductFromRs(rs);
                products.add(product);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return products;
    }

    private static Product getProductFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Float price = rs.getFloat("price");
        String description = rs.getString("description");
        String company = rs.getString("company");
        int idCategory = rs.getInt("idCategory");

        ICategory iCategory = new CategoryImplMySql();
        Category category = iCategory.findById(idCategory);
        Product product = new Product(id, name, price, description, company, idCategory);
        product.setCategory(category);
        return product;
    }

    @Override
    public void save(Product product) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO `products_jdbc`.`products` (`name`, `price`, `description`, `company`, `idCategory`) " +
                            "VALUES (?, ?, ?, ?, ?);");
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getCompany());
            ps.setInt(5, product.getIdCategory());
            System.out.println("Function save " + ps);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public Product findById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from products where id = ?");
            ps.setInt(1, id);
            System.out.println("Function findByid" + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product  product = getProductFromRs(rs);
                return product;
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public void update(int id, Product product) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("UPDATE `products_jdbc`.`products` SET `name` = ?, `price` = ?, `description` = ?, `company` = ?, `idCategory` = ? WHERE (`id` = ?);");
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getCompany());
            ps.setInt(5, product.getIdCategory());
            ps.setInt(6, id);
            ps.executeUpdate();
            System.out.println("Function update" + ps);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void remove(int id) {

    }
}
