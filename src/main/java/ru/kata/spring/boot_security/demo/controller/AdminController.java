package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.UserDao.UserDao;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserModel;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping()
    public String ListUser(Model model) {
        model.addAttribute("admin", userService.listUsers());
        return "ListUser";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", userService.showUserById(id));
        return "showById";
    }

    @GetMapping("/createNew")
    public String newUser(@ModelAttribute("person") UserModel userModel, Role role) {
        return "newUser";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("person") @Valid UserModel userModel,
                                BindingResult bindingResult, @RequestParam("listRoles") ArrayList<Long> roles) {
        if (bindingResult.hasErrors())
            return "newUser";
        userService.add(userModel, userService.findRoles(roles));
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String ShowById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", userService.showUserById(id));
        return "Update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid UserModel userModel,
                         Model model, BindingResult bindingResult, @PathVariable("id") long id,
                         @RequestParam("listRoles1") ArrayList<Long> roles) {
        if (bindingResult.hasErrors())
            return "Update";
        model.addAttribute("roles", userService.findRoles(roles));
        userService.update(userModel, userService.findRoles(roles));
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
