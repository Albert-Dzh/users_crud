package com.baeldung.crud.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.baeldung.crud.entities.User;
import com.baeldung.crud.repositories.UserRepository;


@Controller
@SuppressWarnings("UnusedParameters")
public class UserController {
    
    private final UserRepository userRepository;


    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = {"/", "/index"})
    public String mainRedirect() {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUserList(@RequestParam(name = "search", required = false) String login,
                               @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(name = "uppg", required = false, defaultValue = "9") int usersPerPage,
                               Model model) {

        PageRequest request = PageRequest.of(page - 1, usersPerPage);
        Page<User> users = login == null ?
                userRepository.findAllByOrderById(request) :
                userRepository.findAllByLoginContains(login, request);

        model.addAttribute("curPage", page);
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/adduser")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(user);

        return "redirect:/users";
    }

    @GetMapping("/edit/users")
    public String showUserUpdateForm(@RequestParam("id") int id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);

        return "update-user";
    }

    @PostMapping("/update/users")
    public String updateUser(@RequestParam("id") int id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);

        return "redirect:/users";
    }

    @GetMapping("/delete/users")
    public String deleteUser(@RequestParam("id") int id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);

        return "redirect:/users";
    }
}
