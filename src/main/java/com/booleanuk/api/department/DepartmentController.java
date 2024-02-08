package com.booleanuk.api.department;

import com.booleanuk.api.department.dto.DepartmentDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper

    @GetMapping
    public List<DepartmentDTO> getAllDepartmentsNoUsers() {
        List<Department> departments = this.departmentRepository.findAll();

        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());

    }

    @GetMapping("/users")
    public List<Department> getAllDepartmentsWithUsers() {
        return this.departmentRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<DepartmentDTO> addOne(@RequestBody Department department) {
        return new ResponseEntity<>(modelMapper.map(this.departmentRepository.save(department), DepartmentDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateOne(@PathVariable int id, @Valid @RequestBody Department department) {
        Department dep = this.departmentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        dep.setName(department.getName());
        dep.setLocation(department.getLocation());
        return new ResponseEntity<>(modelMapper.map(this.departmentRepository.save(dep), DepartmentDTO.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDTO> deleteOne(@PathVariable int id) {
        Department department = this.departmentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            this.departmentRepository.delete(department);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department is linked, cannot be deleted");
        }

        return new ResponseEntity<>(modelMapper.map(department, DepartmentDTO.class), HttpStatus.OK);
    }

}
