package co.hrd.employeerestapi.controller;


import co.hrd.employeerestapi.entity.Employee;
import co.hrd.employeerestapi.entity.Employees;
import co.hrd.employeerestapi.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    // GET endpoint to fetch all employees
    @GetMapping
    public Employees getEmployees() {
        return employeeDao.getAllEmployees();
    }

    // POST endpoint to add employee
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        // Generate id for new employee
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);

        // Add employee to list
        employeeDao.addEmployee(employee);

        // Build location URI for the new employee
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
