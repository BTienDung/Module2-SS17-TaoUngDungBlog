package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Controller
public class CategogyController {
    @Autowired
    public CategoryService categoryService;
    @RequestMapping("/list-category")
    public ModelAndView showList(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/category/list");
        Page<Category> category = categoryService.findAll(pageable);
        modelAndView.addObject("categorys", category);
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("categorys", new Category());
        return modelAndView;
    }
    @PostMapping("/create-category")
    public ModelAndView createCategory(@ModelAttribute("categorys") Category category){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        categoryService.save(category);
        modelAndView.addObject("messsage", "Them thanh cong");
        return modelAndView;
    }

    @GetMapping("/edit-category/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        Category category = categoryService.findById(id);
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @PostMapping("/edit-category")
    public ModelAndView editCategory(@ModelAttribute Category category){
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        categoryService.save(category);
        modelAndView.addObject("message", "Edit Success");
        return modelAndView;
    }
}
