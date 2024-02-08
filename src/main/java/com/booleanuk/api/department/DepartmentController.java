package com.booleanuk.api.department;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper
    @GetMapping
    public List<Department> getAllDepartments() {
        return this.departmentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Department> addOne(@RequestBody Department department){
        return new ResponseEntity<>(this.departmentRepository.save(department), HttpStatus.CREATED);
    }
}
