package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categorys")
    public Page<Category> categories(Pageable pageable){
        return categoryService.findAll(pageable);
    }
    @GetMapping("/")
    public ModelAndView showAllBlog(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        Page<Blog> blogList = blogService.findAll(pageable);
        modelAndView.addObject("blogs", blogList);
        return modelAndView;
    }
    @GetMapping("/list-blog")
    public ModelAndView allblog(@PageableDefault(size = 5) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/blog/list");
            Page<Blog> blogs = blogService.findAll(pageable);
            modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");

        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }
    @PostMapping("/create-blog")
    public ModelAndView createBlog(@ModelAttribute Blog blog){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        blogService.save(blog);
        modelAndView.addObject("message", "Create Blog Success");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @PostMapping("/edit-blog")
    public ModelAndView showFormEdit(@ModelAttribute Blog blog){
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        blogService.save(blog);
        modelAndView.addObject("message", "Edit success");
        return modelAndView;
    }
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @PostMapping("/delete-blog")
    public ModelAndView deleteBlog(@ModelAttribute Blog blog){
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        blogService.remove(blog);
        modelAndView.addObject("message", "Delete Success");
        return modelAndView;
    }

    @PostMapping("/search-blog")
    public ModelAndView searchBlog(@ModelAttribute("categoryBlog") String category, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/blog/search");
        Category category1 = categoryService.findByCategory(category);
        Page<Blog> blogs = blogService.findAllByCategory(category1, pageable);
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
}
