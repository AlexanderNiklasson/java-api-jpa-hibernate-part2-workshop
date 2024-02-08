package com.booleanuk.api.employee.dto;

import com.booleanuk.api.department.dto.DepartmentDTONoEmployee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private DepartmentDTONoEmployee department;


    // Constructor
    public EmployeeDTO(int id, String firstName, String lastName, DepartmentDTONoEmployee department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

}