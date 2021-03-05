package com.codegym.service;

import com.codegym.model.Employee;

import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

public interface IEmployeeService{
    List<Employee> findAll();

    Employee findById(int id);

    void update(int id, Employee employee);

    void remove(int id);

    void save(Employee employee);
}
