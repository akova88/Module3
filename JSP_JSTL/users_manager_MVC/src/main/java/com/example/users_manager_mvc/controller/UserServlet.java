package com.example.users_manager_mvc.controller;

import com.example.users_manager_mvc.dao.UserDAO;
import com.example.users_manager_mvc.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(req, resp);
                    break;
                case "edit":
                    updateUser(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                default:
                    listUser(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<User> listUser = userDAO.selectAllUsers();
        req.setAttribute("listUser", listUser);
        req.getRequestDispatcher("user/list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/create.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        req.setAttribute("user", existingUser);
        req.getRequestDispatcher("user/edit.jsp").forward(req, resp);
    }

    public void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(name, email, country);
        userDAO.insertUser(user);
        req.getRequestDispatcher("user/create.jsp").forward(req, resp);
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(id, name, email, country);
        userDAO.updateUser(user);
        req.getRequestDispatcher("user/edit.jsp").forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);
        List<User> listUser = userDAO.selectAllUsers();
        req.setAttribute("listUser", listUser);
        req.getRequestDispatcher("user/list.jsp").forward(req, resp);
    }





}
