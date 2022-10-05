package com.pucp.lab5gtics.controller;


import com.pucp.lab5gtics.entity.Employee;
import com.pucp.lab5gtics.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping("ventanaLogin")
    public String ventanaLogin(){
        return "Login";
    }
    @GetMapping("redireccionarPorRol")
    public String redireccionarPorRol(Authentication authentication, HttpSession session){
        String rol ="";
        List<GrantedAuthority> authorities=(List<GrantedAuthority>) authentication.getAuthorities();
        for(GrantedAuthority grantedAuthority : authorities){
            System.out.println(grantedAuthority);
            rol=grantedAuthority.getAuthority();
        }

        String username = authentication.getName();//obtengo por correo
        Employee usuariosRegistrado = employeeRepository.findByEmail(username);//busco por email
        session.setAttribute("usuario",usuariosRegistrado);

        switch (rol){
            case "EMPLOYEE" -> {return "redirect:/empleado/lista";}

            default -> {return"redirect:/empleado/lista";}
        }
    }

}
