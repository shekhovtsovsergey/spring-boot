package com.example.springboot.controller;


import com.example.springboot.entity.Cart;
import com.example.springboot.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {


    private final CartService cartService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("carts", cartService.findAll());
        return "cart-list";
    }

    @GetMapping("/{productId}")
    public String info(Model model, @PathVariable(name = "cartId") Long id) {
        Cart cart;
        if (id != null) {
            cart = cartService.findById(id);
        } else {
            return "redirect:/cart/all";
        }
        model.addAttribute("product", cart);
        return "product-info";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Cart cart;
        if (id != null) {
            cart = cartService.findById(id);
        } else {
            cart = new Cart();
        }
        model.addAttribute("product", cart);
        return "product-form";
    }

    @PostMapping
    public String saveCart(Cart cart) {
        cartService.save(cart);
        return "redirect:/cart/all";
    }


    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id) {
        cartService.deleteById(id);
        return "redirect:/cart/all";
    }



    @GetMapping("/addById")
    public String addById(@RequestParam(name = "id") Long id) {
        cartService.addById(id);
        return "redirect:/cart/all";
    }

}
