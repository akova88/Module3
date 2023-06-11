package com.example.customers_manager_mvc.service;

import com.example.customers_manager_mvc.DBContext;
import com.example.customers_manager_mvc.model.CustomerType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeImplMySql extends DBContext implements ICustomerType{

    @Override
    public List<CustomerType> getAllCustomerTypes() {
        List<CustomerType> customerTypes = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers_jdbc.customertype;");

            System.out.println("Function getAllCustomers " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CustomerType customerType = getCustomerFromRs(rs);
                customerTypes.add(customerType);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customerTypes;
    }

    private static CustomerType getCustomerFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String type = rs.getString("type");
        Date sqlDeleteAt = rs.getDate("delete_at");

        LocalDate lDeleteAt = null;
        if (sqlDeleteAt != null) {
            lDeleteAt = sqlDeleteAt.toLocalDate();
        }
        CustomerType customerType = new CustomerType(id, type, lDeleteAt);
        return customerType;
    }

    @Override
    public CustomerType findById(long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM customers_jdbc.customertype WHERE `id`=?;");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CustomerType customerType = getCustomerFromRs(rs);
                return customerType;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
}
