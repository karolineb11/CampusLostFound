package com.campus.lostfound.controller;

import com.campus.lostfound.model.FoundItem;
import com.campus.lostfound.model.ItemStatus;
import com.campus.lostfound.model.ItemType;
import com.campus.lostfound.service.FoundItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {

    private final FoundItemService service;

    public ItemController(FoundItemService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String dashboard(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("items", service.searchItems(keyword));
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("totalCount", service.countAll());
        model.addAttribute("lostCount", service.countByType("LOST"));
        model.addAttribute("foundCount", service.countByType("FOUND"));
        model.addAttribute("openCount", service.countOpen());
        return "index";
    }

    @GetMapping("/items/new")
    public String showCreateForm(Model model) {
        FoundItem item = new FoundItem();
        item.setDateReported(java.time.LocalDate.now());
        model.addAttribute("item", item);
        model.addAttribute("types", ItemType.values());
        return "item-form";
    }

    @PostMapping("/items")
    public String saveItem(@Valid @ModelAttribute("item") FoundItem item,
                           BindingResult result, Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("types", ItemType.values());
            return "item-form";
        }
        if (item.getStatus() == null) {
            item.setStatus(ItemStatus.OPEN);
        }
        service.saveItem(item);
        redirectAttributes.addFlashAttribute("successMessage", "Your report has been saved!");
        return "redirect:/";
    }

    @PostMapping("/items/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam ItemStatus status,
                               RedirectAttributes redirectAttributes) {
        FoundItem item = service.getItemById(id);
        item.setStatus(status);
        service.saveItem(item);
        redirectAttributes.addFlashAttribute("successMessage", "Item status updated.");
        return "redirect:/";
    }

    @PostMapping("/items/{id}/delete")
    public String deleteItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.deleteItem(id);
        redirectAttributes.addFlashAttribute("successMessage", "Report deleted.");
        return "redirect:/";
    }
}
