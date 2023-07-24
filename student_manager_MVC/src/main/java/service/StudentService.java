package service;

import model.Classroom;
import model.Student;
import utils.DBContext;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService extends DBContext {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM students_jdbc.students");
            System.out.println("Function findAll" + ps);
            rs = ps.executeQuery();
            while (rs.next()) {

                Student student = getStudentFromRs(rs);
                students.add(student);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return students;
    }

    private Student getStudentFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Date sqlDob = rs.getDate("dob");
        LocalDate dob = null;
        if (sqlDob != null) {
            dob = sqlDob.toLocalDate();
        }
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        int idClass = rs.getInt("idClass");

        ClassService classService = new ClassService();
        Classroom classroom = classService.findById(idClass);
        Student student = new Student(id, name, dob, address, phone, email, classroom);
        return student;
    }
    public void save(Student student) {
        try {
            String query = "INSERT INTO `students_jdbc`.`students` (`name`, `dob`, `address`, `phone`, `email`, `idClass`) VALUES (?, ?, ?, ?, ?, ?);";
            connection = getConnection();
            ps = connection.prepareStatement(query);

            ps.setString(1, student.getName());
            ps.setDate(2, Date.valueOf(student.getDob()));
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getPhone());
            ps.setString(5,student.getEmail());
            ps.setInt(6, student.getIdClass());
            System.out.println("Function save" + ps);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
    public Student findById(int id) {
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM students_jdbc.students WHERE id = ?");
            ps.setInt(1, id);
            System.out.println("Function findAll" + ps);
            rs = ps.executeQuery();
            while (rs.next()) {

                Student student = getStudentFromRs(rs);
                return student;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
    public void update(int id, Student student) {
        try {
            String query = "UPDATE `students_jdbc`.`students` SET `name` = ?, `dob` = ?, `address` = ?, `phone` = ?, `email` = ?, `idClass` = ? WHERE (`id` = ?);";
            connection = getConnection();
            ps = connection.prepareStatement(query);

            ps.setString(1, student.getName());
            ps.setDate(2, Date.valueOf(student.getDob()));
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getPhone());
            ps.setString(5,student.getEmail());
            ps.setInt(6, student.getIdClass());
            ps.setInt(7, id);
            System.out.println("Function update" + ps);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
    public void remove(int id) {
        try {
            connection = getConnection();
            ps = connection.prepareStatement
                    ("DELETE FROM `students_jdbc`.`students` WHERE (`id` = ?);");
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
    public List<Student> search(String name) {
        List<Student> students = new ArrayList<>();
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM students_jdbc.students WHERE name like ?");
            ps.setString(1, "%"+name+"%");
            System.out.println("Function Search" + ps);
            rs = ps.executeQuery();
            while (rs.next()) {

                Student student = getStudentFromRs(rs);
                students.add(student);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return students;
    }
}
