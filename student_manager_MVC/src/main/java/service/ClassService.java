package service;

import model.Classroom;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassService extends DBContext {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ClassService() {
    }

    public List<Classroom> getAllClassroom() {
        List<Classroom> classrooms = new ArrayList<>();
        try {
            connection = getConnection();

            //magic string
            ps = connection.prepareStatement("SELECT * FROM students_jdbc.classroom");
            System.out.println("Function getAllClassroom " + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                Classroom classroom = getClassFromRs(rs);
                classrooms.add(classroom);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return classrooms;
    }

    private Classroom getClassFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Classroom classroom = new Classroom(id, name);
        return classroom;
    }
    public Classroom findById(int id) {
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM students_jdbc.classroom WHERE id = ?");
            ps.setInt(1, id);
            System.out.println("Function findById " + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                Classroom classroom = getClassFromRs(rs);
                return classroom;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
}
