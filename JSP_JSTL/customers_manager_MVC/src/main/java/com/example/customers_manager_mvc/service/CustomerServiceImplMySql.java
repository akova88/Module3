package com.example.customers_manager_mvc.service;

import com.example.customers_manager_mvc.DBContext;
import com.example.customers_manager_mvc.model.Customer;
import com.example.customers_manager_mvc.model.CustomerType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerServiceImplMySql extends DBContext implements ICustomerService{



    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try {
//          Lấy kết nối
            Connection connection = getConnection();
            // Dùng preparedStatement để bọc câu lệnh truy vấn
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers");
            System.out.println("Function findAll" + preparedStatement);
            // Thực thi câu lênh: executeQuery - select, executeUpdate - thêm/xóa/sửa
            ResultSet rs = preparedStatement.executeQuery();
            // rs.next(): đọc qua từng dòng
            while (rs.next()) {
                // getInt, getString: lấy giá trị theo tên cột hoặc chỉ số cột ( bắt đầu từ 1 )
                Customer c = getCustomerFromRs(rs);
                customers.add(c);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    private static Customer getCustomerFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String email = rs.getString("email");
        Date createAt = rs.getDate("createAt");
        int idType = rs.getInt("idType");
        Customer c = new Customer(id, name, email, address, createAt, idType);
        return c;
    }

    @Override
    public List<Customer> findAll2() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT c.*, ct.type, ct.delete_at FROM customers c join customertype ct on c.idType = ct.id order by c.id;");
            System.out.println("Function findAll2: " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                Date createAt = rs.getDate("createAt");
                int idType = rs.getInt("idType");

                String type = rs.getString("type");
                java.sql.Date sqlDeleteAt = rs.getDate("delete_At");
                LocalDate deleteAt = null;
                if (sqlDeleteAt != null) {
                    deleteAt = sqlDeleteAt.toLocalDate();
                }
                CustomerType customerType = new CustomerType(idType, type, deleteAt);
                Customer customer = new Customer(id, name, email, address,  createAt, idType);
                customer.setCustomerType(customerType);
                customers.add(customer);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO customers (`name`, `email`, `address`, `createAt`, `idType`) VALUES (?, ?, ?, ?, ?)");
            // Đưa tham số vào preparedStatement
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAddress());
            ps.setDate(4, new java.sql.Date(new Date().getTime()));
            ps.setInt(5, customer.getIdType());

            System.out.println("Function save " + ps);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public Customer findById(int id) {
        
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customers where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
               Customer customer = getCustomerFromRs(rs);
                return customer;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public void update(int id, Customer customer) {

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("UPDATE `customers_jdbc`.`customers` SET `name` = ?, `email` = ?, `address` = ?, `createAt` = ?, `idType` = ? WHERE `id` = ?");

//            UPDATE `customers_jdbc`.`customers` SET `name` = 'Quang1', `email` = 'quang1@gmail.com', `address` = 'Hue1', `idType` = '2' WHERE (`id` = '1');
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAddress());
            ps.setDate(4, new java.sql.Date(new Date().getTime()));
            ps.setInt(5, customer.getIdType());
            ps.setInt(6, id);
            ps.executeUpdate();

            System.out.println("Function update: " + ps);
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void remove(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement
                    ("DELETE FROM `customers_jdbc`.`customers` WHERE (`id` = ?);");
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
}
