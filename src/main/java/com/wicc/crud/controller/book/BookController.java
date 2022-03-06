package com.wicc.crud.controller.book;


import com.wicc.crud.Dto.AuthorDto;
import com.wicc.crud.Dto.BookDto;
import com.wicc.crud.Dto.CategoryDto;
import com.wicc.crud.service.AuthorService;
import com.wicc.crud.service.BookService;
import com.wicc.crud.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("/book")
    public String openBookPage(Model model){
        if(model.getAttribute("bookDto") == null)
          model.addAttribute("bookDto", new BookDto());
        List<AuthorDto> authorDtoList = authorService.findAll();
        List<CategoryDto> categoryDtoList = categoryService.findAll();
        model.addAttribute("authorDto",authorDtoList);
        model.addAttribute("categoryDto",categoryDtoList);
        model.addAttribute("bookDtoList",bookService.findAll());
      return "/book/book";
    }

    @PostMapping("/creating")
    public String creatingMember(@ModelAttribute BookDto bookDto,
                               RedirectAttributes redirectAttributes) throws IOException {
        bookDto = bookService.save(bookDto);
        if(bookDto != null){
            redirectAttributes.addFlashAttribute("message","Book created successfully");
        }
        else {
            redirectAttributes.addFlashAttribute("message","Book not created");
        }
        return "redirect:/book";
    }

    @GetMapping("/find-id/{id}")
    public String findById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes ) throws IOException {
        BookDto bookDto = bookService.findById(id);
        if(bookDto != null)
            redirectAttributes.addFlashAttribute("bookDto",bookDto);
        return  "redirect:/book";
    }

    @GetMapping("/delete-id/{id}")
    public String deleteBookById(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes)
    {
        String message ="Book Deleted Successfully!!!!";
        bookService.deleteById(id);
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/book";
    }
}
