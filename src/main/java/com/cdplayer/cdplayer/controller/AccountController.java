package com.cdplayer.cdplayer.controller;

import com.cdplayer.cdplayer.Dto.UserDto;
import com.cdplayer.cdplayer.model.User;
import com.cdplayer.cdplayer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;

@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLogin () {

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("user",new UserDto());

        return "register";
    }

    @PostMapping("/register")
    public RedirectView register(Model model, UserDto userDto){

        userService.signUp(userDto);

        return new RedirectView("/");
    }
}
