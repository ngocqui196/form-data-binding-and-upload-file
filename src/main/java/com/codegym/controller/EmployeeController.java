package com.codegym.controller;

import com.codegym.model.Employee;
import com.codegym.model.EmployeeForm;
import com.codegym.service.EmployeeServiceImpl;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private Environment environment;

    @GetMapping
    public String index(Model model) {
        List employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "infor";
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create","employee",new Employee());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addEmployee(@ModelAttribute("employee") EmployeeForm employee) throws IOException {
        int code = (int) Math.floor(((Math.random() * 99999999)));
        employee.setId(code);
        MultipartFile file = employee.getAvatar();
        String avatar = file.getOriginalFilename();
        String thu_muc_anh_goc = environment.getProperty("file_upload").toString();
        FileCopyUtils.copy(file.getBytes(), new File(thu_muc_anh_goc+avatar));
        Employee employee1 = new Employee(employee.getId(), employee.getName(), employee.getAddress(),avatar);
        employeeService.save(employee1);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject( "employee",new EmployeeForm() );

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable int id, @ModelAttribute Employee employee) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editCustomer(@ModelAttribute Employee employee) {
        employeeService.update(employee.getId(),employee);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message","Sửa thành công ${employee} !!!");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showViewDelete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        Employee employee = employeeService.findById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteCustomer(@ModelAttribute Employee employee) {
        employeeService.remove(employee.getId());
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
