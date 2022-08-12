package com.george.springrestapi.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeResponse {

    private Long id;

    private List<String> department;

    private String employeeName;
}
