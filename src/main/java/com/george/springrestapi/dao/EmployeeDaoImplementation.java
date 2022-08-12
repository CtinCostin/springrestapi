package com.george.springrestapi.dao;

import com.george.springrestapi.model.Employee;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDaoImplementation {

    @PersistenceContext
    private EntityManager manager;

    public List<Employee> getAllEmployees() {
        return manager.createNamedQuery("getAllRecords", Employee.class).getResultList();
    }
}
