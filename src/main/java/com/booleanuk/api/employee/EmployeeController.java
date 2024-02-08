package com.booleanuk.api.employee;

import com.booleanuk.api.department.Department;
import com.booleanuk.api.employee.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper

    @GetMapping
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable int id) {
        Employee employee = this.employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // Map Employee entity to EmployeeDTO
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addOne(@RequestBody Employee employee){
        return new ResponseEntity<>(this.employeeRepository.save(employee), HttpStatus.CREATED);
    }


}
