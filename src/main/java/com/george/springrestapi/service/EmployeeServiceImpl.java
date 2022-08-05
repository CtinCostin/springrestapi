package com.george.springrestapi.service;

import com.george.springrestapi.model.Employee;
import com.george.springrestapi.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

//    private static List<Employee> employeeList = new ArrayList<>();
//    static {
//        Employee e = new Employee();
//        e.setName("Ion");
//        e.setAge(43L);
//        e.setLocation("Buzau");
//        e.setEmail("ion@gmail.com");
//        e.setDepartment("IT");
//        employeeList.add(e);
//
//        e = new Employee();
//        e.setName("Costi");
//        e.setAge(40L);
//        e.setLocation("Bucuresti");
//        e.setEmail("coxti2000@gmail.com");
//        e.setDepartment("Sales");
//        employeeList.add(e);
//    }

    @Override
    public List<Employee> getEmployees(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize);
        return employeeRepo.findAll(pages).getContent();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepo.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        }
        throw new RuntimeException("The employee is not found for the id " + id);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepo.findByName(name);
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        return employeeRepo.findByNameAndLocation(name, location);
    }

    @Override
    public List<Employee> getEmployeesByNameContaining(String keyword) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return employeeRepo.findByNameContaining(keyword, sort);
    }
}
