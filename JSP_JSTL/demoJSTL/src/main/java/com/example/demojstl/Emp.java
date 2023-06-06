package com.example.demojstl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Emp {
    private int empNo;
    private String empName;
    private String job;
    private Date hireDate;
    private float Salary;
    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public Emp() {
    }

    public Emp(int empNo, String empName, String job, String hireDateStr, float salary) {
        this.empNo = empNo;
        this.empName = empName;
        this.job = job;
        try {
            this.hireDate = df.parse(hireDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Salary = salary;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        Salary = salary;
    }
}
