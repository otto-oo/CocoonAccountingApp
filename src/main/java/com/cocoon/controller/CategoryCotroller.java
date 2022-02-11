package com.cocoon.controller;

import com.cocoon.dto.CategoryDTO;
import com.cocoon.dto.CompanyDTO;
import com.cocoon.exception.CocoonException;
import com.cocoon.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryCotroller {

    CategoryService categoryService;

    public CategoryCotroller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/create")
    public String getCreateCategoryPage(Model model){
        model.addAttribute("category", new CategoryDTO());
        return "category/category-add";
    }

    @PostMapping("/create")
    public String createCategory(CategoryDTO categoryDTO) throws CocoonException {
        categoryService.save(categoryDTO);
        return "redirect:/category/list";
    }

    @GetMapping("/list")
    public String getCompanies(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/category-list";
    }

    @GetMapping({"/edit/{id}","/"})
    public String editCategory(@PathVariable("id") Long id, Model model) throws CocoonException {
        model.addAttribute("category",categoryService.getById(id));
        return "category/category-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable("id") String id,CategoryDTO categoryDTO) throws CocoonException {
        categoryService.update(categoryDTO);
        return "redirect:/category/list";
    }

}
