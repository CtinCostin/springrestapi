package com.george.springrestapi.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeRequest {

    private String name;

    private List<String> department;
}
