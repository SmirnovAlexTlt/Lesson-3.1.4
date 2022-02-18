package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.UserService.RoleService;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserModel;
import javax.validation.Valid;
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
        userService.add(userModel, roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @GetMapping("/{id}/Update")
    public String ShowById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", userService.showUserById(id));
        return "Update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid UserModel userModel,
                          BindingResult bindingResult, @PathVariable("id") long id,
                         @RequestParam("listRoles1") ArrayList<Long> roles) {
        if (bindingResult.hasErrors())
            return "Update";
        userService.update(userModel, roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
