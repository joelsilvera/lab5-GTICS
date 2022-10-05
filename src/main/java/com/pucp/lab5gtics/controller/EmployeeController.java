package com.pucp.lab5gtics.controller;

import com.pucp.lab5gtics.entity.Employee;
import com.pucp.lab5gtics.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping({"empleado/info"})
    public String infoempleado(Model model, @PathVariable(name = "id") Integer id){
        Optional<Employee> optEmp = employeeRepository.findById(id);

        //List<Employee> listaempleados = employeeRepository.findAll(Sort.by("first_name"));

        //model.addAttribute("listaemployee", listaempleados);

        return "employee/information";
    }


    @GetMapping({"empleado/lista", "empleado"})
    public String listEmployee(Model model, @RequestParam(name = "search",required = false) String search, @RequestParam(name = "order", required = false) Integer order, RedirectAttributes attributes){


        return "XXXXXX";
    }


    //Buscar Empleado
    public String searchEmployee(Model model, @RequestParam(name = "search",required = false) String search, @RequestParam(name = "order", required = false) Integer order, RedirectAttributes attributes){

        return "XXXXXX";
    }


    //Editar Empleado
    //@...Mapping("")
    public String informEmployee(  ) {
        //        COMPLETAR
        return "XXXXXX";
    }

    //Guardar Empleado
    @PostMapping("empleado/guardar")
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/empleado/lista";
    }

    @GetMapping("empleado/nuevo")
    public String newEmployee() {
        return "employee/newForm";
    }
}
