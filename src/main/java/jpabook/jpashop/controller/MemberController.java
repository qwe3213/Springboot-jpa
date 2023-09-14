package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.Memberservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

     private final Memberservice memberservice;
     @GetMapping("/members/new")
     public String createForm(Model model){
         model.addAttribute("memberForm", new MemberForm());
         return "members/createMemberForm";
     }

     @PostMapping("/members/new")
     public String create(@Valid MemberForm form){

         Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

         Member member = new Member();
         member.setUsername(form.getName());
         member.setAddress(address);

         memberservice.join(member);

         return "redirect:/";
     }

}