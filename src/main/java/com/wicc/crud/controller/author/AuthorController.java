package com.wicc.crud.controller.author;


import com.wicc.crud.Dto.AuthorDto;
import com.wicc.crud.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // to open the author page
    @GetMapping("/author")
    public String openAuthorPage(Model model)
    {
        if(model.getAttribute("authorDto") == null)
          model.addAttribute("authorDto",new AuthorDto());
        model.addAttribute("authorDtoList",authorService.findAll());
        return "/author/author";
    }

    // to save data
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute AuthorDto authorDto,
                             RedirectAttributes redirectAttributes) {

        //call service to save data
        authorDto = authorService.saveAuthor(authorDto);
        if(authorDto != null){
            redirectAttributes.addFlashAttribute("message","Author created successfully");
        }
        else {
            redirectAttributes.addFlashAttribute("message","Author not created");
        }
        return "redirect:/author";
    }

    @GetMapping("/findbyid/{id}")
    public String findById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes )
    {
        AuthorDto authorDto = authorService.findById(id);
        if(authorDto != null)
            redirectAttributes.addFlashAttribute("authorDto",authorDto);
        return  "redirect:/author";
    }


    @GetMapping("/delete-by-id/{id}")
    public String deleteById(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes)
    {
        String message ="Author Deleted Successfully!!!!";
        authorService.deleteAuthorById(id);
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/author";
    }
}
