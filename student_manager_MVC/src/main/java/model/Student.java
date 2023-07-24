package model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
    private int idClass;
    private Classroom classroom;

    public Student() {
    }

    public Student(int id, String name, LocalDate dob, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Student(int id, String name, LocalDate dob, String address, String phone, String email, int idClass) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idClass = idClass;
    }

    public Student(int id, String name, LocalDate dob, String address, String phone, String email, Classroom classroom) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
