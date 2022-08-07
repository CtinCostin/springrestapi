package com.george.springrestapi.service;

import com.george.springrestapi.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees(int pageNumber, int pageSize);

    Employee saveEmployee(Employee employee);

    Employee getSingleEmployee(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);

    List<Employee> getEmployeesByName(String name);

    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    List<Employee> getEmployeesByNameContaining(String keyword);

    List<Employee> getEmployeesByNameOrLocation(String name, String location);

    Integer deleteEmployeeByName(String name);


}
