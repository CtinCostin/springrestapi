package com.george.springrestapi.repository;

import com.george.springrestapi.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findByName(String name);

    //SELECT * FROM employee WHERE name="Rares" AND location="Buzau";
    List<Employee> findByNameAndLocation(String name, String location);

    //SELECT * FROM employee WHERE name LIKE "%rar%"
    List<Employee> findByNameContaining(String keyword, Sort sort);

    @Query("FROM Employee WHERE name = :name OR location = :location")
    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    @Transactional
    @Modifying
    @Query("DELETE FROM Employee WHERE name = :name")
    Integer deleteEmployeeByName(String name);

    List<Employee> findByDepartmentName(String name);

    @Query("FROM Employee WHERE department.name = :name")
    List<Employee> getEmployeesByDepartmentName(String name);
}
