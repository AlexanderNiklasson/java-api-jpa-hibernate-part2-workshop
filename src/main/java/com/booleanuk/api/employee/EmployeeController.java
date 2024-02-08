package com.booleanuk.api.employee;

import com.booleanuk.api.department.Department;
import com.booleanuk.api.department.DepartmentRepository;
import jakarta.validation.Valid;
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
    private DepartmentRepository departmentRepository;


    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper

    @GetMapping
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee employee = this.employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addOne(@Valid @RequestBody EmployeeRequestItem employee) {
        Department department = this.departmentRepository
                .findById(employee.getDepartment_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No valid department"));
        Employee actualEmployee = new Employee(employee.getFirstName(), employee.getLastName(), department);

        return new ResponseEntity<>(this.employeeRepository.save(actualEmployee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateOne(@PathVariable int id, @Valid @RequestBody EmployeeRequestItem employee) {
        Employee actualEmployee = this.employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No valid id"));

        Department department = this.departmentRepository
                .findById(employee.getDepartment_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No valid department"));

        actualEmployee.setDepartment(department);
        actualEmployee.setFirstName(employee.getFirstName());
        actualEmployee.setLastName(employee.getLastName());

        return new ResponseEntity<>(this.employeeRepository.save(actualEmployee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteOne(@PathVariable int id) {
        Employee employee = this.employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found on id"));

        this.employeeRepository.delete(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
