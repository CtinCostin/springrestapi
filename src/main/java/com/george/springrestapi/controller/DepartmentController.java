package com.george.springrestapi.controller;

import com.george.springrestapi.model.Department;
import com.george.springrestapi.repository.DepartmentRepo;
import com.george.springrestapi.response.DepartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping("/departments")
    public List<DepartmentResponse> getDepartments() {
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentResponse> responseList = new ArrayList<>();
        departments.forEach(d -> {
            DepartmentResponse departmentResponse = new DepartmentResponse();
            departmentResponse.setDepartmentName(d.getName());
            departmentResponse.setId(d.getId());
            departmentResponse.setEmployeeName(d.getEmployee().getName());
            responseList.add(departmentResponse);
        });
        return responseList;
    }
}
