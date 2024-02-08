package com.booleanuk.api.department.dto;

import com.booleanuk.api.employee.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO {
    private int id;
    private String name;
    private String location;


    public DepartmentDTO(int id, String name, String location, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

}