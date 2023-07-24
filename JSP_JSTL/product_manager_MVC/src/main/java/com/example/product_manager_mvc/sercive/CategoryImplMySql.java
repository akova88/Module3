package com.example.product_manager_mvc.sercive;

import com.example.product_manager_mvc.model.Category;
import com.example.product_manager_mvc.utils.DBContext;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CategoryImplMySql extends DBContext implements ICategory{
    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM products_jdbc.category;");
            System.out.println("Function getAllCategory" + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = getCategoryFromRs(rs);
                categories.add(category);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return categories;
    }

    public static Category getCategoryFromRs(ResultSet rs) throws SQLException {
        int idCategory = rs.getInt("idcategory");
        String nameCategory = rs.getString("namecategory");
        Date sqlDeleteAt = rs.getDate("delete_at");
        LocalDate deleteAt = null;
        if (sqlDeleteAt != null) {
            deleteAt = sqlDeleteAt.toLocalDate();
        }
        Category  category = new Category(idCategory, nameCategory, deleteAt);
        return category;
    }

    @Override
    public Category findById(int idCategory) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT * FROM products_jdbc.category where `idcategory` = ?;");
            ps.setInt(1, idCategory);
            System.out.println("Function findById");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = getCategoryFromRs(rs);
                return category;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
}
