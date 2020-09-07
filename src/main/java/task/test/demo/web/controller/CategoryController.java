package task.test.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import task.test.demo.model.CategoryEntity;
import task.test.demo.service.CategoryService;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String findAll(Model model) {
        List<CategoryEntity> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            categories = null;
        }
        model.addAttribute("categories", categories);
        return "category-list";
    }

    @GetMapping("/category-create")
    public String createCategoryForm(CategoryEntity category) {
        return "category-create";
    }

    @PostMapping("/category-create")
    public String createCategory(CategoryEntity category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/category-delete/{id}" )
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

    @GetMapping("/category-update/{id}")
    public String updateCategoryForm(@PathVariable("id") Long id, Model model){
        CategoryEntity category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category-update";
    }

    @PostMapping("/category-update")
    public String updateCategory(CategoryEntity category){
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }
}
