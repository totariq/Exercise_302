package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
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
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listToDo(Model model){
        model.addAttribute("todoitems", toDoRepository.findAll());
        return "list";
    }


    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("todoitem", new ToDoItem());
        return "todoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid ToDoItem todoitem, BindingResult result){
        if(result.hasErrors()){
            return "todoform";
        }

        toDoRepository.save(todoitem);
        return "redirect:/";
    }
}
