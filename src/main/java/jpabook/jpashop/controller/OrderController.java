package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.Memberservice;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

   private final OrderService orderService;
   private final Memberservice memberservice;
   private final ItemService itemService;

   @GetMapping("/order")
    public String createForm(Model model){

       List<Member> members = memberservice.findMembers();
       List<Item> items = itemService.findItems();

       model.addAttribute("members", members);
       model.addAttribute("items",items);

       return "order/orderForm";
   }
   @PostMapping("/order")
   public String order(@RequestParam("memberId") Long memberId,
                       @RequestParam("itemId") Long itemid,
                       @RequestParam("count") int count){
      orderService.order(memberId, itemid, count);
      return "redirect:/orders";
   }
}
