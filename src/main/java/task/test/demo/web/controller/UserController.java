package task.test.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.test.demo.model.CategoryEntity;
import task.test.demo.model.UserEntity;
import task.test.demo.service.CategoryService;
import task.test.demo.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final CategoryService categoryService;

    public UserController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<UserEntity> users = userService.findAll();
        if (users.isEmpty()) {
            users = null;
        }
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(UserEntity user, Model model) {
        List<CategoryEntity> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            categories = null;
        }
        model.addAttribute("categories", categories);
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(UserEntity user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}" )
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        UserEntity user = userService.findById(id);
        List<CategoryEntity> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            categories = null;
        }
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(UserEntity user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-search")
    public String searchUserByIIN(UserEntity user){
        return "user-search";
    }

    @PostMapping("/user-search")
    public String searchUserByIIN(UserEntity user, Model model){
        String categoryName = userService.findUserCategoryByIin(user.getIin());
        System.out.println(categoryName);
        model.addAttribute("category", categoryName);
        return "user-search";
    }
}
