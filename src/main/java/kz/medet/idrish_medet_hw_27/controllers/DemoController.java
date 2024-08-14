package kz.medet.idrish_medet_hw_27.controllers;

import kz.medet.idrish_medet_hw_27.entities.User;
import kz.medet.idrish_medet_hw_27.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping("/home")
    public String homePage(){
        return "home page";
    }

    @GetMapping("/unsecured")
    public String unsecuredPage(){
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authPage(){
        return "auth page";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/user_info")
    public String userInfo(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException());
        return "Auth Info: " + user.getUsername() + " : " + user.getEmail();
    }
}
