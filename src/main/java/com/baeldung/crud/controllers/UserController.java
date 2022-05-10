package com.baeldung.crud.controllers;

import javax.validation.Valid;

import com.baeldung.crud.entities.UserItem;
import com.baeldung.crud.entities.wrapper.ItemTemplateWrapper;
import com.baeldung.crud.repositories.ItemTemplateRepository;
import com.baeldung.crud.repositories.UserItemRepository;
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
    private final UserItemRepository userItemRepository;
    private final ItemTemplateRepository itemTemplateRepository;

    @Autowired
    public UserController(UserRepository userRepository, UserItemRepository userItemRepository, ItemTemplateRepository itemTemplateRepository) {
        this.userRepository = userRepository;
        this.userItemRepository = userItemRepository;
        this.itemTemplateRepository = itemTemplateRepository;
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

        Page<User> users = login != null ?
                userRepository.findAllByLogin(login, PageRequest.of(page - 1, usersPerPage)) :
                userRepository.findAllByOrderById(PageRequest.of(page - 1, usersPerPage));

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

    @GetMapping("/items")
    public String showItemList(@RequestParam("owner") int id,  Model model) {
        model.addAttribute("userItems", userItemRepository.findAllByUserIdOrderById(id));
        model.addAttribute("items", itemTemplateRepository.findAllByOrderById());
        model.addAttribute("itemTemplateWrapper", new ItemTemplateWrapper());
        model.addAttribute("userId", id);
        return "items";
    }

    @PostMapping("/additem")
    public String addItemToUser(@RequestParam("owner") int userId, @Valid ItemTemplateWrapper wrapper, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "items";
        }

        userItemRepository.save(new UserItem(wrapper.getItem(), userId));
        return "redirect:/items?owner=" + userId;
    }

    @GetMapping("/edit/items")
    public String showItemUpdateForm(@RequestParam("itemId") int itemId, Model model) {
        UserItem item = userItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid item id " + itemId));
        model.addAttribute("item", item);

        return "update-item";
    }

    @PostMapping("/update/items")
    public String updateItem(@RequestParam("itemId") int itemId, @Valid UserItem dummy, BindingResult result, Model model) {
        if (result.hasErrors()) {
            dummy.setId(itemId);
            return "update-item";
        }

        UserItem item = userItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid item id " + itemId));

        userItemRepository.save(item.mergedWith(dummy));

        return "redirect:/items?owner=" + item.getUserId();
    }

    @GetMapping("/delete/items")
    public String deleteItem(@RequestParam("itemId") int itemId, Model model) {
        UserItem item = userItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid item Id " + itemId));
        userItemRepository.delete(item);

        return "redirect:/items?owner=" + item.getUserId();
    }
}
