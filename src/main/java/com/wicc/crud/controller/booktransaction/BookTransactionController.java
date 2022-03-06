package com.wicc.crud.controller.booktransaction;


import com.wicc.crud.Dto.*;
import com.wicc.crud.enums.RentStatus;
import com.wicc.crud.service.BookService;
import com.wicc.crud.service.BookTransactionService;
import com.wicc.crud.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookTransactionController {

    private final BookTransactionService bookTransactionService;
    private final BookService bookService;
    private final MemberService memberService;

    public BookTransactionController(BookTransactionService bookTransactionService,
                                     BookService bookService, MemberService memberService) {
        this.bookTransactionService = bookTransactionService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping("/transaction")
    public String openTransaction(Model model){
        if(model.getAttribute("bookTransactionDto") == null)
          model.addAttribute("bookTransactionDto",new BookTransactionDto());
        BookTransactionDto bookTransactionDto = new BookTransactionDto();
        bookTransactionDto.setRentTypes(RentStatus.RENT.name());
        bookTransactionDto.setRentTypes(RentStatus.RETURN.name());
        List<BookDto> bookDtoList = bookService.findAll();
        List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("bookDto",bookDtoList);
        model.addAttribute("memberDto",memberDtoList);
        model.addAttribute("bookTransactionDtoList",bookTransactionService.findAll());
        return "transaction/transaction";
    }

    @PostMapping("/saved")
    public String savingBook(@ModelAttribute BookTransactionDto bookTransactionDto,
                                 RedirectAttributes redirectAttributes) throws IOException {
        bookTransactionDto = bookTransactionService.save(bookTransactionDto);
        if(bookTransactionDto != null){
            redirectAttributes.addFlashAttribute("message","Book Transaction created successfully");
        }
        else {
            redirectAttributes.addFlashAttribute("message","Book Transaction not created");
        }
        return "redirect:/transaction";
    }

    @GetMapping("/by-id/{id}")
    public String findById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes ) throws IOException {
        BookTransactionDto bookTransactionDto= bookTransactionService.findById(id);
        if(bookTransactionDto != null)
            redirectAttributes.addFlashAttribute("bookTransactionDto",bookTransactionDto);
        return  "redirect:/transaction";
    }

    @GetMapping("/d-id/{id}")
    public String deleteBookById(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes)
    {
        String message ="Book Transaction Deleted Successfully!!!!";
        bookTransactionService.deleteById(id);
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/transaction";
    }
}
