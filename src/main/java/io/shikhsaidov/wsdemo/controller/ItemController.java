package io.shikhsaidov.wsdemo.controller;

import io.shikhsaidov.wsdemo.model.Item;
import io.shikhsaidov.wsdemo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "home";
    }

    @GetMapping("/remove/{docNo}")
    public String removeItem(@PathVariable long docNo) {
        itemService.removeItem(docNo);
        return "home";
    }

}
