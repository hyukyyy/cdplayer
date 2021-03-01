package com.cdplayer.cdplayer.controller;

import com.cdplayer.cdplayer.model.Blog;
import com.cdplayer.cdplayer.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/list")
    public String list(Model model){

        List<Blog> boards = blogRepository.findAll();
        model.addAttribute("boards",boards);

        return "blog";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {

        if (id==null) {
            model.addAttribute("blog", new Blog());
        } else {
            Blog blog = blogRepository.findById(id).orElse(null);
            model.addAttribute("blog", blog);
        }

        return "form";
    }

    @PostMapping("/form")
    public RedirectView greetingSubmit(@Valid Blog blog, Principal principal, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new RedirectView("/blog/form");
        } else {

            java.util.Date nowUtil = new java.util.Date();
            Date now = new Date(nowUtil.getTime());
            String writer=principal.getName();

            blog.setRegDate(now);
            blog.setWriter(writer);
            blogRepository.save(blog);

            return new RedirectView("/blog/list");

        }

    }

    @DeleteMapping("/delete")
    public RedirectView deleteBlog(@RequestParam("id") long id){

        blogRepository.deleteById(id);

        return new RedirectView("/blog/list");
    }


}
