package org.example.firstprojectfront.controllers;

import org.example.firstprojectfront.entities.CartItem;
import org.example.firstprojectfront.services.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'USER')")
    public List<CartItem> getMyCart(Principal principal) {
        return cartService.getCartItems(principal.getName());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'USER')")
    public CartItem addToCart(Principal principal, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addToCart(principal.getName(), productId, quantity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'USER')")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeCartItem(id);
    }
}
