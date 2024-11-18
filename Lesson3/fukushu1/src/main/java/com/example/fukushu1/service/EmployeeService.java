package com.example.fukushu1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.fukushu1.model.Employee;
import com.example.fukushu1.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public Optional<Employee> findEmployeeById(Long id){
        return employeeRepository.findById(id);
    }
    public void deleteEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

    public List<Employee> findByEmployeeName(String name) {
        return employeeRepository.findActiveEmployee(name);
    // return employeeRepository.findAll()
    //         .stream()
    //         .filter(e -> e.getCompany() != null && e.getCompany().getCompanyName().equalsIgnoreCase(companyName))
    //         .collect(Collectors.toList());
    }
}
