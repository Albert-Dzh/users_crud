package com.baeldung.crud.controllers;

import javax.validation.Valid;

import com.baeldung.crud.entities.UserItem;
import com.baeldung.crud.entities.wrapper.ItemTemplateWrapper;
import com.baeldung.crud.repositories.ItemTemplateRepository;
import com.baeldung.crud.repositories.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.baeldung.crud.entities.User;
import com.baeldung.crud.repositories.UserRepository;

import java.util.List;

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
    public String showUserList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(name = "uppg", required = false, defaultValue = "9") int usersPerPage,
                               Model model) {
        int start = (page - 1) * usersPerPage;

        List<User> allUsers = userRepository.findAllByOrderById();
        List<User> subList = allUsers.subList(start, Math.min(allUsers.size(), start + usersPerPage));

        model.addAttribute("curPage", page);
        model.addAttribute("totalPages", Math.ceil(allUsers.size() / (double) usersPerPage));
        model.addAttribute("users", subList);
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
    public String showUpdateForm(@RequestParam("id") int id, Model model) {
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
        model.addAttribute("userItems", userItemRepository.findAllByUserId(id));
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
    public String editItem(@RequestParam("owner") int userId, @RequestParam("itemId") int itemId, Model model) {
        UserItem item = userItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid item id " + itemId));
        model.addAttribute("userId", userId);
        model.addAttribute("item", item);

        return "update-item";
    }

    @PostMapping("/update/items")
    public String updateItem(@RequestParam("owner") int userId, @RequestParam("itemId") int itemId, @Valid UserItem item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            item.setId(itemId);
            return "update-item";
        }

        userItemRepository.save(item);

        return "redirect:/items?owner=" + userId;
    }

    @GetMapping("/delete/items")
    public String delItemFromUser(@RequestParam("owner") int userId, @RequestParam("itemId") int itemId, Model model) {
        UserItem item = userItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid item Id " + itemId));
        userItemRepository.delete(item);

        return "redirect:/items?owner=" + userId;
    }
}
