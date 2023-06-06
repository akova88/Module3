package com.example.demojstl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/jstlCoreExample01")
public class JstlCoreEx01Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JstlCoreEx01Servlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Truy vấn dữ liệu từ DB ( MÔ Phỏng)
        List<Dept> list = DBUtils.queryDepartments();

//        Lưu dữ liệu vào thuộc tính 'departments' của request
        req.setAttribute("departments", list);

//        Tạo đối tượng RequestDispatcher
        // để Forward (chuyển tiếp) yêu cầu tới jstl_core_example01.jsp
        req.getRequestDispatcher("/jstl_core_example01.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
