package com.icia.member.controller;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor // final 키워드가 붙은 필드만으로 생성자를 만들어 줌


public class MemberController  {
    private final MemberService ms;

    @GetMapping("save")
    public String saveForm(Model model){
        model.addAttribute("member", new MemberSaveDTO());
        return "member/save";
    }

    @PostMapping("save")
    public String save(@Validated @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult bindingResult){
        System.out.println("memberSaveDTO = " + memberSaveDTO); //soutp
        System.out.println("MemberController.save"); //soutm

        if(bindingResult.hasErrors()){
            return "member/save";
        }

        ms.save(memberSaveDTO);
        return "redirect:/member/login";
    }

    @GetMapping("login")
    public String loginForm(Model model){
        model.addAttribute("login", new MemberLoginDTO());
        return "member/login";
    }

    @PostMapping("login")
    public String login(@Validated @ModelAttribute("login") MemberLoginDTO memberLoginDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "member/login";
        }
        return "redirect:/member/login";
    }

    // 상세조회
    // /member/2, /member/15 => /member/{memberId}
    @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model) {
        System.out.println("memberId = " + memberId);
        MemberDetailDTO member=ms.findById(memberId);
        model.addAttribute("member", member);
        return "member/detail";
    }
}
