package com.cdplayer.cdplayer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal,Model model){
        if (principal!=null) {
            String username=principal.getName();
            model.addAttribute("username",username);
        }

        return "index";
    }

    @GetMapping("/diary")
    public String diary(){
        return "diary";
    }


}
