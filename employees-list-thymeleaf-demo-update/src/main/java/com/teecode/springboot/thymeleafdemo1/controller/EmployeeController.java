package com.teecode.springboot.thymeleafdemo1.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teecode.springboot.thymeleafdemo1.entity.Employee;
import com.teecode.springboot.thymeleafdemo1.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmployeeService) {
		
		employeeService=theEmployeeService;
		
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		//get employee from the service
		Employee theEmployee = employeeService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
            theModel.addAttribute("employee", theEmployee);
		
		// send over to our form
		
		return "employees/employee-form";
	}
	
	// add mapping for "/list"
	
	@GetMapping("/list")
	public String listEmployees(Model theModel){
		
		// get Employees from the database
		List<Employee>theEmployees = employeeService.findAll();
		
		
		// add to the Spring model
		theModel.addAttribute("employees", theEmployees);
		
		return "/employees/list-employees";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
		employeeService.save(theEmployee);
		return "redirect:/employees/list";
		
	}
	}

