package com.george.springrestapi.model;

import com.george.springrestapi.request.EmployeeRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "employee")
//@NamedNativeQuery(name = "getAllRecords", query = "select * from employee", resultClass = Employee.class)
@NamedQuery(name = "Employee.getAllRecords", query = "FROM Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name should not be null!")
    private String name;

    private Long age = 0L;

    private String location;

    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

//    @JoinColumn(name = "department_id")
//    @OneToOne()
//    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Department> departmentList;

    public Employee(EmployeeRequest request) {
        this.name = request.getName();
    }


}
