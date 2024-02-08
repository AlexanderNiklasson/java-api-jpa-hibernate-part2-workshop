package com.booleanuk.api.department.dto;

import com.booleanuk.api.employee.dto.EmployeeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DepartmentDTO {
    private int id;
    private String name;
    private String location;
    private List<EmployeeDTO> employees; // List of users associated with the department


    public DepartmentDTO(int id, String name, String location, List<EmployeeDTO> employees) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employees = employees;
    }

}