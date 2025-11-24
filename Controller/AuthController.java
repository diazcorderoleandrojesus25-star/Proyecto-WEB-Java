package com.Jobxpress.Jobxpress.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Jobxpress.Jobxpress.Entity.Usuario;
import com.Jobxpress.Jobxpress.Repository.ServicioRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {

        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", List.of("cliente", "prestador"));
        model.addAttribute("servicios", servicioRepository.findAll());

        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @ModelAttribute Usuario usuario,
            @RequestParam(required = false) List<Integer> serviciosSeleccionados) {

        // guardar usuario...

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // <-- AQUÍ SE CORRIGE EL PROBLEMA
    }
}
