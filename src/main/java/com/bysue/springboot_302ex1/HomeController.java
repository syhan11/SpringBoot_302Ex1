package com.bysue.springboot_302ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String listTodos(Model model) {
        model.addAttribute("todo", todoRepository.findAll());
        return "showtodos";
    }


    @GetMapping("/add")
    public String todoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "addtodo";
    }

    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "addtodo";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }
}
