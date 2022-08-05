package com.george.springrestapi.repository;

import com.george.springrestapi.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findByName(String name);

    //SELECT * FROM employee WHERE name="Rares" AND location="Buzau";
    List<Employee> findByNameAndLocation(String name, String location);

    //SELECT * FROM employee WHERE name LIKE "%rar%"
    List<Employee> findByNameContaining(String keyword, Sort sort);
}
