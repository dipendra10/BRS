package com.wicc.crud.controller.category;

import com.wicc.crud.Dto.CategoryDto;
import com.wicc.crud.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // to open category page
    @GetMapping("/category")
    public String openCategory(Model model){

        if(model.getAttribute("categoryDto") == null)
            model.addAttribute("categoryDto",new CategoryDto());
        model.addAttribute("categoryDtoList",categoryService.findAll());
        return "/category/category";
    }

    @PostMapping("/created")
    public String createdCategory( @ModelAttribute CategoryDto categoryDto, RedirectAttributes
                                  redirectAttributes) {
        //call service to save data
        categoryDto = categoryService.saveCategory(categoryDto);
        if(categoryDto != null){
            redirectAttributes.addFlashAttribute("message","Category created successfully");
        }
        else {
            redirectAttributes.addFlashAttribute("message","Category not created");
        }
        return "redirect:/category";
    }

    @GetMapping("/findby/{id}")
    public String findById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes )
    {
        CategoryDto categoryDto = categoryService.findById(id);
        if(categoryDto != null)
            redirectAttributes.addFlashAttribute("categoryDto",categoryDto);
        return  "redirect:/category";
    }


    @GetMapping("/deleteby/{id}")
    public String deleteById(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes)
    {
        String message ="Category Deleted Successfully!!!!";
        categoryService.deleteCategoryById(id);
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/category";
    }
}
