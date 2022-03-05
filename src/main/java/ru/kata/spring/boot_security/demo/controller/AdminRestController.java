package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.UserModel;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;


    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> showUsers() {
        final List<UserModel> users = userService.getAllUser();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> showUser(@PathVariable("id") long id){
        final UserModel userModel = userService.findUserById(id);

        return userModel != null
                ? new ResponseEntity<>(userModel,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel){

        userService.addUser(userModel,userModel.getRoles());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel){

        userService.addUser(userModel,userModel.getRoles());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteById(id);
    }

    @GetMapping("/api/email")
    public ResponseEntity<UserModel> showUser (Principal principal){
        UserModel userModel = userService.showUserByEmail(principal.getName());
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

}
