package com.pucp.lab5gtics.controller;

import com.pucp.lab5gtics.entity.Employee;
import com.pucp.lab5gtics.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Access;
import java.util.Optional;

@RequestMapping("/empleado")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping(value = {"", "/", "lista"})
    public String listEmployee(Model model){
        model.addAttribute("listaEmpleados", employeeRepository.findAll());
        return "employee/list";
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
    //@...Mapping("")
    public String saveEmployee(  ) {
        //        COMPLETAR
        return "XXXXXX";
    }

    //Nuevo Empleado
    public String newEmployee(Model model) {
        //        COMPLETAR
        return "XXXXXX";
    }

    @GetMapping("/borrar")
    public String borrarEmpleado(@RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            employeeRepository.deleteById(id);
        }
        attr.addFlashAttribute("msg","empleado borrado exitosamente");
        return "redirect:/empleado";
    }
}
