package controller;

import model.Classroom;
import model.Student;
import service.ClassService;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet (name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;
    private ClassService classService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
        classService = new ClassService();
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
                showStudents(req, resp);
        }
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student studentEdit = studentService.findById(id);
        List<Classroom> classrooms = classService.getAllClassroom();

        req.setAttribute("student", studentEdit);
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/edit-Student.jsp").forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classroom> classrooms = classService.getAllClassroom();
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/create-Student.jsp").forward(req, resp);
    }

    private void showStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.findAll();

        req.setAttribute("students", students);
        req.getRequestDispatcher("/list-Student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createStudent(req, resp);
                break;
            case "edit":
                editStudent(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            case "search":
                searchStudent(req, resp);
                break;
            default:
        }
    }
    private void searchStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("kw");
        List<Student> students = studentService.search(keyword);
        req.setAttribute("kw", keyword);
        req.setAttribute("students", students);
        req.getRequestDispatcher("/list-Student.jsp").forward(req, resp);
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentService.remove(id);
        resp.sendRedirect("/students");
    }

    private void editStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.findById(id);
        setValue(req, errors, student);

        if (errors.isEmpty()) {
            studentService.update(id, student);
            req.setAttribute("message", "updated");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("student", student);
        }
        List<Classroom> classrooms = classService.getAllClassroom();
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/edit-Student.jsp").forward(req, resp);
    }

    private void setValue(HttpServletRequest req, List<String> errors, Student student) {
        String name = req.getParameter("name");
        student.setName(name);
        String dob = req.getParameter("dob");
        LocalDate dobSql = LocalDate.parse(dob);
        student.setDob(dobSql);
        String address = req.getParameter("address");
        student.setAddress(address);
        String phone = req.getParameter("dob");
        student.setPhone(phone);
        String email = req.getParameter("email");
        student.setEmail(email);
        int idClass = Integer.parseInt(req.getParameter("classroom"));
        Classroom classroom = classService.findById(idClass);
        if (classroom == null) {
            errors.add("Không tìm thấy classroom");
            student.setIdClass(1);
        } else {
            student.setIdClass(idClass);
        }
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Student student = new Student();
        setValue(req, errors, student);

        if (errors.isEmpty()) {
            studentService.save(student);
            req.setAttribute("message", "saved");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("student", student);
        }
        List<Classroom> classrooms = classService.getAllClassroom();
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/create-Student.jsp").forward(req, resp);
    }
}
