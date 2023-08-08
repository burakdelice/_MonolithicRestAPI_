package com.burakdelice.web;

import com.burakdelice.model.Category;
import com.burakdelice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.burakdelice.constant.RestApiUrl.*;

@Controller
    @RequestMapping("/api"+CATEGORY)
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping({"/", "/list"})
    public String getAllEmployees(Model model) {
        List<Category> listCategories = categoryService.findAll();
        model.addAttribute("listCategories", listCategories);
        return "categories";
    }

    @GetMapping("/getAddCategory")
    public String getAddCategory(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") Category category) {
        categoryService.createCategory(category);
        return "redirect:/api/categories/list";
    }
}