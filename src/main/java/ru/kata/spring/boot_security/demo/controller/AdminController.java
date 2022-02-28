package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.UserService.RoleService;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.UserModel;
import java.security.Principal;
import java.util.ArrayList;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String ListUser(Model model, Principal principal,@ModelAttribute("user") UserModel userModel) {
        model.addAttribute("getUsers", userService.getAllUser());
        model.addAttribute("getRoles", roleService.getAllRoles());
        model.addAttribute("userByEmail", userService.showUserByEmail(principal.getName()));
        return "ListUser";
    }


    @PostMapping()
    public String createNewUser(@ModelAttribute("user") UserModel userModel,
                                @RequestParam("checked") ArrayList<Long> roles) {
        String email = userModel.getEmail();
        for (UserModel person : userService.getAllUser()){
            if (person.getEmail().equals(email)){
            return "ErrUser";
            }
        }
        userService.addUser(userModel,roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") UserModel userModel,
                          @RequestParam("checked") ArrayList<Long> roles) {

        userService.updateUser(userModel,roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
