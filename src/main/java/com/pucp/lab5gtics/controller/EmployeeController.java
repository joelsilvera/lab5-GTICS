package com.pucp.lab5gtics.controller;

import com.pucp.lab5gtics.entity.Department;
import com.pucp.lab5gtics.entity.Employee;
import com.pucp.lab5gtics.entity.Job;
import com.pucp.lab5gtics.repository.DepartmentRepository;
import com.pucp.lab5gtics.repository.EmployeeRepository;
import com.pucp.lab5gtics.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Access;
import java.util.Optional;

@RequestMapping("/empleado")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    JobRepository jobRepository;

    @GetMapping(value = {"", "/", "lista"})
    public String listEmployee(Model model){
        model.addAttribute("listaEmpleados", employeeRepository.findAll());
        return "employee/list";
    }

    @GetMapping({"empleado/info/"})
    public String infoempleado(Model model, @PathVariable(name = "id") Integer id){
        Optional<Employee> optEmp = employeeRepository.findById(id);
        Optional<Department> optdep = departmentRepository.findById(optEmp.get().getDepartment().getId());
        Optional<Job> optjob = jobRepository.findById(optEmp.get().getJob().getId());

        if(optEmp.isPresent()) {
            Employee employee = optEmp.get();
            model.addAttribute("employee", employee);
            model.addAttribute("department", optdep.get());
            model.addAttribute("job",optjob.get());
            return "employee/information";
        }else{
            return "redirect:/empleado/lista";
        }
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
    @PostMapping("/guardar")
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/empleado/lista";
    }

    @GetMapping("/nuevo")
    public String newEmployee() {
        return "employee/newForm";
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
