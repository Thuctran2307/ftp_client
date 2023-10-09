package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.web.client.Client;
import com.example.web.model.User;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WebController {


    @GetMapping("/")
    public String index(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        System.out.println("Địa chỉ IP của máy khách: " + clientIp);
        return "index";
    }

    @GetMapping("/login")
    public String login( Model model ) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        try {
            Client client = new Client("localhost", 2023, user.getUsername(), user.getPassword());
            if (client.connect()){
                return "redirect:/home";
            }
            return "redirect:/login";
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "redirect:/login";
    }

    @GetMapping("/SignIn")
    public String SignIn() {
        return "SignIn";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/sendfile")
    public String upload() {
        return "sendfile";
    }
}
