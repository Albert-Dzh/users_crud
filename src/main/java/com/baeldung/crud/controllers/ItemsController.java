package com.baeldung.crud.controllers;

import com.baeldung.crud.entities.UserItem;
import com.baeldung.crud.entities.wrapper.ItemTemplateWrapper;
import com.baeldung.crud.exceptions.ItemNotFoundException;
import com.baeldung.crud.repositories.ItemTemplateRepository;
import com.baeldung.crud.repositories.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@SuppressWarnings("UnusedParameters")
public class ItemsController {

    private final UserItemRepository userItemRepository;
    private final ItemTemplateRepository itemTemplateRepository;

    @Autowired
    public ItemsController(UserItemRepository userItemRepository, ItemTemplateRepository itemTemplateRepository) {
        this.userItemRepository = userItemRepository;
        this.itemTemplateRepository = itemTemplateRepository;
    }

    @GetMapping("/items")
    public String showItemList(@RequestParam("owner") int id,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "ippg", required = false, defaultValue = "7") int itemsPerPage,
                               Model model) {

        Page<UserItem> items = userItemRepository.findAllByUserIdOrderById(id, PageRequest.of(page - 1, itemsPerPage));

        model.addAttribute("userItems",             items);
        model.addAttribute("curPage",               page);
        model.addAttribute("totalPages",            items.getTotalPages());
        model.addAttribute("items",                 itemTemplateRepository.findAllByOrderById());
        model.addAttribute("itemTemplateWrapper",   new ItemTemplateWrapper());
        model.addAttribute("userId",                id);

        return "items";
    }

    @PostMapping("/additem")
    public String addItemToUser(@RequestParam("owner") int userId,
                                @Valid ItemTemplateWrapper wrapper,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "items";
        }

        userItemRepository.save(new UserItem(wrapper.getItem(), userId));
        return "redirect:/items?owner=" + userId;
    }

    @GetMapping("/edit/items")
    public String showItemUpdateForm(@RequestParam("itemId") int itemId, Model model) {
        UserItem item = userItemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
        model.addAttribute("item", item);

        return "update-item";
    }

    @PostMapping("/update/items")
    public String updateItem(@RequestParam("itemId") int itemId,
                             @Valid UserItem dummy,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            dummy.setId(itemId);
            return "update-item";
        }

        UserItem item = userItemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));

        userItemRepository.save(item.mergedWith(dummy));

        return "redirect:/items?owner=" + item.getUserId();
    }

    @GetMapping("/delete/items")
    public String deleteItem(@RequestParam("itemId") int itemId, Model model) {
        UserItem item = userItemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
        userItemRepository.delete(item);

        return "redirect:/items?owner=" + item.getUserId();
    }
}
