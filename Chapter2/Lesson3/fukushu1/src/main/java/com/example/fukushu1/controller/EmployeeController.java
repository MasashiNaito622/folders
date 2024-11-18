package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fukushu1.service.CompanyService;
import com.example.fukushu1.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

import com.example.fukushu1.model.Company;
import com.example.fukushu1.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    //
    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public String listEmployee(Model model){
        List<Employee>employees = employeeService.findAllEmployees();
        model.addAttribute("employees1", employees);
        return "employees/list";//ビュー(employees.html)の名前を返す
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model){
        model.addAttribute("employee", new Employee());
        //return 前にemployeeデータの全件取得を実施------ここから-----
        model.addAttribute("companys", companyService.findAllCompany());
        //-----ここまで-----
        return "employees/add";
    }
    @PostMapping("/add")
    public String addEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        List<Company>companys = companyService.findAllCompany();
        model.addAttribute("employee", employee);
        model.addAttribute("companys", companys);
        return "employees/edit";
    }
    @PostMapping("/edit")
    public String editEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees/list";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees/list";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "employeeName", required = false) String employeeName,
            Model model) {
        List<Employee> employees = employeeService.findByEmployeeName(employeeName);
        model.addAttribute("employees", employees);
        return "/employees/search";
    }

}