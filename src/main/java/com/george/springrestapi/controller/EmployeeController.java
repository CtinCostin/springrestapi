package com.george.springrestapi.controller;

import com.george.springrestapi.dao.EmployeeDaoImplementation;
import com.george.springrestapi.model.Department;
import com.george.springrestapi.model.Employee;
import com.george.springrestapi.repository.DepartmentRepo;
import com.george.springrestapi.repository.EmployeeRepo;
import com.george.springrestapi.request.EmployeeRequest;
import com.george.springrestapi.response.EmployeeResponse;
import com.george.springrestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeDaoImplementation daoImplementation;

    @Value("${app.name:Employee Management}")
    private String appName;

    @Value("${app.version:version 1}")
    private String appVersion;


    @GetMapping("/version")
    public String getAppDetails() {
        return appName + " - " + appVersion;
    }


//    @GetMapping("/employees")
//    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
//        return new ResponseEntity<List<Employee>>(employeeService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
//    }

//    @GetMapping("/employees")
//    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
//        List<Employee> list = (List<Employee>) employeeRepo.findAll();
//        List<EmployeeResponse> responseList = new ArrayList<>();
//        list.forEach(e -> {
//            EmployeeResponse employeeResponse = new EmployeeResponse();
//            employeeResponse.setId(e.getId());
//            employeeResponse.setEmployeeName(e.getName());
//            List<String> depts = new ArrayList<>();
//            for (Department d : e.getDepartmentList()) {
//                depts.add(d.getName());
//            }
//            employeeResponse.setDepartment(depts);
//            responseList.add(employeeResponse);
//        });
//        return new ResponseEntity<List<EmployeeResponse>>(responseList, HttpStatus.OK);
//
//    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<Employee>(employeeService.getSingleEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest) {
        //case with department is the child of employee//
//        Department dept = new Department();
//        dept.setName(eRequest.getDepartment());
//
//        dept = departmentRepo.save(dept);
//
//        Employee employee = new Employee(eRequest);
//        employee.setDepartment(dept);
//
//        employee = employeeRepo.save(employee);
//        return new ResponseEntity<Employee>(employeeRepo.save(employee), HttpStatus.CREATED);

        //case with employee is the child of department//
        Employee employee = new Employee(eRequest);
        employee = employeeRepo.save(employee);

        for (String s : eRequest.getDepartment()) {
            Department d = new Department();
            d.setName(s);
            d.setEmployee(employee);

            departmentRepo.save(d);
        }
        return new ResponseEntity<String>("Record saved successfully", HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/filterByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByNameAndLocation")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByKeyword")
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/employees/{name}/{location}")
    public ResponseEntity<List<Employee>> getEmployeesByNameOrLocation(@PathVariable String name, @PathVariable String location) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameOrLocation(name, location), HttpStatus.OK);
    }


//    @GetMapping("/employees/filter/{name}")
//    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String name) {
//        //return new ResponseEntity<List<Employee>>(employeeRepo.findByDepartmentName(name), HttpStatus.OK);
//        return new ResponseEntity<List<Employee>>(employeeRepo.getEmployeesByDepartmentName(name), HttpStatus.OK);
//    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        // return employeeRepo.getEmployees();
        // return daoImplementation.getAllEmployees();
        return employeeRepo.getAllRecords();
    }
}
