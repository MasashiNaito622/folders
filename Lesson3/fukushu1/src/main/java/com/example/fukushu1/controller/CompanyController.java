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
@RequestMapping("/companys")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    //
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String listCompany(Model model){
        List<Company>companys = companyService.findAllCompany();
        model.addAttribute("companys1", companys);
        return "companys/list";//ビュー(companys.html)の名前を返す
    }

    @GetMapping("/add")
    public String addCompanyForm(Model model){
        model.addAttribute("company", new Company());
        //return 前にcompanyデータの全件取得を実施------ここから-----
        model.addAttribute("employees", employeeService.findAllEmployees());
        //-----ここまで-----
        return "companys/add";
    }
    @PostMapping("/add")
    public String addCompany(Company company){
        companyService.saveCompany(company);
        return "redirect:/companys/list";
    }

    @GetMapping("/edit/{id}")
    public String editCompanyForm(@PathVariable("id") Long id, Model model) {
        Company company = companyService.findCompanyById(id)
        .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + id));
        model.addAttribute("company", company);
        return "companys/edit";
    }
    @PostMapping("/edit")
    public String editCompany(Company company) {
        companyService.updateCompany(company);
        return "redirect:/companys/list";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompanyById(id);
        return "redirect:/companys/list";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "companyName", required = false) String companyName,
            Model model) {
        List<Company> companys = companyService.findByCompanyName(companyName);
        model.addAttribute("companys", companys);
        return "/companys/search";
    }

}
