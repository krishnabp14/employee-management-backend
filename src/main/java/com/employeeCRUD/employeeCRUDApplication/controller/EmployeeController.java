package com.employeeCRUD.employeeCRUDApplication.controller;

import com.employeeCRUD.employeeCRUDApplication.entity.Employee;
import com.employeeCRUD.employeeCRUDApplication.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH}, allowedHeaders = "*")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create-employee")
    private Employee postEmployee(@RequestBody Employee employee) {
        return employeeService.postEmployee(employee);
    }

    @GetMapping("/get-all-employees")
    private List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employee/{id}")
    private ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee with id " + id + "deleted successfuly", HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/employee/{id}")
    private ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);

        if(employee.isEmpty()) {
            return new ResponseEntity<>("Employee Not Found with this id " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PatchMapping("/employee/{id}")
    private ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> updatedEmployee = Optional.ofNullable(employeeService.updateEmployee(id, employee));

        if(updatedEmployee.isEmpty()) {
            return new ResponseEntity<>("Employee Not Updated", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

}
