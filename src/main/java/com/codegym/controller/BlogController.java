package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/")
    public ModelAndView showAllBlog(){
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Blog> blogList = blogService.findAll();
        modelAndView.addObject("blogs", blogList);
        return modelAndView;
    }
    @RequestMapping("/listBlog")
    public ModelAndView allblog(){
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Blog> blogList = blogService.findAll();
        modelAndView.addObject("blogs", blogList);
        return modelAndView;
    }

    @GetMapping("/createBlog")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/createBlog");

        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }
    @PostMapping("/createBlog")
    public ModelAndView createBlog(@ModelAttribute Blog blog){
        ModelAndView modelAndView = new ModelAndView("/createBlog");
        blogService.save(blog);
        modelAndView.addObject("message", "Create Blog Success");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @PostMapping("/editBlog")
    public ModelAndView showFormEdit(@ModelAttribute Blog blog){
        ModelAndView modelAndView = new ModelAndView("/edit");
        blogService.save(blog);
        modelAndView.addObject("message", "Edit success");
        return modelAndView;
    }
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/view");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @PostMapping("/deleteBlog")
    public ModelAndView deleteBlog(@ModelAttribute Blog blog){
        ModelAndView modelAndView = new ModelAndView("/delete");
        blogService.delete(blog);
        modelAndView.addObject("message", "Delete Success");
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView findBlock(@RequestParam("searchBlog") String blog){
        ModelAndView modelAndView = new ModelAndView("/list");
        Blog blogFind = blogService.findByName(blog);
        modelAndView.addObject("blogs", blogFind);
        return modelAndView;
    }
}
