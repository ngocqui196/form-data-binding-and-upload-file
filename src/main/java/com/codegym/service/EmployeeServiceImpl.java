package com.codegym.service;


import com.codegym.model.Employee;

import java.util.ArrayList;

import java.util.List;


public class EmployeeServiceImpl implements IEmployeeService {

    private static List<Employee> listStudent;
    static {
        listStudent = new ArrayList<>();
        listStudent.add( new Employee(1, "Thành", "Hà Nội"));
        listStudent.add( new Employee(2, "Tuấn Anh", "Hà Đông"));
        listStudent.add( new Employee(3, "Tú", "Hải Dương"));
        listStudent.add( new Employee(4, "Dũng", "Hải Phòng"));
    }

    @Override
    public List<Employee> findAll() {
        return listStudent;
    }

    @Override
    public Employee findById(int id) {
        for (Employee employee:
                listStudent ) {
            if (employee.getId() == id)
                return employee;
        }
        return null;
    }

    @Override
    public void update(int id,Employee employee) {
        listStudent.set(listStudent.indexOf(this.findById(id)),employee);
    }

    @Override
    public void save(Employee employee) {
        listStudent.add(employee);
    }

    @Override
    public void remove(int id) {
        listStudent.removeIf(employee -> employee.getId() == id);
    }
}