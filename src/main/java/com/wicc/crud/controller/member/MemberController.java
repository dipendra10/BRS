package com.wicc.crud.controller.member;


import com.wicc.crud.Dto.MemberDto;
import com.wicc.crud.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member")
    public String openMemberPage(Model model)
    {
        if(model.getAttribute("memberDto") == null)
         model.addAttribute("memberDto",new MemberDto());
        model.addAttribute("memberList",memberService.findAll());
        return "/member/member";
    }

    @PostMapping("/create")
    public String createMember(@ModelAttribute MemberDto memberDto,
                               RedirectAttributes redirectAttributes) throws IOException {
       memberDto = memberService.save(memberDto);
       if(memberDto != null){
           redirectAttributes.addFlashAttribute("message","Member created successfully");
       }
       else {
           redirectAttributes.addFlashAttribute("message","Member not created");
       }
       return "redirect:/member";
    }

    @GetMapping("/view-by/{id}")
    public String viewById(@PathVariable("id") Integer id,Model model ) throws IOException {
        model.addAttribute("memberDto",memberService.viewById(id));
        return "member/memberview";
    }

    @GetMapping("/find-by-id/{id}")
    public String findById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes ) throws IOException {
        MemberDto memberDto = memberService.findById(id);
        if(memberDto != null)
         redirectAttributes.addFlashAttribute("memberDto",memberDto);
        return  "redirect:/member";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes)
    {
        String message ="Member Deleted Successfully!!!!";
        memberService.deleteById(id);
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/member";
    }
}
