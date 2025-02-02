package co.hrd.employeerestapi.entity;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    private List<Employee> employees;

    public List<Employee> getEmployeeList() {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
